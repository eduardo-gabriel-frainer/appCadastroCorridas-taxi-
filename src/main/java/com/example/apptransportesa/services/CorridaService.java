package com.example.apptransportesa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apptransportesa.model.Corrida;
import com.example.apptransportesa.repository.CorridaRepository;

@Service
public class CorridaService {

    @Autowired
    private CorridaRepository corridaRepository;

    public Corrida salvarCorrida(Corrida corrida) {
        return corridaRepository.save(corrida);
    }

    public List<Corrida> listarCorridas() {
        return corridaRepository.findAll();
    }

    public Corrida buscarPorId(Long id) {
        Optional<Corrida> corrida = corridaRepository.findById(id);
        return corrida.orElseThrow(() -> new RuntimeException("Corrida não encontrado"));
    }

    public void deletarPorId(Long id) {
        corridaRepository.deleteById(id);
    }

    public List<Corrida> listarCorridasPorAplicativo(Long id_aplicativo) {
        return corridaRepository.findByAplicativoId(id_aplicativo);
    }

    public List<Corrida> listarCorridasPorMotorista(Long id_motorista) {
        return corridaRepository.findByMotoristaId(id_motorista);
    }

    public enum FormaPagamento {
        DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, PIX;
    }

}