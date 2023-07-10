package br.ufma.sppg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufma.sppg.model.*;

public interface ProgramaRepository 
    extends JpaRepository<Programa, Integer> {
        List<Programa> findByNome(String nomePPG);

        @Query("select p.docentes from Programa p where p.id = :idPPG")
        List<Docente> obterDocentes(@Param("idPPG") Integer idPPG);

        @Query("select count(o) from Orientacao o join o.orientador d join d.programas p" + 
               " where (p.id = :idPrograma and o.ano >= :anoIni and o.ano <= :anoFin and o.producoes is not empty)")
        Integer quantitatioOrientacaoProducao(@Param("idPrograma") Integer idPrograma, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);

        @Query("select count(o) from Orientacao o join o.orientador d join d.programas p" + 
        " where (p.id = :idPrograma and o.ano >= :anoIni and o.ano <= :anoFin and o.tecnicas is not empty)")
        Integer quantitatioOrientacaoTecnica(@Param("idPrograma") Integer idPrograma, @Param("anoIni") Integer anoIni, @Param("anoFin") Integer anoFin);
}
