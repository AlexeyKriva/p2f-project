package com.software.pick2flick.nlpmicroservice.controllers;

import com.software.pick2flick.nlpmicroservice.services.TextProcessingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")
@CrossOrigin(origins = "*")
public class TextProcessingController {
    private final TextProcessingService textProcessingService;

    @PostMapping
    public ResponseEntity<List<String>> findKeywords(
            @Valid @RequestBody List<String> descriptions
    ) {
        System.out.println("Пришёл сюда");
        return ResponseEntity.ok(textProcessingService.findKeywords(descriptions));
    }
}