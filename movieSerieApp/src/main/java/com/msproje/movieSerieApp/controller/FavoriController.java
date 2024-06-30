package com.msproje.movieSerieApp.controller;

import com.msproje.movieSerieApp.dto.FilmDTO;
import com.msproje.movieSerieApp.dto.SerieDTO;
import com.msproje.movieSerieApp.exception.ResourceNotFoundException;
import com.msproje.movieSerieApp.model.Favori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.msproje.movieSerieApp.service.FavoriService;
import com.msproje.movieSerieApp.service.FilmService;
import com.msproje.movieSerieApp.service.SerieService;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.List;

@RestController
@RequestMapping("/api/favoris")
@CrossOrigin(origins = "http://localhost:54007")
public class FavoriController {

    @Autowired
    private FavoriService favoriService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SerieService serieService;

    @PostMapping("/add-film")
    public void addFilmToFavori(@RequestParam(required = false) Long userId, @RequestParam Long filmId) {
        if (userId == null) {
            userId = 3L; // Default user ID
        }
        favoriService.addFilmToFavori(userId, filmId);
    }

    @PostMapping("/add-serie")
    public void addSerieToFavori(@RequestParam Long userId, @RequestParam Long serieId) {
        favoriService.addSerieToFavori(userId, serieId);
    }

    @GetMapping("/user/{userId}/films")
    public ResponseEntity<?> getUserFavoriteFilms(@PathVariable Long userId) {
        try {
            List<FilmDTO> favoriteFilms = favoriService.getUserFavoriteFilms(userId);
            return ResponseEntity.ok(favoriteFilms);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user favorite films");
        }
    }

    @GetMapping("/user/{userId}/series")
    public ResponseEntity<?> getUserFavoriteSeries(@PathVariable Long userId) {
        try {
            List<SerieDTO> series = serieService.getAllSeriesDTO(); // Assuming getAllSeriesDTO() returns List<SerieDTO>

            return ResponseEntity.ok(series);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user favorite series");
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favori>> getUserFavoris(@PathVariable Long userId) {
        List<Favori> favoris = favoriService.getUserFavoris(userId);
        return new ResponseEntity<>(favoris, HttpStatus.OK);
    }

    @DeleteMapping("/remove-film")
    public ResponseEntity<?> removeFilmFromFavori(
            @RequestParam(required = false) Long userId,
            @RequestParam Long filmId) {
        if (userId == null) {
            userId = 3L; // Default user ID
        }
        try {
            favoriService.removeFilmFromFavori(userId, filmId);
            return ResponseEntity.ok().body("Film removed from favorites successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error removing film from favorites: " + e.getMessage());
        }
    }
}
