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

import br.ufscar.dc.dsw.AA2Veiculos.domain.Veiculo;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.IVeiculoService;

@RestController
@RequestMapping("/api")
public class VeiculoRestController {

	@Autowired
	private IVeiculoService veiculoService;

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
	    veiculo.setCNPJ((String) json.get("cnpj"));
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
	
	@GetMapping(path = "/veiculos/{id}")
	public ResponseEntity<Veiculo> lista(@PathVariable("id") long id) {
		Veiculo veiculo = veiculoService.buscarPorId(id);
		if (veiculo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculo);
	}
	

	@PostMapping(path = "/veiculos")
	@ResponseBody
	public ResponseEntity<Veiculo> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Veiculo veiculo = new Veiculo(); 
				parse(veiculo, json);
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
	
	@PutMapping(path = "/veiculos/{id}")
	public ResponseEntity<Veiculo> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Veiculo veiculo = veiculoService.buscarPorId(id);
				if (veiculo == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(veiculo, json);
					veiculoService.salvar(veiculo);
					return ResponseEntity.ok(veiculo);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
	@DeleteMapping(path = "/veiculos/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Veiculo veiculo = veiculoService.buscarPorId(id);
		if (veiculo == null) {
			return ResponseEntity.notFound().build();
		} else {
			veiculoService.excluir(id);
			return ResponseEntity.noContent().build();
	}
	}
}
