package com.stopark.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarroRequest {

    @Max(value = 4, message = "Placa do carro possui apenas 4 dígitos")
    private String placa;
    private String marca;
    private String modeloDoCarro;
    private Date fabricacao;
    private String numeroDoDocumento;

}
