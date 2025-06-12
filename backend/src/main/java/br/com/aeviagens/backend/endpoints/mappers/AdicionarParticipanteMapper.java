package br.com.aeviagens.backend.endpoints.mappers;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.endpoints.dto.DadosParticipanteDTO;

public class AdicionarParticipanteMapper {

    public static Participante toParticipante(DadosParticipanteDTO dadosParticipanteDTO) {
        return Participante.builder()
                .nome(dadosParticipanteDTO.getNome())
                .cpf(dadosParticipanteDTO.getCpf())
                .endereco(dadosParticipanteDTO.getEndereco())
                .build();
    }
}
