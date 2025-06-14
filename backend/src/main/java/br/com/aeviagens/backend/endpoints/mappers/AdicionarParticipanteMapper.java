package br.com.aeviagens.backend.endpoints.mappers;

import br.com.aeviagens.backend.domain.Bandeira;
import br.com.aeviagens.backend.domain.DadosDeLocalizacao;
import br.com.aeviagens.backend.domain.DadosDoCartao;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.endpoints.dto.DadosParticipanteDTO;

public class AdicionarParticipanteMapper {

    public static Participante toParticipante(DadosParticipanteDTO dadosParticipanteDTO) {
        return Participante.builder()
                .nome(dadosParticipanteDTO.getNome())
                .cpf(dadosParticipanteDTO.getCpf())
                .dadosDoCartao(DadosDoCartao.builder()
                        .bandeira(dadosParticipanteDTO.getDadosDoCartao().getBandeira())
                        .cvv(dadosParticipanteDTO.getDadosDoCartao().getCvv())
                        .dataValidade(dadosParticipanteDTO.getDadosDoCartao().getDataValidade())
                        .nomeTitular(dadosParticipanteDTO.getDadosDoCartao().getDataValidade())
                        .numeroDoCartao(dadosParticipanteDTO.getDadosDoCartao().getNumeroDoCartao())
                        .build())
                .dadosDeLocalizacao(DadosDeLocalizacao
                        .builder()
                        .cep(dadosParticipanteDTO.getDadosDeLocalizacao().getCep())
                        .rua(dadosParticipanteDTO.getDadosDeLocalizacao().getRua())
                        .pais(dadosParticipanteDTO.getDadosDeLocalizacao().getPais())
                        .bairro(dadosParticipanteDTO.getDadosDeLocalizacao().getBairro())
                        .cidade(dadosParticipanteDTO.getDadosDeLocalizacao().getCidade())
                        .estado(dadosParticipanteDTO.getDadosDeLocalizacao().getEstado())
                        .numero(dadosParticipanteDTO.getDadosDeLocalizacao().getNumero())
                        .build())
                .build();
    }
}
