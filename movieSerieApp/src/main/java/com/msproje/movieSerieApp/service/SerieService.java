// src/main/java/com/msproje/movieSerieApp/service/SerieService.java
package com.msproje.movieSerieApp.service;

import com.msproje.movieSerieApp.dto.SerieDTO;
import com.msproje.movieSerieApp.model.Serie;
import com.msproje.movieSerieApp.repositorie.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository serieRepository;

    public SerieDTO addSerie(SerieDTO serieDTO) {
        Serie serie = new Serie();
        serie.setTitre(serieDTO.getTitre());
        serie.setDateDebut(serieDTO.getDateDebut());
        serie.setDateFin(serieDTO.getDateFin());
        serie.setGenre(serieDTO.getGenre());
        serie.setCreateur(serieDTO.getCreateur());
        serie.setNote(serieDTO.getNote());

        Serie savedSerie = serieRepository.save(serie);

        return convertToDTO(savedSerie);
    }

    public List<SerieDTO> getAllSeries() {
        List<Serie> series = serieRepository.findAll();
        return series.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SerieDTO convertToDTO(Serie serie) {
        SerieDTO dto = new SerieDTO();
        dto.setIdSerie(serie.getId_serie());
        dto.setTitre(serie.getTitre());
        dto.setDateDebut(serie.getDateDebut());
        dto.setDateFin(serie.getDateFin());
        dto.setGenre(serie.getGenre());
        dto.setCreateur(serie.getCreateur());
        dto.setNote(serie.getNote());
        return dto;
    }
}
