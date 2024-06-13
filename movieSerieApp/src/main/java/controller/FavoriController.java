package controller;

import model.Favori;
import model.Film;
import model.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.FavoriService;
import service.FilmService;
import service.SerieService;

import java.util.List;

@RestController
@RequestMapping("/api/favoris")
public class FavoriController {

    @Autowired
    private FavoriService favoriService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SerieService serieService;

    @PostMapping("/add-film")
    public void addFilmToFavori(@RequestParam Long userId, @RequestParam Long filmId) {
        favoriService.addFilmToFavori(userId, filmId);
    }

    @PostMapping("/add-serie")
    public void addSerieToFavori(@RequestParam Long userId, @RequestParam Long serieId) {
        favoriService.addSerieToFavori(userId, serieId);
    }

    @GetMapping("/user/{userId}")
    public List<Favori> getUserFavoris(@PathVariable Long userId) {
        return favoriService.getUserFavoris(userId);
    }

    @GetMapping("/films")
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/series")
    public List<Serie> getAllSeries() {
        return serieService.getAllSeries();
    }
}
