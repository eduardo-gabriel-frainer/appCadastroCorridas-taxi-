package com.example.apptransportesa.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.apptransportesa.exception.CorridaComMotoristasException;
import com.example.apptransportesa.model.Motorista;
import com.example.apptransportesa.services.MotoristaService;

@Controller
public class ControllerMotorista {

    @Autowired
    private MotoristaService motoristaService;

    // -----------------------Corridas-------------------------

    @GetMapping("/cadMotoristas")
    public String mostrarCadastroAplicativos(Model model) {
        model.addAttribute("motorista", new Motorista());
        return "cadMotoristas";
    }

    @PostMapping("/cadMotoristas")
    public String cadastrarMotoristas(Motorista motorista) throws IOException {
        motoristaService.salvarMotorista(motorista);
        return "redirect:/cadMotoristas";
    }

    @GetMapping("/listar_motorista")
    public String listarMotorista(Model model) {
        model.addAttribute("motorista", motoristaService.listarMotoristas());
        return "listar_motorista";
    }

    @PostMapping("/motorista/deletar/{id_motorista}")
    public String deletarMotorista(@PathVariable Long id_motorista, RedirectAttributes redirectAttributes) {

        try {
            motoristaService.deletarPorId(id_motorista);
            redirectAttributes.addFlashAttribute("sucesso", "Motorista deletado com sucesso.");
        } catch (CorridaComMotoristasException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/listar_motorista"; // Corrigido o redirecionamento
    }

    @GetMapping("/motorista/editar/{id_motorista}")
    public String mostrarFormularioEdicao(@PathVariable Long id_motorista, Model model) {
        Motorista motorista = motoristaService.buscarPorId(id_motorista); // Busca o motorista pelo ID
        if (motorista != null) {
            model.addAttribute("motorista", motorista); // Adiciona o motorista ao modelo
            return "editar_motorista"; // Retorna o template de edição
        } else {
            return "redirect:/listar_motorista"; // Se o motorista não existir, redireciona para a lista
        }
    }

    @PostMapping("/motorista/editar")
    public String editarMotorista(@ModelAttribute Motorista motorista) {
        motoristaService.salvarMotorista(motorista); // Atualiza o motorista no banco
        return "redirect:/listar_motorista";
    }

    @PostMapping("/motorista/salvar")
    public String salvarMotorista(@ModelAttribute Motorista motorista) {
        motoristaService.salvarMotorista(motorista);
        return "redirect:/listar_motorista";
    }
}