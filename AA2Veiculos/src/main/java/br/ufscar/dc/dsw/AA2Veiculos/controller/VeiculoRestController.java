package br.ufscar.dc.dsw.AA2Veiculos.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.ILojaService;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IVeiculoService;

@RestController
@RequestMapping("/api")
public class VeiculoRestController {

	@Autowired
	private IVeiculoService veiculoService;
	
    @Autowired
    private ILojaService lojaService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}
	
	private void parse(Veiculo veiculo, JSONObject json) {
	    Object id = json.get("id");
	    if (id != null) {
	        if (id instanceof Integer) {
	            veiculo.setId(((Integer) id).longValue());
	        } else {
	            veiculo.setId((Long) id);
	        }
	    }

	    veiculo.setPlaca((String) json.get("placa"));
	    veiculo.setModelo((String) json.get("modelo"));
	    veiculo.setChassi((String) json.get("chassi"));
	    
	    Object ano = json.get("ano");
	    if (ano != null) {
	        veiculo.setAno(((Number) ano).intValue());
	    }

	    Object quilometragem = json.get("quilometragem");
	    if (quilometragem != null) {
	        veiculo.setQuilometragem(((Number) quilometragem).intValue());
	    }

	    veiculo.setDescricao((String) json.get("descricao"));

	    Object valor = json.get("valor");
	    if (valor != null) {
	        veiculo.setValor(new BigDecimal(valor.toString()));
	    }
	}
	
	@GetMapping(path = "/veiculos")
	public ResponseEntity<List<Veiculo>> lista() {
		List<Veiculo> lista = veiculoService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(path = "/veiculos/lojas/{idLoja}")
	public ResponseEntity<List<Veiculo>> lista(@PathVariable("idLoja") long idLoja) {
        Loja loja = lojaService.buscarPorId(idLoja);
        
		if (loja == null) {
			return ResponseEntity.notFound().build();
		}
        List<Veiculo> veiculos = veiculoService.buscarTodosPorLoja(loja);

		return ResponseEntity.ok(veiculos);
	}
	
	@GetMapping(path = "/veiculos/modelos/{modelo}")
	public ResponseEntity<List<Veiculo>> lista(@PathVariable("modelo") String modelo) {
        List<Veiculo> veiculos = veiculoService.buscarTodosPorModelo(modelo);
        
		if (veiculos == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(veiculos);
	}
	
	

	@PostMapping(path = "/veiculos/lojas/{idLoja}")
	@ResponseBody
	public ResponseEntity<Veiculo> cria(@PathVariable("idLoja") long idLoja, @RequestBody JSONObject json) {
		try {
	        Loja loja = lojaService.buscarPorId(idLoja);
	        
			if (loja == null) {
				return ResponseEntity.notFound().build();
			}
			
			if (isJSONValid(json.toString())) {
				Veiculo veiculo = new Veiculo();				
				parse(veiculo, json);
				
				veiculo.setLoja(loja);

				veiculoService.salvar(veiculo);
				return ResponseEntity.ok(veiculo);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
}
