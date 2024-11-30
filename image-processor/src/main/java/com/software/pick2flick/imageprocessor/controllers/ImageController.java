package com.software.pick2flick.imageprocessor.controllers;

 import com.software.pick2flick.imageprocessor.entities.ImageDto;
 import com.software.pick2flick.imageprocessor.entities.movie.Movie;
 import com.software.pick2flick.imageprocessor.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

 import java.util.List;

@RestController
@RequestMapping(value = "/api/images")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/search-movies")
    public ResponseEntity<List<Movie>> findMoviesByImage(@RequestBody ImageDto imageDto) {
        return ResponseEntity.ok(imageService.findMovieByImage(imageDto.getImageUrl()));
    }

    @PostMapping("/describe")
    public ResponseEntity<List<String>> describeImage(@RequestBody ImageDto imageDto) {
        return ResponseEntity.ok(imageService.describeImage(imageDto.getImageUrl(), imageDto.getIsKeyWordsMode()));
    }
}
