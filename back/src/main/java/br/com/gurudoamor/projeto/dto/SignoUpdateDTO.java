package br.com.gurudoamor.projeto.dto;

public class SignoUpdateDTO {
    private String signo;
    private Integer mes;
    private Integer dia;

    public SignoUpdateDTO(){}
    public SignoUpdateDTO(String signo, Integer mes, Integer dia) {
        this.signo = signo;
        this.mes = mes;
        this.dia = dia;
    }

    public String getSigno() { return signo; }
    public Integer getMes() { return mes; }
    public Integer getDia() { return dia; }

    public void setSigno(String signo) { this.signo = signo; }
    public void setMes(Integer mes) { this.mes = mes; }
    public void setDia(Integer dia) { this.dia = dia; }


}
