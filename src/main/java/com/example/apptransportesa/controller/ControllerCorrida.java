package com.example.apptransportesa.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.apptransportesa.model.Aplicativo;
import com.example.apptransportesa.model.Corrida;
import com.example.apptransportesa.model.Motorista;
import com.example.apptransportesa.repository.AplicativoRepository;
import com.example.apptransportesa.repository.MotoristaRepository;
import com.example.apptransportesa.services.AplicativoService;
import com.example.apptransportesa.services.CorridaService;
import com.example.apptransportesa.services.MotoristaService;

@Controller
public class ControllerCorrida {

    @Autowired
    private CorridaService corridaService;

    @Autowired
    private AplicativoService aplicativoService;

    @Autowired
    private MotoristaService motoristaService;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    // -----------------------Corridas-------------------------

    @GetMapping("/telaInicial")
    public String telaInicial() {
        return "telaInicial";
    }

    @GetMapping("/cadCorridas")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("corrida", new Corrida());

        List<Motorista> motoristas = motoristaRepository.findAll();
        List<Aplicativo> aplicativos = aplicativoRepository.findAll();

        model.addAttribute("motoristas", motoristas);
        model.addAttribute("aplicativos", aplicativos);
        return "cadCorridas";
    }

    @PostMapping("/cadCorridas")
    public String cadastrarPublicacao(Corrida corrida, @RequestParam Long id_aplicativo,
            @RequestParam Long id_motorista)
            throws IOException {
        Aplicativo aplicativo = aplicativoService.buscarPorId(id_aplicativo);
        Motorista motorista = motoristaService.buscarPorId(id_motorista);

        corrida.setAplicativo(aplicativo);
        corrida.setMotorista(motorista);

        corridaService.salvarCorrida(corrida);
        return "redirect:/cadCorridas";
    }

    @GetMapping("/listar_corrida")
    public String listarCorrida(@RequestParam(value = "id_motorista", required = false) Long id_motorista,
            Model model) {

        List<Corrida> corridas;
        if (id_motorista != null) {
            corridas = corridaService.listarCorridasPorMotorista(id_motorista);
        } else {
            corridas = corridaService.listarCorridas();
        }

        float soma = 0;
        for (Corrida corrida : corridas) {
            soma += corrida.getValor();
        }

        // Formatar valor total
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String totalValorFormatado = "R$ " + df.format(soma);

        model.addAttribute("motorista", motoristaService.listarMotoristas());
        model.addAttribute("corridas", corridas);
        model.addAttribute("totalValor", totalValorFormatado);

        return "listar_corrida";
    }

    @PostMapping("/corrida/deletar/{id}")
    public String deletarProduto(@PathVariable Long id) {
        corridaService.deletarPorId(id);
        return "redirect:/listar_corrida";
    }

    @GetMapping("/corrida/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Corrida corrida = corridaService.buscarPorId(id);
        if (corrida != null) {
            model.addAttribute("corrida", corrida);
            model.addAttribute("motoristas", motoristaService.listarMotoristas());
            model.addAttribute("aplicativos", aplicativoService.listarAplicativos());
            return "editar_corrida";
        } else {
            redirectAttributes.addFlashAttribute("erro", "Corrida não encontrada.");
            return "redirect:/listar_corrida";
        }
    }

    @PostMapping("/corrida/editar")
    public String editarPublicacao(@ModelAttribute Corrida corrida,
            @RequestParam Long id_aplicativo,
            @RequestParam Long id_motorista) {

        Aplicativo aplicativo = aplicativoService.buscarPorId(id_aplicativo);
        Motorista motorista = motoristaService.buscarPorId(id_motorista);

        corrida.setAplicativo(aplicativo);
        corrida.setMotorista(motorista);

        corridaService.salvarCorrida(corrida);

        return "redirect:/listar_corrida";
    }

}