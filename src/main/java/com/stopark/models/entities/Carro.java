package com.stopark.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carro extends RepresentationModel<Carro> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String placa;
    private String marca;
    private String modeloDoCarro;
    private Date fabricacao;

    @ManyToOne
    private Cliente cliente;

}
