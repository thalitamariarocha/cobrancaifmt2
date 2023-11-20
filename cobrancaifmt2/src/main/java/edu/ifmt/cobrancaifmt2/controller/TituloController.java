package edu.ifmt.cobrancaifmt2.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.ifmt.cobrancaifmt2.model.titulo.StatusTitulo;
import edu.ifmt.cobrancaifmt2.model.titulo.Titulo;
import edu.ifmt.cobrancaifmt2.repository.TitulosRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/titulo")
public class TituloController {

	@Autowired
    private TitulosRepository titulosRepository;

	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulosRepository.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulos");
		mv.addObject(new Titulo());
		return mv;
	}

	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("CadastroTitulos");
		if (errors.hasErrors()) {
			return "CadastroTitulos";
		}
		titulosRepository.save(titulo);
		mv.addObject("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulo";
	}
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView("CadastroTitulos");
		mv.addObject(titulo);
		return mv;
	}

	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		titulosRepository.deleteById(codigo);
		attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
		return "redirect:/titulo";
	}
}
