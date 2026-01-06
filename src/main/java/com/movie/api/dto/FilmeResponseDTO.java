package com.movie.api.dto;

import com.movie.api.model.Filme;
import com.movie.api.model.Genero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FilmeResponseDTO {

    private Long id;

    private String titulo;

    private String diretor;

    private Integer anoLancamento;

    private Genero genero;

    private String sinopse;

    public FilmeResponseDTO(Filme filme) {
        id = filme.getId();
        titulo = filme.getTitulo();
        diretor = filme.getDiretor();
        anoLancamento = filme.getAnoLancamento();
        genero = filme.getGenero();
        sinopse = filme.getSinopse();
    }
}
