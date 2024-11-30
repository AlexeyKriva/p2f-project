package com.software.pick2flick.imageprocessor.services;

import com.software.pick2flick.imageprocessor.clients.MovieClient;
import com.software.pick2flick.imageprocessor.entities.movie.Movie;
import com.software.pick2flick.imageprocessor.exceptions.BadReadingBytesFromImageException;
import com.software.pick2flick.imageprocessor.exceptions.UnclearImageException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.software.pick2flick.imageprocessor.exceptions.ExceptionMessage.BAD_READING_BYTES_MESSAGE;
import static com.software.pick2flick.imageprocessor.exceptions.ExceptionMessage.UNCLEAR_IMAGE_MESSAGE;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageDescriptionService imageDescriptionService;
    private final MovieClient movieClient;
    private final int MIN_NUMBER_OF_KEYWORDS_OR_PHRASES = 0;

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 100))
    @SneakyThrows
    public List<String> describeImage(String imageUrl, Boolean isKeyWordsMode) {
        byte[] bytesFromImage = toByteArray(loadImageFromUrl(imageUrl), "png");

        List<String> imageDescriptions = imageDescriptionService.describeImage(bytesFromImage, isKeyWordsMode);

        if (isImageClear(imageDescriptions)) {
            return imageDescriptions;
        }

        throw new UnclearImageException(UNCLEAR_IMAGE_MESSAGE);
    }

    @Retryable(maxAttempts = 10, backoff = @Backoff(delay = 100))
    @SneakyThrows
    public List<Movie> findMovieByImage(String imageUrl) {
        byte[] bytesFromImage = toByteArray(loadImageFromUrl(imageUrl), "png");

        List<String> imageDescriptions = imageDescriptionService.describeImage(bytesFromImage, true);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isImageClear(imageDescriptions)) {
            ResponseEntity<List<Movie>> moviesFromDb = movieClient.getFilmsByImageDescription(
                    "Bearer " + (String) ((Jwt) authentication.getCredentials()).getTokenValue(),
                    imageDescriptions
            );

            return moviesFromDb.getBody();
        }

        throw new UnclearImageException(UNCLEAR_IMAGE_MESSAGE);
    }

    public static byte[] toByteArray(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, format, baos);
        baos.flush();
        byte[] byteArray = baos.toByteArray();
        baos.close();
        return byteArray;
    }

    public static BufferedImage loadImageFromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage image = ImageIO.read(url);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> Boolean isImageClear(List<T> imageDescriptions) {
        return imageDescriptions.size() > MIN_NUMBER_OF_KEYWORDS_OR_PHRASES;
    }

    private byte[] readBytesFromImage(MultipartFile image) {
        try {
            return image.getBytes();
        } catch (IOException exception) {
            throw new BadReadingBytesFromImageException(BAD_READING_BYTES_MESSAGE);
        }
    }
}