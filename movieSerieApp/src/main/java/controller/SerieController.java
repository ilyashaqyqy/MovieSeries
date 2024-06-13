package controller;


import model.Serie;
import service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @GetMapping
    public List<Serie> getAllSeries() {
        return serieService.getAllSeries();
    }

    @PostMapping
    public Serie saveSerie(@RequestBody Serie serie) {
        return serieService.saveSerie(serie);
    }

    // Other endpoints for updating, deleting, or retrieving single series
}

