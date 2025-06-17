package br.com.aeviagens.backend.endpoints.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdicionarParticipanteRequestDTO {
    @NotNull
    private String hash;
    @NotNull
    private DadosParticipanteDTO dadosParticipante;
}
