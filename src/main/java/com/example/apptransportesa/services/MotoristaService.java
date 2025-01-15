package com.example.apptransportesa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apptransportesa.exception.CorridaComMotoristasException;
import com.example.apptransportesa.model.Corrida;
import com.example.apptransportesa.model.Motorista;
import com.example.apptransportesa.repository.MotoristaRepository;

@Service
public class MotoristaService {

    @Autowired
    private CorridaService corridaService;

    @Autowired
    private MotoristaRepository motoristaRepository;

    public Motorista salvarMotorista(Motorista motorista) {
        return motoristaRepository.save(motorista);
    }

    public List<Motorista> listarMotoristas() {
        return motoristaRepository.findAll();
    }

    public Motorista buscarPorId(Long id_motorista) {
        Optional<Motorista> motorista = motoristaRepository.findById(id_motorista);
        return motorista.orElseThrow(() -> new RuntimeException("Motorista não encontrado"));
    }

    public void deletarPorId(Long id_motorista) {
        List<Corrida> corridas = corridaService.listarCorridasPorMotorista(id_motorista);
        if (corridas.isEmpty()) {
            motoristaRepository.deleteById(id_motorista);
        } else {
            throw new CorridaComMotoristasException(
                    "Não é possível deletar. Existem corridas associadas a este motorista.");
        }
    }
}