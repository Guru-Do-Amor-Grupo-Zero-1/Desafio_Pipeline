package br.com.gurudoamor.projeto.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

@Entity
@Table(name = "matchs", schema = "public")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MatchResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario1;
    private String usuario2;
    private String compatibilidade;
    private String signos;
    private String mensagem;
    private String distancia;

    // Construtores
    public MatchResponse() {
    }

    public MatchResponse(String usuario1, String usuario2, String compatibilidade, String signos, String mensagem, String distancia) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.compatibilidade = compatibilidade;
        this.signos = signos;
        this.mensagem = mensagem;
        this.distancia = distancia;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(String usuario1) {
        this.usuario1 = usuario1;
    }

    public String getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(String usuario2) {
        this.usuario2 = usuario2;
    }

    public String getCompatibilidade() {
        return compatibilidade;
    }

    public void setCompatibilidade(String compatibilidade) {
        this.compatibilidade = compatibilidade;
    }

    public String getSignos() {
        return signos;
    }

    public void setSignos(String signos) {
        this.signos = signos;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }
}
