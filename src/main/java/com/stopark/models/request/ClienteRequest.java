package com.stopark.models.request;

import com.stopark.models.entities.Endereco;

public class ClienteRequest {

    private int id;
    private String numeroDoDocumento;
    private String nome;
    private Endereco endereco;

    public ClienteRequest() {
    }

    public ClienteRequest(int id, String numeroDoDocumento, String nome, Endereco endereco) {
        this.id = id;
        this.numeroDoDocumento = numeroDoDocumento;
        this.nome = nome;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
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

    @Override
    public String toString() {
        return "ClienteRequest{" +
                "id=" + id +
                ", numeroDoDocumento='" + numeroDoDocumento + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
