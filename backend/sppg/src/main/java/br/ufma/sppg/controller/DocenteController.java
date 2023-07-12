package br.ufma.sppg.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufma.sppg.dto.DocenteProducao;
import br.ufma.sppg.model.Docente;
import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Producao;
import br.ufma.sppg.model.Tecnica;
import br.ufma.sppg.repo.DocenteRepository;
import br.ufma.sppg.service.OrientacaoService;
import br.ufma.sppg.service.ProducaoService;
import br.ufma.sppg.service.TecnicaService;
import br.ufma.sppg.service.exceptions.ServicoRuntimeException;


@RequestMapping("/api/docente")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocenteController{
    @Autowired
    TecnicaService tecnicaServivce;

    @Autowired
    ProducaoService producaoServivce;

    @Autowired
    OrientacaoService orientacaoServivce;

    @Autowired
    DocenteRepository docenteRepository;

    @GetMapping("/obter_producoes/{data1}/{data2}")
    public ResponseEntity<?> obterProducoesDeDocenteContadas(@PathVariable(value = "data1", required = true)  Integer data1,
    @PathVariable(value = "data2", required = true)  Integer data2){

        try{
            List<DocenteProducao> producaoDocente = producaoServivce.obterProducoesTodosDocentes(data1, data2);
            List<DocenteProducao> returnList = new ArrayList<DocenteProducao>();
            for (DocenteProducao docenteProducao : producaoDocente){
                List<Integer> qualis = new ArrayList<Integer>(Collections.nCopies(9, 0));
                for (Producao producao : docenteProducao.getProducoes()){
                    if (producao.getQualis() != null){
                        if(producao.getQualis().equals("A1")){
                            qualis.set(0, qualis.get(0) + 1);
                        }else if(producao.getQualis().equals("A2")){
                            qualis.set(1, qualis.get(1) + 1);
                        }else if(producao.getQualis().equals("A3")){
                        qualis.set(2, qualis.get(2) + 1);
                        }else if(producao.getQualis().equals("A4")){
                            qualis.set(3, qualis.get(3) + 1);
                        }else if(producao.getQualis().equals("B1")){
                            qualis.set(4, qualis.get(4) + 1);
                        }else if(producao.getQualis().equals("B2")){
                            qualis.set(5, qualis.get(5) + 1);
                        }else if(producao.getQualis().equals("B3")){
                            qualis.set(6, qualis.get(6) + 1);
                        }else if(producao.getQualis().equals("B4")){
                            qualis.set(7, qualis.get(7) + 1);
                        }else if(producao.getQualis().equals("C")){
                            qualis.set(8, qualis.get(8) + 1);
                        }
                    }
                }
                DocenteProducao returnObject = DocenteProducao.builder().docente(docenteProducao.getDocente()).
                                                // producoes(docenteProducao.getProducoes()).
                                                qualis(qualis).build(); 
                returnList.add(returnObject);
            }
            return ResponseEntity.ok(returnList);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_producoes/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterProducoesDeDocente(@PathVariable(value = "id", required = true) Integer idDocente,
    @PathVariable(value = "data1", required = true)  Integer data1,
    @PathVariable(value = "data2", required = true)  Integer data2){

        try{
            List<Producao> orientacaoDocente = producaoServivce.obterProducoesDocente(idDocente, data1, data2);
            return ResponseEntity.ok(orientacaoDocente);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/obter_orientacoes/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterOrientacoesDeDocente(@PathVariable(value = "id", required = true) Integer idDocente,
    @PathVariable(value = "data1", required = true)  Integer data1,
    @PathVariable(value = "data2", required = true)  Integer data2){

        try{
            List<Orientacao> orientacaoDocente = orientacaoServivce.obterOrientacaoDocentePorPeriodo(idDocente, data1, data2);
            return ResponseEntity.ok(orientacaoDocente);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_tecnicas/{id}/{data1}/{data2}")
    public ResponseEntity<?> obterTecnicasDeDocente(@PathVariable(value = "id", required = true) Integer idDocente,@PathVariable(value = "data1", required = true)  Integer data1,
    @PathVariable(value = "data2", required = true)  Integer data2){

        try{
            List<Tecnica> tecnicaDocente = tecnicaServivce.obterTecnicasDocentePorPeriodo(idDocente,data1,data2); 
            return ResponseEntity.ok(tecnicaDocente);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter_docentes")
    public ResponseEntity<?> obterDocentes(){
        try{
            List<Docente> docentes = docenteRepository.findAll();
            return ResponseEntity.ok(docentes);
        }catch (ServicoRuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}