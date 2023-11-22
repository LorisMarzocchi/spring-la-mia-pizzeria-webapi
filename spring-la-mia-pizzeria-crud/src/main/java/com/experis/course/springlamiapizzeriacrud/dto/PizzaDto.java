package com.experis.course.springlamiapizzeriacrud.dto;

import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PizzaDto {


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

    private MultipartFile coverFile;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "pizzas_ingredients",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
//    @JsonIgnore
    private List<Ingredient> ingredients = new ArrayList<>();

    //    private Set<Ingredient> ingredients = new HashSet<>();

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public MultipartFile getCoverFile() {
        return coverFile;
    }

    public void setCoverFile(MultipartFile coverFile) {
        this.coverFile = coverFile;
    }


}
