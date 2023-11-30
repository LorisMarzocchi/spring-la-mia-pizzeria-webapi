package com.experis.course.springlamiapizzeriacrud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @NotNull(message = "il campo prezzo non puo essere vuoto")
    @DecimalMin(value = "0.01", message = "Il prezzo non può essere inferiore o uguale a 0")
    @DecimalMax(value = "100.0", message = "Il prezzo non può essere maggiore di 100")
    @Digits(integer = 3, fraction = 2, message = "Il prezzo deve avere al massimo 3 cifre intere e 2 decimali")
    private BigDecimal prezzo;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Lob
    @Column(length = 16777215)
//    @JsonIgnore
    private byte[] cover;

    @OneToMany(mappedBy = "pizza", orphanRemoval = true, fetch = FetchType.LAZY)
//    @JsonIgnore
    private List<OffertaSpeciale> offertaSpeciale = new ArrayList<>();

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

    public List<OffertaSpeciale> getOffertaSpeciale() {
        return offertaSpeciale;
    }

    public void setOffertaSpeciale(List<OffertaSpeciale> offertaSpeciale) {
        this.offertaSpeciale = offertaSpeciale;
    }

    // Getter e Setter image
    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

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


    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }
}
