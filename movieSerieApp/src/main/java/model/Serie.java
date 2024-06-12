package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Setter
@Getter
@Entity
public class Serie {
    // Getters et Setters
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_serie;
    private String titre;
    private Date dateDebut;
    private Date dateFin;
    private String genre;
    private String createur;
    private double note;

}