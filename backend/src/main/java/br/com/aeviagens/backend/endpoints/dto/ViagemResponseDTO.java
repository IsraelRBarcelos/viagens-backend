package br.com.aeviagens.backend.endpoints.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.aeviagens.backend.domain.Viagem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViagemResponseDTO {
  private LocalDate dataDeSaida;
  private LocalDate dataEstimadaDeChegada;
  private LocalTime horaDeSaida;
  private LocalTime horaEstimadaDeChegada;
  private String localDePartidaComDescricao;
  private String localDeChegadaComDescricao;
  private BigDecimal ValorEstimado;


  public static ViagemResponseDTO toDTO(Viagem viagem){
    ViagemResponseDTO viagemResponseDTO = new ViagemResponseDTO();
    viagemResponseDTO.setDataDeSaida(viagem.getDataInicioViagem());
    viagemResponseDTO.setHoraDeSaida(viagem.getHoraDoInicioDaViagem());
    viagemResponseDTO.setLocalDePartidaComDescricao(viagem.getLocalDePartida());
    viagemResponseDTO.setLocalDeChegadaComDescricao(viagem.getLocalDeChegada());

    return viagemResponseDTO; 
  }
}
