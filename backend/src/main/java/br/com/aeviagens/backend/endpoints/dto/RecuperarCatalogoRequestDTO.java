package br.com.aeviagens.backend.endpoints.dto;

import br.com.aeviagens.backend.domain.Participante;
import lombok.Getter;

@Getter
public class RecuperarCatalogoRequestDTO {
    private String hash;
    private Long idRequerente;
}
