package br.com.aeviagens.backend.endpoints.dto;

import br.com.aeviagens.backend.domain.Catalogo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@AllArgsConstructor
@Builder
@Getter
public class RecuperarCatalogoResponseDTO {
    private String titulo;
    private String descricao;
    private List<RecuperarViagemResponseDTO> listaDeViagens;
    private String hash;

    public static RecuperarCatalogoResponseDTO toDTO(Catalogo catalogo) {
        return RecuperarCatalogoResponseDTO.builder()
                .titulo(catalogo.getTitulo())
                .descricao(catalogo.getDescricao())
                .listaDeViagens(catalogo.getViagens().stream().map(RecuperarViagemResponseDTO::toDTO).toList())
                .hash(catalogo.getHash())
                .build();
    }
}
