package br.com.aeviagens.backend.endpoints.dto;

import lombok.Getter;

@Getter
public class AdicionarViagemAoCatalogoRequestDTO {
    String hashDoCatalogo;
    InserirViagemRequestDTO dadosDaViagem;
}
