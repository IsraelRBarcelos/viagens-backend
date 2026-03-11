package br.com.aeviagens.backend.endpoints.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class InserirViagemResponseDTO {
    public String hash;
}
