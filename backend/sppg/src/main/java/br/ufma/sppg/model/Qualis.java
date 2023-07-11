package br.ufma.sppg.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Qualis {
    @Id
    @Column(name = "id_qualis")
    Integer idQualis;
    @Column(name = "issn_sigla")
    String issnSigla;
    String titulo;
    @Column(name = "estrato_sucupira")
    String estratoSucupira;
    @Column(name = "estrato_atualizado")
    String estratoAtualizado;
    @Column(name = "ajuste_sbc")
    String ajusteSbc;
    @Column(name = "link_scopus")
    String linkScopus;
    String percentil;
    @Column(name = "data_atualizacao")
    String dataAtualizacao;
    String logs;
    String area;
    String tipo;

    @OneToMany(mappedBy = "qualisRef")
    List<Producao> producoes;

}
