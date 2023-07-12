package br.ufma.sppg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.model.Docente;
import br.ufma.sppg.model.Producao;
import br.ufma.sppg.service.DocenteService;
import br.ufma.sppg.service.ProducaoService;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/producao")
public class ProducaoController {
    @Autowired
    ProducaoService producaoServ;

    @Autowired
    DocenteService docenteServ;

    @GetMapping("/obterProducao/{idDocente}")
    public ResponseEntity obterProducao(@PathVariable Integer idDocente) {
        try {
            Docente docente = docenteServ.obterDocente(idDocente).get();
            List<Producao> producoes = producaoServ.obterProducoesParaUmDocente(docente);
            return new ResponseEntity(producoes, HttpStatus.OK);
        } catch (ServicoRuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/setandoEstaticticas/{producao}/{esta1}/{esta2}/{esta3}")
    public ResponseEntity setandoEstaticticas(@PathVariable Integer producao, @PathVariable Integer esta1, @PathVariable Integer esta2, @PathVariable Integer esta3) {
        try {
            Producao producaoAtualizada = producaoServ.informarEstatisticasProducao(producao, esta1, esta2, esta3);
            return ResponseEntity.ok().body(producaoAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
