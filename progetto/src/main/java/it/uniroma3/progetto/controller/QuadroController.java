package it.uniroma3.progetto.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.progetto.model.Autore;

import it.uniroma3.progetto.model.Quadro;
import it.uniroma3.progetto.service.AutoreService;
import it.uniroma3.progetto.service.QuadroService;


@Controller
public class QuadroController  {

	@Autowired
	private QuadroService quadroService; 

	@Autowired
	private AutoreService autoreService;




	@GetMapping("/quadro")
	public String mostraFormQuadro(Quadro quadro, Model model) {
		List<Autore> autori = (List<Autore>) autoreService.findAll(); 
		model.addAttribute("autori",autori);
		return "formQuadro";
	}


	@PostMapping("/controllaQuadro")
	public String checkQuadroInfo(@Valid @ModelAttribute Quadro quadro,
			BindingResult bindingResult, Model model,
			@RequestParam(value = "autoriEsistenti", required = false) Long autoriEsistenti) {
		if (bindingResult.hasErrors()) { 
			return "erroreInserimentoQuadro";

		}
		else { 
			try{
				Autore aut= autoreService.findbyId(autoriEsistenti);
				quadro.setAutore(aut);
				model.addAttribute(quadro);
				quadroService.add(quadro); 

			}catch(Exception e){

				return "erroreInserimentoQuadro";

			}


		}
		return "confermaQuadro";
	}

	@GetMapping("/rimuoviQuadro")
	public String mostraFormRimuoviQuadro(Quadro quadro,Model model) {
		List<Quadro> quadri = (List<Quadro>) quadroService.findAll(); 
		model.addAttribute("quadri",quadri);
		return "RimuoviQuadro";
	}
	@PostMapping("/rimuoviQuadro")
	public String RimuoviQuadro(@ModelAttribute("quadro") Quadro quadro,@RequestParam(value = "quadriEsistenti", required = false) Long quadriEsistenti,Model model,BindingResult bindingResult){

		if (bindingResult.hasErrors()) {
			List<Quadro> quadri = (List<Quadro>) quadroService.findAll(); 
			model.addAttribute("quadri",quadri);
			return "RimuoviQuadro";
		}

		Quadro quadroEliminato=this.quadroService.findbyId(quadriEsistenti);
		model.addAttribute("quadro",quadroEliminato);
		//immagineQuadroService.delete(quadroEliminato.getImmagine());
		quadroService.delete(quadroEliminato);


		return "confermaRimozioneQuadro";
	}


	@GetMapping("/selezionaQuadro")
	public String selezionaQuadro(Model model,Quadro quadro){
		List<Quadro> quadri= (List<Quadro>) quadroService.findAll();
		model.addAttribute("quadri",quadri);
		List<Autore> autori= (List<Autore>) autoreService.findAll();
		model.addAttribute("autori",autori);
		return "selezionaQuadro";
	}

	@PostMapping("/modificaQuadro")
	public String modificaQuadro(Model model,@RequestParam(value = "quadriEsistenti") Long quadriEsistenti){
		Quadro quadro= quadroService.findbyId(quadriEsistenti);
		model.addAttribute(quadro);		
		List<Autore> autori = (List<Autore>) autoreService.findAll(); 
		model.addAttribute("autori",autori);
		return "modificaQuadroForm";
	}

	@PostMapping("/confermaModificaQuadro")
	public String confermaModificaQuadro(@RequestParam(value = "autoriEsistenti", required = false) Long autoriEsistenti,@Valid @ModelAttribute Quadro quadro,Model model,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "modificaQuadroForm";
		}
		else
		{		try{	Autore aut= autoreService.findbyId(autoriEsistenti);
		quadro.setAutore(aut);
		model.addAttribute(quadro);
		quadroService.add(quadro);
		//return "confermaQuadro";
		}catch(Exception e){
			model.addAttribute("QuadroSelezionato",quadro);
			return "modificaQuadroForm";
		}
		return "confermaQuadro";
		}
	}

	@GetMapping(value = "/dettagliQuadro")
	public String dettagliQuadro(@ModelAttribute("id") Long id, Model model){
		Quadro quadro = quadroService.findbyId(id);
		model.addAttribute(quadro);
		return "dettagliQuadro";
	}

	@GetMapping(value="/listaQuadri")
	public String mostraListaQuadri(Model model){
		List<Quadro> quadri = (List<Quadro>) quadroService.findAll(); 
		model.addAttribute("quadri",quadri);
		return "listaQuadri";
	}

}


