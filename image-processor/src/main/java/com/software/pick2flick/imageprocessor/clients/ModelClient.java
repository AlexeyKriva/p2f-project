package com.software.pick2flick.imageprocessor.clients;

import com.software.pick2flick.imageprocessor.entities.descrption.TextDescription;
import com.software.pick2flick.imageprocessor.entities.descrption.ImageDescription;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "model-client")
public interface ModelClient {
    @PostMapping("/Salesforce/blip-image-captioning-base")
    List<TextDescription> processImageFromSalesforceModel(
            @RequestHeader("Authorization") String apiKey,
            @RequestBody byte[] imageBytes
    );

    @PostMapping("/timm/resnet50.a1_in1k")
    List<ImageDescription> processImageFromTimmModel(
            @RequestHeader("Authorization") String apiKey,
            @RequestBody byte[] imageBytes
    );

    @PostMapping(value = "/apple/mobilevit-small", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    List<ImageDescription> processImageFromAppleModel(
            @RequestHeader("Authorization") String apiKey,
            @RequestBody byte[] imageBytes
    );
}