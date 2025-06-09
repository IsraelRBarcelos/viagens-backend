package br.com.aeviagens.backend.endpoints.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import br.com.aeviagens.backend.domain.Viagem;
import lombok.Setter;


@Setter
public class ViagemResponseDTO {
  public LocalDate dataDeSaida;
  public LocalDate dataEstimadaDeChegada;
  public LocalTime horaDeSaida;
  public LocalTime horaEstimadaDeChegada;
  public String localDePartidaComDescricao;
  public String localDeSaidaComDescricao;
  public BigDecimal ValorEstimado;


  public static ViagemResponseDTO toDTO(Viagem viagem){

    ViagemResponseDTO viagemResponseDTO = new ViagemResponseDTO();
    viagemResponseDTO.setDataDeSaida(viagem.getDataInicioViagem());
    viagemResponseDTO.setHoraDeSaida(viagem.getHoraDoInicioDaViagem());
    this.horaEstimadaDeChegada = viagem.calcularHoraEstimadaDeChegada();
    this.dataEstimadaDeChegada = viagem.calcularDataDeChegada();
    this.localDePartidaComDescricao = viagem.getLocalDePartida();
    this.localDeSaidaComDescricao = viagem.getLocalDeChegada();
    this.ValorEstimado = viagem.getValorEstimado();

    return viagemResponseDTO; 
  }
}
