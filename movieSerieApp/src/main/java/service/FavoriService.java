package service;


import exception.ResourceNotFoundException;
import model.Favori;
import model.Film;
import model.Serie;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorie.FavoriRepository;
import repositorie.FilmRepository;
import repositorie.SerieRepository;
import repositorie.UserRepository;

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
}