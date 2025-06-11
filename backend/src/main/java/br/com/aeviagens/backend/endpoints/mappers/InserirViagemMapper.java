package br.com.aeviagens.backend.endpoints.mappers;

import br.com.aeviagens.backend.domain.Local;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.InserirViagemRequestDTO;

import java.time.format.DateTimeFormatter;

public class InserirViagemMapper {
    public static Viagem toViagem(InserirViagemRequestDTO request) {
        return Viagem.builder()
                .dataInicioViagem(request.getDataDeInicio().format(DateTimeFormatter.ofPattern("ddMMyyyy")))
                .horaDoInicioDaViagem(request.getHoraDeInicio().format(DateTimeFormatter.ofPattern("hhmm")))
                .tempoEstimadodeChegada(Viagem.calcularHoraEstimadaDeChegada(request.getTempoEstimadoDeViagem()))
                .valorEstimado(request.getValorEstimado())
                .localDePartida(Local.valueOf(request.getLocalDeSaida()))
                .localDeChegada(Local.valueOf(request.getLocalDeChegada()))
                .build();
    }
}
