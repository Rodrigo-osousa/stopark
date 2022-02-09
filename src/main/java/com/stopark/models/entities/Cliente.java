package com.stopark.models.entities;

import javax.persistence.*;

@Entity
public class Cliente {

    @Id
    private String cpf;
    private String nome;

    @OneToOne
    private Carro carro;

    @ManyToOne
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(String cpf, String nome, Carro carro) {
        this.cpf = cpf;
        this.nome = nome;
        this.carro = carro;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


}
