package br.com.aeviagens.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import jakarta.persistence.Enumerated;

public class Viagem {
  
  private final String padraoDeFormatacaoDeData = "ddMMyyyy";
  private final String padraoDeFormatacaoDeTempo = "HHmm";
  private String dataInicioViagem;
  private String horaDoInicioDaViagem;
  private String tempoEstimadoViagem;
  private Participantes participantes;
  private BigDecimal valorEstimado;
  @Enumerated
  private Local localDePartida;
  @Enumerated
  private Local localDeChegada;
  private ArrayList<Local> paradas;


  public Viagem() {

  }

  public LocalDate getDataInicioViagem() {
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoDeFormatacaoDeData);
    return LocalDate.parse(this.dataInicioViagem, formatador);
  }

  public String getTempoEstimadoViagem() {
    return this.tempoEstimadoViagem;
  }

  public LocalTime getHoraDoInicioDaViagem() {
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern(padraoDeFormatacaoDeTempo);
    return LocalTime.parse(this.horaDoInicioDaViagem, formatador);
  }

  public Participantes getParticipantes() {
    return this.participantes;
  }

  public String getLocalDePartida() {
    return this.localDePartida.mostrarConteudo();
  }

  public BigDecimal getValorEstimado() {
    return this.valorEstimado;
  }
  
  public ArrayList<Local> getParadas() {
    return this.paradas;
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
