package com.software.pick2flick.imageprocessor.services;

import com.software.pick2flick.imageprocessor.clients.ModelClient;
import com.software.pick2flick.imageprocessor.clients.NlpClient;
import com.software.pick2flick.imageprocessor.entities.descrption.TextDescription;
import com.software.pick2flick.imageprocessor.entities.descrption.ImageDescription;
import com.software.pick2flick.imageprocessor.entities.descrption.ModelName;
import com.software.pick2flick.imageprocessor.exceptions.ModelNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import static com.software.pick2flick.imageprocessor.exceptions.ExceptionMessage.*;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ImageDescriptionService {
    private final ModelClient modelClient;
    private final NlpClient nlpClient;
    private final Double MIN_SCORE = 0.25D;


    @Value("${huggingface.api.key}")
    private String modelApiKey;

    public List<String> describeImage(byte[] bytesFromImage, Boolean isKeyWordsMode) {
        List<ImageDescription> imageDescriptions = describeImageByModels(bytesFromImage);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isKeyWordsMode) {
            return nlpClient.findKeywords(
                    "Bearer " + (String) ((Jwt) authentication.getCredentials()).getTokenValue(),
                    imageDescriptions.stream()
                            .map(ImageDescription::getLabel)
                            .toList()
            );
        } else {
            return imageDescriptions.stream()
                    .map(ImageDescription::getLabel)
                    .toList();
        }
    }

    public List<ImageDescription> describeImageByModels(byte[] bytesFromImage) {
        return Stream.concat(
                findImageDescription(bytesFromImage, ModelName.Salesforce).stream()
                        .filter(imageDescription -> deleteDescriptionsWithSmallScores(imageDescription) != null),
                Stream.concat(
                        findImageDescription(bytesFromImage, ModelName.Timm).stream()
                                .filter(imageDescription -> deleteDescriptionsWithSmallScores(imageDescription) != null),
                        findImageDescription(bytesFromImage, ModelName.Apple).stream()
                                .filter(imageDescription -> deleteDescriptionsWithSmallScores(imageDescription) != null)
                )
        ).toList();
    }



    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 100))
    private List<ImageDescription> findImageDescription(byte[] bytesFromImage, ModelName modelName) {
        switch (modelName) {
            case Salesforce -> {
                List<TextDescription> textDescriptions = modelClient.processImageFromSalesforceModel(
                        "Bearer " + modelApiKey, bytesFromImage);

                return textDescriptions.stream()
                        .map(textDescription -> new ImageDescription(textDescription.getGeneratedText(), 1.0))
                        .toList();
            }
            case Timm -> {
                return modelClient.processImageFromTimmModel(
                        "Bearer " + modelApiKey, bytesFromImage);
            }
            case Apple -> {
                return modelClient.processImageFromAppleModel(
                        "Bearer " + modelApiKey, bytesFromImage);
            }
        }

        throw new ModelNotFoundException(String.format(MODEL_NOT_FOUND_MESSAGE, modelName));
    }

    private ImageDescription deleteDescriptionsWithSmallScores(ImageDescription description) {
        if (isScoreHigh(description.getScore())) {
            return description;
        }

        return null;
    }

    private Boolean isScoreHigh(Double score) {
        return score >= MIN_SCORE;
    }
}