//salvei o pom

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

import com.example.apptransportesa.exception.CorridaComAplicativosException;
import com.example.apptransportesa.model.Aplicativo;
import com.example.apptransportesa.services.AplicativoService;

@Controller
public class ControllerAplicativo {

    @Autowired
    private AplicativoService aplicativoService;

    // ---------------------Aplicativo---------------------------

    @GetMapping("/cadAplicativos")
    public String mostrarCadastroAplicativos(Model model) {
        model.addAttribute("aplicativo", new Aplicativo());
        return "cadAplicativos";
    }

    @PostMapping("/cadAplicativos")
    public String cadastrarAplicativo(Aplicativo aplicativo) throws IOException {
        aplicativoService.salvarAplicativo(aplicativo);
        return "redirect:/cadAplicativos";
    }

    @GetMapping("/listarAplicativo")
    public String listarAplicativo(Model model) {
        model.addAttribute("aplicativo", aplicativoService.listarAplicativos());
        return "listarAplicativo";
    }

    @PostMapping("/aplicativo/deletar/{id_aplicativo}")
    public String deletarAplicativo(@PathVariable Long id_aplicativo, RedirectAttributes redirectAttributes) {
        try {
            aplicativoService.deletarPorId(id_aplicativo);
            redirectAttributes.addFlashAttribute("sucesso", "Aplicativo deletado com sucesso.");
        } catch (CorridaComAplicativosException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/listarAplicativo";
    }

    @GetMapping("/aplicativo/editar/{id_aplicativo}")
    public String mostrarFormularioEdicao(@PathVariable Long id_aplicativo, Model model) {
        Aplicativo aplicativo = aplicativoService.buscarPorId(id_aplicativo); // Busca o produto pelo ID
        if (aplicativo != null) {
            model.addAttribute("aplicativo", aplicativo); // Adiciona o produto ao modelo
            return "editar_aplicativo"; // Retorna o template de edição
        } else {
            return "redirect:/aplicativo/lista"; // Se o produto não existir, redireciona para a lista
        }
    }

    @PostMapping("/aplicativo/editar")
    public String editarAplicativo(@ModelAttribute Aplicativo aplicativo) {
        aplicativoService.salvarAplicativo(aplicativo); // Atualiza o produto no banco
        return "redirect:/listar_aplicativo"; // Redireciona para a lista de produtos após a edição
    }

    @PostMapping("/aplicativo/salvar")
    public String salvarAplicativo(@ModelAttribute Aplicativo aplicativo) {
        aplicativoService.salvarAplicativo(aplicativo); // Salva o produto no banco de dados
        return "redirect:/listarAplicativo"; // Redireciona para a lista de produtos
    }

}