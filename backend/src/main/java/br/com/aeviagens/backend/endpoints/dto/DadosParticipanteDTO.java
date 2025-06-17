package br.com.aeviagens.backend.endpoints.dto;

import br.com.aeviagens.backend.domain.DadosDeLocalizacao;
import br.com.aeviagens.backend.domain.DadosDoCartao;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DadosParticipanteDTO {
    private String nome;
    private String cpf;
    private DadosDoCartao dadosDoCartao;
    private DadosDeLocalizacao dadosDeLocalizacao;
}
