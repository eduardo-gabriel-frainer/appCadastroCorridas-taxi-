package com.example.apptransportesa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "aplicativo")
public class Aplicativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_aplicativo;
    private String nome;
    private float tarifa;

    @OneToMany(mappedBy = "aplicativo", cascade = CascadeType.ALL)
    private List<Corrida> publicacaos = new ArrayList<>();

    public Long getId_aplicativo() {
        return id_aplicativo;
    }

    public void setId_aplicativo(Long id_aplicativo) {
        this.id_aplicativo = id_aplicativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
        this.tarifa = tarifa;
    }

}