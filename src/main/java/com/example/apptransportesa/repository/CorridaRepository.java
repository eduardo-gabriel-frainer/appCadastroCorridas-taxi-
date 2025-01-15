package com.example.apptransportesa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.apptransportesa.model.Corrida;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {

    @Query("SELECT p FROM Corrida p WHERE p.aplicativo.id_aplicativo = :id_aplicativo")
    List<Corrida> findByAplicativoId(@Param("id_aplicativo") Long id_aplicativo);

    @Query("SELECT p FROM Corrida p WHERE p.motorista.id_motorista = :id_motorista")
    List<Corrida> findByMotoristaId(@Param("id_motorista") Long id_motorista);

}