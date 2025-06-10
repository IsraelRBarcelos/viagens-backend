package br.com.aeviagens.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Enumerated;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viagem {
  
  private final String padraoDeFormatacaoDeData = "ddMMyyyy";
  private final String padraoDeFormatacaoDeTempo = "HHmm";
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String dataInicioViagem;
  private String horaDoInicioDaViagem;
  private String tempoEstimadoViagem;

  @Getter
  private BigDecimal valorEstimado;
  @Enumerated(EnumType.STRING)
  private Local localDePartida;
  @Enumerated(EnumType.STRING)
  private Local localDeChegada;
  @Getter
  @ElementCollection(targetClass = Local.class)
  @Enumerated(EnumType.STRING)
  private List<Local> paradas = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @ElementCollection(targetClass = Participante.class)
  private ArrayList<Participante> participantes;

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

  public Viagem comParticipantes(ArrayList<Participante> participantes) {
    if(participantes.isEmpty()) {
      return this;
    }
    this.participantes = participantes;
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
