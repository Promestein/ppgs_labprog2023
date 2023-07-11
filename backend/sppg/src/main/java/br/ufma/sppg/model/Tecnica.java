package br.ufma.sppg.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tecnica")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tecnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnica")
    Integer id;

    @Column(name = "tipo")
    String tipo;

    @Column(name = "titulo")
    String titulo;

    @Column(name = "autores")
    String autores;

    @Column(name="ano")
    Integer ano;

    @Column(name = "financiadora")
    String financiadora;

    @Column(name = "outras_informacoes")
    String outrasInformacoes;

    @Column(name = "qtd_grad")
    Integer qtdGrad;

    @Column(name = "qtd_mestrado")
    Integer qtdMestrado;

    @Column(name = "qtd_doutorado")
    Integer qtdDoutorado;

    @ManyToMany
    @JoinTable(name = "tecnica_orientacao", joinColumns = @JoinColumn(name = "id_tecnica"), inverseJoinColumns = @JoinColumn(name = "id_orientacao"))
    List<Orientacao> orientacoes;

    @ManyToMany(mappedBy = "tecnicas")
    List<Docente> docentes;
}
