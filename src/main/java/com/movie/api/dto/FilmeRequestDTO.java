package com.movie.api.dto;

import com.movie.api.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDTO {

    private String titulo;

    private String diretor;

    private Integer anoLancamento;

    private Genero genero;

    private String sinopse;
}
