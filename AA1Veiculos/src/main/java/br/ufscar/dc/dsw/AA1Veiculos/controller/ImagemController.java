package br.ufscar.dc.dsw.AA1Veiculos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import br.ufscar.dc.dsw.AA1Veiculos.domain.Imagem;

import br.ufscar.dc.dsw.AA1Veiculos.service.spec.IImagemService;

@Controller
@RequestMapping("/imagem")
public class ImagemController {

	@Autowired
	private IImagemService imagemService;
	

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id) {
		Imagem image = imagemService.buscarPorId(id);
		
		if (image == null || image.getDados() == null) {
			System.out.println("Erros encontrados: ");
			System.out.println(image.toString());
	        return ResponseEntity.noContent().build();
	    }
		
	    MediaType contentType = image.getContentType() == "image/png" ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;
	    ByteArrayResource resource = new ByteArrayResource(image.getDados());

	    return ResponseEntity
	            .ok()
	            .contentType(contentType)
	            .body(resource);
	}
}
