package br.com.aeviagens.backend.endpoints.dto;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class AdicionarParticipanteResponseDTO {

    private String localSaida;
    private String localDestino;
    private String hash;
    private List<String> nomesDeTodosOsParticipantes;

    public static AdicionarParticipanteResponseDTO toDTO(Viagem viagem) {
        return AdicionarParticipanteResponseDTO.builder()
                .localSaida(viagem.getLocalDePartida())
                .localDestino(viagem.getLocalDeChegada())
                .hash(viagem.getHash())
                .nomesDeTodosOsParticipantes(viagem.getParticipantes().stream().map(Participante::getNome).toList())
                .build();
    }
}
