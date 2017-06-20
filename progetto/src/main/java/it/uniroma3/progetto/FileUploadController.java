package it.uniroma3.progetto;

import it.uniroma3.progetto.model.Autore;
import it.uniroma3.progetto.model.Quadro;
import it.uniroma3.progetto.service.AutoreService;
import it.uniroma3.progetto.service.QuadroService;
import it.uniroma3.progetto.storage.StorageFileNotFoundException;
import it.uniroma3.progetto.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    @Autowired
	private QuadroService quadroService; 

	@Autowired
	private AutoreService autoreService;
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
}
//    @GetMapping("/quadro")
//    public String listUploadedFiles(Model model,Quadro Quadro) throws IOException {
//
//        model.addAttribute("files", storageService
//                .loadAll()
//                .map(path ->
//                        MvcUriComponentsBuilder
//                                .fromMethodName(FileUploadController.class, "serveFile", path.getFileName().toString())
//                                .build().toString())
//                .collect(Collectors.toList()));
//    	
//		List<Autore> autori = (List<Autore>) autoreService.findAll(); 
//		model.addAttribute("autori",autori);
//		return "formQuadro";
//    }
//
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity
//                .ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
//                .body(file);
//    }
//
//    @PostMapping("/quadro")
//    public String handleFileUpload(Model model,@RequestParam("file") MultipartFile file,@Valid @ModelAttribute Quadro quadro,
//			BindingResult bindingResult,RedirectAttributes redirectAttributes,@RequestParam(value = "autoriEsistenti", required = false) Long autoriEsistenti) throws IOException {
////    	if (bindingResult.hasErrors()) { 
////			return "erroreInserimentoQuadro";
////
////		}
////		else {
////			try{
//				Autore aut= autoreService.findbyId(autoriEsistenti);
//				quadro.setAutore(aut);
//				
////		        redirectAttributes.addFlashAttribute("message",
////		                "You successfully uploaded " + file.getOriginalFilename() + "!");
//				quadro.setFile(file.getBytes());
//		        model.addAttribute(quadro);
//		        
//
//				quadroService.add(quadro); 
////				
////		}catch(Exception e){
////			
////			return "erroreInserimentoQuadro";
////
////		}
//			//}
//	     
//        
//
//        return "confermaQuadro";
//    }
//
//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }
//
//}
