// src/main/java/com/msproje/movieSerieApp/controller/SerieController.java
package com.msproje.movieSerieApp.controller;

import com.msproje.movieSerieApp.dto.SerieDTO;
import com.msproje.movieSerieApp.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @PostMapping
    public ResponseEntity<SerieDTO> addSerie(@RequestBody SerieDTO serieDTO) {
        SerieDTO savedSerie = serieService.addSerie(serieDTO);
        return ResponseEntity.ok(savedSerie);
    }

    @GetMapping
    public ResponseEntity<List<SerieDTO>> getAllSeries() {
        List<SerieDTO> series = serieService.getAllSeries();
        return ResponseEntity.ok(series);
    }
}
