package br.ufma.sppg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufma.sppg.model.Orientacao;
import br.ufma.sppg.model.Producao;


public interface OrientacaoRepository extends JpaRepository<Orientacao, Integer> {
        List<Orientacao> findAllById(Integer id);

        @Query("SELECT o FROM Orientacao o JOIN o.orientador d JOIN d.programas p where p.id = :idPrograma AND o.ano >= :anoInicio AND o.ano<= :anoFim")
        Optional<List<Orientacao>> findByPPG(Integer idPrograma, Integer anoInicio, Integer anoFim);

        @Query("SELECT o FROM Orientacao o JOIN o.orientador d WHERE d.id = :idDocente AND o.ano >= :anoInicio AND o.ano<= :anoFim")
        Optional<List<Orientacao>> findByDocente(Integer idDocente, Integer anoInicio, Integer anoFim);


        @Query("Select o.discente from Orientacao o join Docente d where d.id = :idDocente")
        List<String> discenteApartirDeUmDocenteId(Integer idDocente);

        // make a @Query  SELECT p.* FROM Producao p JOIN Orientacao o ON p.orientacao_id = o.id WHERE o.discente LIKE '%<valor_do_discente>%'
        @Query("Select p from Producao p where p.autores LIKE %:discente%")
        List<Producao> producaoApartirDeUmDiscente(String discente);
        

        @Query("SELECT o FROM Orientacao o " +
                        "JOIN Docente d " +
                        "JOIN Tecnica t " +
                        "WHERE d.id = :idDocente " +
                        "AND o.ano >= :anoInicio " +
                        "AND o.ano <= :anoFim " +
                        "AND t.ano >= :anoInicio " +
                        "AND t.ano <= :anoFim")
        Optional<List<Orientacao>> obterOrientacoesComTecnicaPorPeriodo(Integer idDocente, Integer anoInicio,
                        Integer anoFim);

        @Query("SELECT o FROM Orientacao o " +
                        "JOIN Docente d " +
                        "JOIN Producao p " +
                        "WHERE d.id = :idDocente " +
                        "AND o.ano >= :anoInicio " +
                        "AND o.ano <= :anoFim " +
                        "AND p.ano >= :anoInicio " +
                        "AND p.ano <= :anoFim")
        Optional<List<Orientacao>> obterOrientacoesComProducaoPorPeriodo(Integer idDocente, Integer anoInicio,
                        Integer anoFim);

        Orientacao findByTipoAndDiscenteAndTitulo(String tipo, String discente, String titulo);

        @Query("Select o from Orientacao o join Docente d on d.id = o.orientador where d.id = :idDocente AND o.ano >= :anoInicio AND o.ano <= :anoFim")
        List<Orientacao> findDocenetePorUmPeriododeOrientacoes(Integer idDocente, Integer anoInicio, Integer anoFim);
}
