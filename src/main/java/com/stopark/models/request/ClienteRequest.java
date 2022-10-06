package com.stopark.models.request;

import com.stopark.models.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest {

    private String numeroDoDocumento;
    private String nome;
    private String cep;
    private String complemento;

}
