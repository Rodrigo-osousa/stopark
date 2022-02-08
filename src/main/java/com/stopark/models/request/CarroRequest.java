package com.stopark.models.request;

import java.util.Date;

public class CarroRequest {

    private String marca;
    private String modelo;
    private Date fabricacao;

    public CarroRequest() {
    }

    public CarroRequest(String marca, String modelo, Date fabricacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.fabricacao = fabricacao;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getFabricacao() {
        return fabricacao;
    }

    public void setFabricacao(Date fabricacao) {
        this.fabricacao = fabricacao;
    }

    @Override
    public String toString() {
        return "CarroRequest{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricacao=" + fabricacao +
                '}';
    }
}
