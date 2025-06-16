package br.com.aeviagens.backend.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DadosDeLocalizacao {
    private int numero;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private Long cep;
}
