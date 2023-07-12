package br.ufma.sppg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufma.sppg.model.Producao;
import br.ufma.sppg.model.Programa;

public interface ProducaoRepository 
    extends JpaRepository<Producao,Integer> {
    
        Producao findByTituloAndNomeLocal(String titulo, String nomeLocal);
        Producao findByTitulo(String titulo);

        @Query("Select d from Producao d")
        List<Producao> findTudo();

        @Query("Select p from Producao p where p.id=:id")
        Producao acharPeloId(Integer id);
}
