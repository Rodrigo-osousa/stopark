package com.stopark.models.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marca;
    private String modelo;
    private Date fabricacao;
    @OneToOne
    private Cliente cliente;

    public Carro() {
    }

    public Carro(int id, String marca, String modelo, Date fabricacao) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.fabricacao = fabricacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Carro{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fabricacao=" + fabricacao +
                '}';
    }
}
