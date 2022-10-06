package com.stopark.models.entities;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
public class Cliente extends RepresentationModel<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numeroDoDocumento;
    private String nome;

    @OneToOne
    private Carro carro;

    @ManyToOne
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(int id, String numeroDoDocumento, String nome, Carro carro, Endereco endereco) {
        this.id = id;
        this.numeroDoDocumento = numeroDoDocumento;
        this.nome = nome;
        this.carro = carro;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroDoDocumento() {
        return numeroDoDocumento;
    }

    public void setNumeroDoDocumento(String numeroDoDocumento) {
        this.numeroDoDocumento = numeroDoDocumento;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", numeroDoDocumento='" + numeroDoDocumento + '\'' +
                ", nome='" + nome + '\'' +
                ", carro=" + carro +
                ", endereco=" + endereco +
                '}';
    }
}
