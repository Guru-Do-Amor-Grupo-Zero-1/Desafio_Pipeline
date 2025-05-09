package br.com.gurudoamor.projeto.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"usuario1", "usuario2", "compatibilidade", "signos", "mensagem", "distancia"}) // Garante a ordem correta
public class MatchResponseDTO {
    private String usuario1;
    private String usuario2;
    private String compatibilidade;
    private String signos;
    private String mensagem;
    private String distancia;

    public MatchResponseDTO(String usuario1, String usuario2, String compatibilidade, String signos, String mensagem, String distancia) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.compatibilidade = compatibilidade;
        this.signos = signos;
        this.mensagem = mensagem;
        this.distancia = distancia;
    }

    public String getUsuario1() { return usuario1; }
    public String getUsuario2() { return usuario2; }
    public String getCompatibilidade() { return compatibilidade; }
    public String getSignos() { return signos; }
    public String getMensagem() { return mensagem; }
    public String getDistancia() { return distancia; }
}
