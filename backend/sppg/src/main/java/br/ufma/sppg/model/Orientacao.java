package br.ufma.sppg.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orientacao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orientacao {
    @Id
    @Column(name = "id_orientacao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name="natureza")
    private String natureza;
    
    @Column(name="discente")
    private String discente;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "modalidade")
    private String modalidade;

    @Column(name = "instituicao")
    private String instituicao;

    @Column(name = "curso")
    private String curso;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_docente")
    Docente orientador;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "producao_orientacao", joinColumns = @JoinColumn(name = "id_orientacao"), inverseJoinColumns = @JoinColumn(name = "id_producao"))
    List<Producao> producoes;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tecnica_orientacao", joinColumns = @JoinColumn(name = "id_orientacao"), inverseJoinColumns = @JoinColumn(name = "id_tecnica"))
    List<Tecnica> tecnicas;

}
