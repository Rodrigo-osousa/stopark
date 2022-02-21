package com.stopark.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String placa;
    private String marca;
    private String modeloDoCarro;
    private Date fabricacao;

    @ManyToOne
    private Cliente cliente;

    public Carro() {
    }

    public Carro(int id, String placa, String marca, String modeloDoCarro, Date fabricacao, Cliente cliente) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modeloDoCarro = modeloDoCarro;
        this.fabricacao = fabricacao;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        return "Carro{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modeloDoCarro='" + modeloDoCarro + '\'' +
                ", fabricacao=" + fabricacao +
                ", cliente=" + cliente +
                '}';
    }
}
