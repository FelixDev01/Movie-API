package com.movie.api;

import com.movie.api.dto.FilmeRequestDTO;
import com.movie.api.dto.FilmeResponseDTO;
import com.movie.api.model.Filme;
import com.movie.api.model.Genero;
import com.movie.api.repository.FilmeRepository;
import com.movie.api.service.FilmeService;
import com.movie.api.service.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @Mock
    private FilmeRepository repository;

    @InjectMocks
    private FilmeService service;

    private Long existingId;
    private Long nonExistingId;
    private Filme filme;
    private FilmeRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 100L;

        filme = new Filme();
        filme.setId(existingId);
        filme.setTitulo("Interestelar");
        filme.setDiretor("Christopher Nolan");
        filme.setAnoLancamento(2014);

        requestDTO = new FilmeRequestDTO("Interestelar", "Christopher Nolan", 2014, Genero.AVENTURA, "Um grupo de exploradores...");
    }

    @Test
    @DisplayName("adicionarFilme deve salvar filme e retornar FilmeResponseDTO")
    void adicionarFilmeDeveSalvarERetornarDTO() {

        Mockito.when(repository.save(any())).thenReturn(filme);

        FilmeResponseDTO result = service.adicionarFilme(requestDTO);

        assertNotNull(result);
        assertEquals(filme.getTitulo(), result.getTitulo());
        verify(repository, times(1)).save(any());
    }

    @Test
    @DisplayName("encontrarFilme deve retornar DTO quando o Id existe")
    void encontrarFilmeDeveRetornarDTOQuandoIdExiste() {
        
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(filme));

        FilmeResponseDTO result = service.encontrarFilme(existingId);

        assertNotNull(result);
        assertEquals(existingId, result.getId());
        verify(repository).findById(existingId);
    }

    @Test
    @DisplayName("encontrarFilme deve lancar ResourceNotFoundException quando Id não existe")
    void encontrarFilmeDeveLancarExcecaoQuandoIdNaoExiste() {
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.encontrarFilme(nonExistingId);
        });
    }

    @Test
    @DisplayName("excluirFilme deve fazer nada quando Id existe")
    void excluirFilmeDeveFazerNadaQuandoIdExiste() {

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.doNothing().when(repository).deleteById(existingId);

        assertDoesNotThrow(() -> {
            service.excluirFilme(existingId);
        });

        verify(repository).deleteById(existingId);
    }

    @Test
    @DisplayName("excluirFilme deve lancar ResourceNotFoundException quando Id não existe")
    void excluirFilmeDeveLancarExcecaoQuandoIdNaoExiste() {

        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            service.excluirFilme(nonExistingId);
        });

        verify(repository, never()).deleteById(any());
    }
}
