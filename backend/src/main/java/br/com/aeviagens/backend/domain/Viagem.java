package br.com.aeviagens.backend.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.aeviagens.backend.constants.DateTimeFormat;
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
@Getter
public class Viagem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String hash;
  private Participante host;

  private String dataInicioViagem;
  private String horaDoInicioDaViagem;
  private String tempoEstimadodeChegada;
  private int numeroMaximoDePassageiros;
  private BigDecimal valorEstimado;
  @Enumerated(EnumType.STRING)
  private Local localDePartida;
  @Enumerated(EnumType.STRING)
  private Local localDeChegada;
  @ElementCollection(targetClass = Local.class)
  @Enumerated(EnumType.STRING)
  private List<Local> paradas = new ArrayList<>();
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Participante> participantes = new ArrayList<>();

  public LocalDate getDataInicioViagem() {
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern(DateTimeFormat.DATE_FORMAT.getFormat());
    return LocalDate.parse(this.dataInicioViagem, formatador);
  }

  public LocalTime getHoraDoInicioDaViagem() {
    DateTimeFormatter formatador = DateTimeFormatter.ofPattern(DateTimeFormat.TIME_FORMAT.getFormat());
    return LocalTime.parse(this.horaDoInicioDaViagem, formatador);
  }

  public String getLocalDePartida() {
    return this.localDePartida.mostrarConteudo();
  }

  public String getLocalDeChegada() {
    return this.localDeChegada.mostrarConteudo();
  }
  
  public LocalDate calcularDataDeChegada(int tempoEstimadoDeViagens) {
    return null;
  }

  public static String calcularHoraEstimadaDeChegada(int tempoEstimadoDeViagem) {
    return null;
  }

  @PrePersist
  public void gerarHash() {
    if (this.hash == null || this.hash.isBlank()) {
      this.hash = UUID.randomUUID().toString();
    }
  }
}
