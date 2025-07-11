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

import jakarta.validation.Valid;
import br.ufscar.dc.dsw.AA2Veiculos.domain.Loja;
import br.ufscar.dc.dsw.AA2Veiculos.service.spec.ILojaService;

@RestController
@RequestMapping("/api/lojas")
public class LojaRestController {

    @Autowired
    private ILojaService lojaService;

    private boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    @GetMapping
    public ResponseEntity<List<Loja>> listar() {
        List<Loja> lista = lojaService.buscarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody Loja loja) {
        try {
            lojaService.salvar(loja);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loja> buscar(@PathVariable Long id) {
        Loja loja = lojaService.buscarPorId(id);
        if (loja == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(loja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable Long id,
            @RequestBody Loja loja) {

        Loja existente = lojaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        loja.setId(id);
        try {
            lojaService.salvar(loja);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Loja existente = lojaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        lojaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}