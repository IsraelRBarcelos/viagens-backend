package br.com.aeviagens.backend.endpoints.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RecuperarViagemResponseDTO {
  private LocalDate dataDeSaida;
  private LocalDate dataEstimadaDeChegada;
  private LocalTime horaDeSaida;
  private LocalTime horaEstimadaDeChegada;
  private String localDePartidaComDescricao;
  private String localDeChegadaComDescricao;
  private BigDecimal ValorEstimado;
  private List<Participante> participantes = new ArrayList<>();


  public static RecuperarViagemResponseDTO toDTO(Viagem viagem){
    return RecuperarViagemResponseDTO.builder()
            .dataDeSaida(viagem.getDataInicioViagem())
            .horaDeSaida(viagem.getHoraDoInicioDaViagem())
            .localDePartidaComDescricao(viagem.getLocalDePartida())
            .localDeChegadaComDescricao(viagem.getLocalDeChegada())
            .participantes(viagem.getParticipantes())
            .build();
  }
}
