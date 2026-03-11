package br.com.aeviagens.backend.endpoints.dto;

import br.com.aeviagens.backend.domain.Catalogo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CriarCatalogoResponseDTO {
    private String titulo;
    private String descricao;
    private String hash;

    public static CriarCatalogoResponseDTO toDTO(Catalogo catalogo) {
        return CriarCatalogoResponseDTO.builder()
                .titulo(catalogo.getTitulo())
                .descricao(catalogo.getDescricao())
                .hash(catalogo.getHash())
                .build();
    }
}
