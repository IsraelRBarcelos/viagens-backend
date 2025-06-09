package br.com.aeviagens.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.persistence.Enumerated;
import lombok.Getter;

public class Viagem {
  
  private final String padraoDeFormatacaoDeData = "ddMMyyyy";
  private final String padraoDeFormatacaoDeTempo = "HHmm";
  private String dataInicioViagem;
  private String horaDoInicioDaViagem;
  private String tempoEstimadoViagem;
  @Getter
  private Participantes participantes;
  @Getter
  private BigDecimal valorEstimado;
  @Enumerated
  private Local localDePartida;
  @Enumerated
  private Local localDeChegada;
  @Getter
  private ArrayList<Local> paradas;


  public Viagem() {

  }

  public LocalDate getDataInicioViagem() {
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoDeFormatacaoDeData);
    return LocalDate.parse(this.dataInicioViagem, formatador);
  }

  public LocalTime getHoraDoInicioDaViagem() {
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoDeFormatacaoDeTempo);
    return LocalTime.parse(this.horaDoInicioDaViagem, formatador);
  }

    public String getLocalDePartida() {
    return this.localDePartida.mostrarConteudo();
  }

    public String getLocalDeChegada() {
    return this.localDeChegada.mostrarConteudo();
  }

  public Viagem comParadas(ArrayList<Local> paradas ) {
    this.paradas = paradas;
    return this;
  }

  public Viagem comDataDeInicio(String dataInicio) {
    this.dataInicioViagem = dataInicio;
    return this;
  }

  public Viagem comTempoEstimado(String tempoEstimado) {
    this.tempoEstimadoViagem = tempoEstimado;
    return this;
  }

  public Viagem comParticipantes(Participantes participantes) {
    if(participantes.isNotEmpty()) {
      this.participantes = participantes;
    }

    return this;
  }

  public Viagem comValor(BigDecimal valor) {
    this.valorEstimado = valor;

    return this;
  }
  
  public LocalDate calcularDataDeChegada() {
    return null;
  }

  public LocalTime calcularHoraEstimadaDeChegada() {
    return null;
  }
}
