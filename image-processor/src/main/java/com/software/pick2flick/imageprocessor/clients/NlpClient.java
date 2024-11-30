package com.software.pick2flick.imageprocessor.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "nlp-client")
public interface NlpClient {
    @PostMapping
    List<String> findKeywords(
            @RequestHeader("Authorization") String apiKey,
            List<String> descriptions
    );
}