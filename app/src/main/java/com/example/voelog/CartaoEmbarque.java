package com.example.voelog;

public class CartaoEmbarque {
    private String origem;
    private String destino;
    private String ida;
    private String volta;
    private String adulto;
    private String crianca;
    private String bebe;
    private String status;
    private String identificador;

    public CartaoEmbarque(String origem, String destino, String ida, String volta, String adulto, String crianca, String bebe, String status, String identificador) {
        this.origem = origem;
        this.destino = destino;
        this.ida = ida;
        this.volta = volta;
        this.adulto = adulto;
        this.crianca = crianca;
        this.bebe = bebe;
        this.status = status;
        this.identificador = identificador;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getIda() {
        return ida;
    }

    public void setIda(String ida) {
        this.ida = ida;
    }

    public String getVolta() {
        return volta;
    }

    public void setVolta(String volta) {
        this.volta = volta;
    }

    public String getAdulto() {
        return adulto;
    }

    public void setAdulto(String adulto) {
        this.adulto = adulto;
    }

    public String getCrianca() {
        return crianca;
    }

    public void setCrianca(String crianca) {
        this.crianca = crianca;
    }

    public String getBebe() {
        return bebe;
    }

    public void setBebe(String bebe) {
        this.bebe = bebe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}
