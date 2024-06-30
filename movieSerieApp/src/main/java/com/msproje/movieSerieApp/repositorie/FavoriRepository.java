package com.msproje.movieSerieApp.repositorie;

import com.msproje.movieSerieApp.model.Favori;
import com.msproje.movieSerieApp.model.Film;
import com.msproje.movieSerieApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriRepository extends JpaRepository<Favori, Long> {
    List<Favori> findByUser(User user);
    Optional<Favori> findByUserAndFilm(User user, Film film);
    boolean existsByUserAndFilm(User user, Film film);
}
