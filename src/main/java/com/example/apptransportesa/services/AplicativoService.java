package com.example.apptransportesa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apptransportesa.exception.CorridaComAplicativosException;
import com.example.apptransportesa.model.Aplicativo;
import com.example.apptransportesa.model.Corrida;
import com.example.apptransportesa.repository.AplicativoRepository;

@Service
public class AplicativoService {

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Autowired
    private CorridaService corridaService;

    public Aplicativo salvarAplicativo(Aplicativo aplicativo) {
        return aplicativoRepository.save(aplicativo);
    }

    public List<Aplicativo> listarAplicativos() {
        return aplicativoRepository.findAll();
    }

    public Aplicativo buscarPorId(Long id) {
        Optional<Aplicativo> aplicativo = aplicativoRepository.findById(id);
        return aplicativo.orElseThrow(() -> new RuntimeException("App não encontrado"));
    }

    public void deletarPorId(Long id_aplicativo) {
        List<Corrida> corridas = corridaService.listarCorridasPorAplicativo(id_aplicativo);
        if (corridas.isEmpty()) {
            aplicativoRepository.deleteById(id_aplicativo);
        } else {
            throw new CorridaComAplicativosException(
                    "Não é possível deletar. Existem corridas associadas a esta aplicativo.");
        }
    }
}
