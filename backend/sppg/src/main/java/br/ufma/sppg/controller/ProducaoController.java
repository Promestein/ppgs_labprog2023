package br.ufma.sppg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.model.Producao;
import br.ufma.sppg.service.ProducaoService;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@RestController
@RequestMapping("/api/producao")
public class ProducaoController {
    @Autowired
    ProducaoService producaoServ;

    @GetMapping("/obterProducao")
    public ResponseEntity obterPrograma() {
        try {
            List<Producao> producoes = producaoServ.retornarTodasAsProducoes();
            return new ResponseEntity(producoes, HttpStatus.OK);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
