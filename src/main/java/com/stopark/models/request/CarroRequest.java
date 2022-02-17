package com.stopark.models.request;

import javax.validation.constraints.Max;
import java.util.Date;

public class CarroRequest {
    @Max(value = 4, message = "Placa do carro possui apenas 4 dígitos")
    private String placa;
    private String marca;
    private String modeloDoCarro;
    private Date fabricacao;
    private String numeroDoDocumento;
    public CarroRequest() {
    }

    public CarroRequest(String placa, String marca, String modeloDoCarro, Date fabricacao, String numeroDoDocumento) {
        this.placa = placa;
        this.marca = marca;
        this.modeloDoCarro = modeloDoCarro;
        this.fabricacao = fabricacao;
        this.numeroDoDocumento = numeroDoDocumento;
    }

    public String getNumeroDoDocumento() {
        return numeroDoDocumento;
    }

    public void setNumeroDoDocumento(String numeroDoDocumento) {
        this.numeroDoDocumento = numeroDoDocumento;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModeloDoCarro() {
        return modeloDoCarro;
    }

    public void setModeloDoCarro(String modeloDoCarro) {
        this.modeloDoCarro = modeloDoCarro;
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
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modeloDoCarro='" + modeloDoCarro + '\'' +
                ", fabricacao=" + fabricacao +
                ", numeroDoDocumento='" + numeroDoDocumento + '\'' +
                '}';
    }
}
