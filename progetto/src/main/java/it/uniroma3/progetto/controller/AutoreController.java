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
import it.uniroma3.progetto.service.AutoreService;


@Controller
public class AutoreController {

	@Autowired 
	private AutoreService autoreService;

	@GetMapping("/autore")
	public String mostraFormAutore(Autore autore) {
		return "formAutore";
	}

	@PostMapping("/controllaAutore")
	public String checkAutoreInfo(@Valid @ModelAttribute Autore autore, 
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "erroreInserimentoAutore";
		}
		else {  try{

			model.addAttribute(autore);
			autoreService.add(autore); }
		catch(Exception e){
			return "erroreInserimentoAutore";

		}
		}
		return "confermaAutore";
	}                                      

	@GetMapping("/rimuoviAutore")
	public String mostraFormRimuoviAutore(Autore autore,Model model) {
		List<Autore> autori = (List<Autore>) autoreService.findAll(); 
		model.addAttribute("autori",autori);
		return "RimuoviAutore";
	}
	@PostMapping("/rimuoviAutore")
	public String RimuoviAutore(@ModelAttribute("cognome") String cognome,BindingResult bindingResult,Model model, @RequestParam(value = "autoriEsistenti", required = false) Long autoriEsistenti){
		if (bindingResult.hasErrors()) {
			return "RimuoviAutore";
		}
		else {try{
			Autore aut= autoreService.findbyId(autoriEsistenti);
			model.addAttribute(aut);
			this.autoreService.delete(aut);

		}catch(Exception e){
			List<Autore> autori = (List<Autore>) autoreService.findAll(); 
			model.addAttribute("autori",autori);
			return "rimuoviAutore";
		}}
		return "confermaRimozioneAutore";

	}


	@GetMapping("/selezionaAutore")
	public String selezionaAutore(Model model){
		List<Autore> autori= (List<Autore>) autoreService.findAll();
		model.addAttribute("autori",autori);
		return "selezionaAutore";
	}

	@PostMapping("/modificaAutore")
	public String modificaAutore(@RequestParam(value = "autoriEsistenti") Long autoreSelezionatoID, Model model){
		Autore autore= autoreService.findbyId(autoreSelezionatoID);
		model.addAttribute("autoreSelezionato",autore);
		return "modificaAutoreForm";
	}

	@PostMapping("/confermaModificaAutore")
	public String confermaModificaAutore(@Valid @ModelAttribute Autore autore,Model model,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "modificaAutoreForm";
		}
		else
		{
			try{
				autoreService.add(autore);
				return "confermaAutore";
			}catch(Exception e){
				model.addAttribute("autoreSelezionato",autore);
				return "modificaAutoreForm";
			}
		}
	}

	@GetMapping(value = "/dettagliAutore")
	public String dettagliAutore(@ModelAttribute("id") Long id, Model model){
		Autore aut = autoreService.findbyId(id);
		model.addAttribute("autore", aut);
		return "dettagliAutore";
	}

}
