package com.experis.course.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pizzas")
public class Pizza {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "il nome non puo essere vuoto")
    @Size(min = 5, max = 25, message = "il campo non può essere minore di 5 e maggiore di 25 caratteri")
    @Column(length = 25, nullable = false, unique = true)
    private String nome;
    @NotBlank(message = "il campo descrizione non puo essere vuoto")
    @Size(min = 5, max = 255, message = "il campo non può essere minore di 5 maggiore di 255 caratteri")
    private String descrizione;
    @NotBlank(message = "il campo Url non puo essere vuoto")
    @Size(min = 5, max = 255, message = "il campo non può essere minore di 5 e maggiore di 255 caratteri")
    @URL(message = "inserisci un url valido")
    private String urlImage;
    @NotNull(message = "il campo prezzo non puo essere vuoto")
    @DecimalMin(value = "0.01", message = "Il prezzo non può essere inferiore o uguale a 0")
    @DecimalMax(value = "100.0", message = "Il prezzo non può essere maggiore di 100")
    @Digits(integer = 3, fraction = 2, message = "Il prezzo deve avere al massimo 3 cifre intere e 2 decimali")
    private BigDecimal prezzo;
    @CreationTimestamp
    private LocalDateTime createdAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
