package br.com.aeviagens.backend.endpoints.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import br.com.aeviagens.backend.domain.Participante;
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
  private List<Participante> participantes = new ArrayList<>();


  public static ViagemResponseDTO toDTO(Viagem viagem){
    ViagemResponseDTO viagemResponseDTO = new ViagemResponseDTO();
    viagemResponseDTO.setDataDeSaida(viagem.getDataInicioViagem());
    viagemResponseDTO.setHoraDeSaida(viagem.getHoraDoInicioDaViagem());
    viagemResponseDTO.setLocalDePartidaComDescricao(viagem.getLocalDePartida());
    viagemResponseDTO.setLocalDeChegadaComDescricao(viagem.getLocalDeChegada());
    viagemResponseDTO.setParticipantes(viagem.getParticipantes());

    return viagemResponseDTO; 
  }
}
