package br.com.gurudoamor.projeto.enums;

public enum Signos {
    ARIES("Fogo"), TOURO("Terra"), GEMEOS("Ar"), CANCER("Água"),
    LEAO("Fogo"), VIRGEM("Terra"), LIBRA("Ar"), ESCORPIAO("Água"),
    SAGITARIO("Fogo"), CAPRICORNIO("Terra"), AQUARIO("Ar"), PEIXES("Água");

    private final String elemento;

    Signos(String elemento) {
        this.elemento = elemento;
    }

    public String getElemento() {
        return elemento;
    }

    public static Signos obterSigno(int mes, int dia) {
        if ((mes == 3 && dia >= 21) || (mes == 4 && dia <= 19)) return ARIES;
        if ((mes == 4 && dia >= 20) || (mes == 5 && dia <= 20)) return TOURO;
        if ((mes == 5 && dia >= 21) || (mes == 6 && dia <= 20)) return GEMEOS;
        if ((mes == 6 && dia >= 21) || (mes == 7 && dia <= 22)) return CANCER;
        if ((mes == 7 && dia >= 23) || (mes == 8 && dia <= 22)) return LEAO;
        if ((mes == 8 && dia >= 23) || (mes == 9 && dia <= 22)) return VIRGEM;
        if ((mes == 9 && dia >= 23) || (mes == 10 && dia <= 22)) return LIBRA;
        if ((mes == 10 && dia >= 23) || (mes == 11 && dia <= 21)) return ESCORPIAO;
        if ((mes == 11 && dia >= 22) || (mes == 12 && dia <= 21)) return SAGITARIO;
        if ((mes == 12 && dia >= 22) || (mes == 1 && dia <= 19)) return CAPRICORNIO;
        if ((mes == 1 && dia >= 20) || (mes == 2 && dia <= 18)) return AQUARIO;
        if ((mes == 2 && dia >= 19) || (mes == 3 && dia <= 20)) return PEIXES;
        return null;
    }
}
