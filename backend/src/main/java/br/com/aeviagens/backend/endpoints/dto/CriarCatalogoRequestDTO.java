package br.com.aeviagens.backend.endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CriarCatalogoRequestDTO {
    private String titulo;
    private String descricao;
    private Long idHost;
}
