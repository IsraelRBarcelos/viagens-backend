package br.com.aeviagens.backend.endpoints.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class InserirViagemRequestDTO {

    @NotNull(message = "O local de saída é obrigatório")
    private String localDeSaida;

    @NotNull(message = "O local de chegada é obrigatório")
    private String localDeChegada;

    @NotNull(message = "A data de início é obrigatória")
    private LocalDate dataDeInicio;

    @NotNull(message = "A hora de início é obrigatória")
    private LocalTime horaDeInicio;

    @Min(value = 1, message = "O tempo estimado deve ser maior que 0")
    private int tempoEstimadoDeViagem;

    @NotNull(message = "O valor estimado é obrigatório")
    private BigDecimal valorEstimado;

}
