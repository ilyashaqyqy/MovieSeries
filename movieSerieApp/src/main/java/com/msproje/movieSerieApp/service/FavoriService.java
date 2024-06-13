package com.msproje.movieSerieApp.service;


import com.msproje.movieSerieApp.exception.ResourceNotFoundException;
import com.msproje.movieSerieApp.model.Favori;
import com.msproje.movieSerieApp.model.Film;
import com.msproje.movieSerieApp.model.Serie;
import com.msproje.movieSerieApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.msproje.movieSerieApp.repositorie.FavoriRepository;
import com.msproje.movieSerieApp.repositorie.FilmRepository;
import com.msproje.movieSerieApp.repositorie.SerieRepository;
import com.msproje.movieSerieApp.repositorie.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriService {

    @Autowired
    private FavoriRepository favoriRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private SerieRepository serieRepository;

    public void addFilmToFavori(Long userId, Long filmId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceNotFoundException("Film not found"));

        Favori favori = new Favori();
        favori.setUser(user);
        favori.setFilm(film);
        favoriRepository.save(favori);
    }

    public void addSerieToFavori(Long userId, Long serieId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Serie serie = serieRepository.findById(serieId).orElseThrow(() -> new ResourceNotFoundException("Serie not found"));

        Favori favori = new Favori();
        favori.setUser(user);
        favori.setSerie(serie);
        favoriRepository.save(favori);
    }

    public List<Favori> getUserFavoris(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return favoriRepository.findByUser(user);
    }

    public List<Film> getUserFavoriteFilms(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Favori> favoris = favoriRepository.findByUser(user);
        List<Film> favoriteFilms = new ArrayList<>();

        for (Favori favori : favoris) {
            if (favori.getFilm() != null) {
                favoriteFilms.add(favori.getFilm());
            }
        }

        return favoriteFilms;
    }

    public List<Serie> getUserFavoriteSeries(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Favori> favoris = favoriRepository.findByUser(user);
        List<Serie> favoriteSeries = new ArrayList<>();

        for (Favori favori : favoris) {
            if (favori.getSerie() != null) {
                favoriteSeries.add(favori.getSerie());
            }
        }

        return favoriteSeries;
    }


}
