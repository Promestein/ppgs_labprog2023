package br.ufma.sppg.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Producao;
import br.ufma.sppg.model.Tecnica;
import br.ufma.sppg.service.OrientacaoService;
import br.ufma.sppg.service.ProducaoService;
import br.ufma.sppg.service.TecnicaService;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;

@RestController
@RequestMapping("/api/orientacao")
public class OrientacaoController {

  @Autowired
  OrientacaoService orientacaoService;

  @Autowired
  ProducaoService producaoService;

  @Autowired
  TecnicaService tecnicaService;

  @GetMapping("/obterOrientacoesDocenteComTecnica")
  public ResponseEntity<?> obterOrientacoesDocenteComTecnica(
      @RequestParam("docente") Integer idDocente, Integer dataInicio, Integer dataFim) {
    try {
      Optional<List<Orientacao>> orientacoes = orientacaoService.obterOrientacoesComTecnicaPorPeriodo(idDocente,
          dataInicio, dataFim);
      return new ResponseEntity<>(
          orientacoes.get(),
          HttpStatus.OK);
    } catch (ServicoRuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/obterOrientacoesDocenteComProducao")
  public ResponseEntity<?> obterOrientacaoDocenteComProducao(
      @RequestParam("docente") Integer idDocente, Integer dataInicio, Integer dataFim) {
    try {
      Optional<List<Orientacao>> orientacoes = orientacaoService.obterOrientacoesComProducaoPorPeriodo(idDocente,
          dataInicio, dataFim);

      return new ResponseEntity<>(
          orientacoes.get(),
          HttpStatus.OK);
    } catch (ServicoRuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/obterDiscentesDocente/{idDocente}")
  public ResponseEntity<?> obterDiscentesDocente(
      @PathVariable(value = "idDocente", required = true) Integer idDocente) {
    try {
      List<String> orientacoes = orientacaoService.discenteApartirDeUmDocenteId(idDocente);
      return ResponseEntity.ok(orientacoes);
    } catch (ServicoRuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/gerarOrientacoesComProducao/{discente}")
  public ResponseEntity<?> gerarOrientacoesComProducao(
      @PathVariable(value = "discente", required = true) String discente) {
    try {
      List<Orientacao> orientacoes = orientacaoService.orientacaoApartirDeUmDocenteId(discente);
      return ResponseEntity.ok(orientacoes);
    } catch (ServicoRuntimeException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}