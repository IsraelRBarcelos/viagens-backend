package br.com.aeviagens.backend.endpoints;

import br.com.aeviagens.backend.domain.Local;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.InserirViagemRequestDTO;
import br.com.aeviagens.backend.repository.ViagemRepository;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import br.com.aeviagens.backend.services.ParticipanteServiceImpl;
import br.com.aeviagens.backend.services.ViagemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ViagemEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
public class ViagemEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ViagemService viagemService;

    @Mock
    private ViagemJPARepository viagemJPARepository;

    @MockBean
    private ViagemRepository viagemRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ParticipanteServiceImpl participanteService() {
            return Mockito.mock(ParticipanteServiceImpl.class);
        }
    }


    @Test
    void deveRecuperarViagemPelaHash() throws Exception {

        String hash = "hash";


        Viagem viagemMock = Viagem.builder()
                .localDeChegada(Local.CANOAS)
                .hash("hash")
                .localDePartida(Local.FLORIANOPOLIS)
                .valorEstimado(BigDecimal.valueOf(500))
                .horaDoInicioDaViagem("2233")
                .numeroMaximoDePassageiros(5)
                .dataInicioViagem("26082006")
                .participantes(List.of())
                .build();

        // Mockar o comportamento do serviço
        when(viagemService.recuperarViagemPorHash(Mockito.anyString()))
                .thenReturn(viagemMock);

        // Act + Assert
        mockMvc.perform(post("/viagens/" + hash)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hash))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarErroAoTerUmaHashInvalida() throws Exception {

        String hash = "hash";


        Viagem viagemMock = Viagem.builder()
                .localDeChegada(Local.CANOAS)
                .hash("hash")
                .localDePartida(Local.FLORIANOPOLIS)
                .valorEstimado(BigDecimal.valueOf(500))
                .horaDoInicioDaViagem("2233")
                .numeroMaximoDePassageiros(5)
                .dataInicioViagem("26082006")
                .participantes(List.of())
                .build();

        // Mockar o comportamento do serviço
        when(viagemService.recuperarViagemPorHash(Mockito.anyString()))
                .thenThrow(new IllegalArgumentException("hash invalida"));

        // Act + Assert
        mockMvc.perform(post("/viagens/" + hash)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hash))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deveRetornarErroAoTerHashNula() throws Exception {

        String hash = "   ";


        Viagem viagemMock = Viagem.builder()
                .localDeChegada(Local.CANOAS)
                .hash("hash")
                .localDePartida(Local.FLORIANOPOLIS)
                .valorEstimado(BigDecimal.valueOf(500))
                .horaDoInicioDaViagem("2233")
                .numeroMaximoDePassageiros(5)
                .dataInicioViagem("26082006")
                .participantes(List.of())
                .build();

        // Mockar o comportamento do serviço

        // Act + Assert
        mockMvc.perform(post("/viagens/" + hash)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hash))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarErroTerExcecaoQualquer() throws Exception {

        String hash = "2   ";


        Viagem viagemMock = Viagem.builder()
                .localDeChegada(Local.CANOAS)
                .hash("hash")
                .localDePartida(Local.FLORIANOPOLIS)
                .valorEstimado(BigDecimal.valueOf(500))
                .horaDoInicioDaViagem("2233")
                .numeroMaximoDePassageiros(5)
                .dataInicioViagem("26082006")
                .participantes(List.of())
                .build();

        // Mockar o comportamento do serviço
        when(viagemService.recuperarViagemPorHash(Mockito.anyString()))
                .thenThrow(new NullPointerException("hash invalida"));


        // Act + Assert
        mockMvc.perform(post("/viagens/" + hash)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(hash))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deveInserirNovaViagemComSucesso() throws Exception {
        InserirViagemRequestDTO request = new InserirViagemRequestDTO();
        request.setLocalDeChegada(String.valueOf(Local.CANOAS));
        request.setLocalDeSaida(String.valueOf(Local.FLORIANOPOLIS));
        request.setValorEstimado(BigDecimal.valueOf(500));
        request.setHoraDeInicio(LocalTime.MIDNIGHT);
        request.setNumeroMaximoDePassageiros(4);
        request.setTempoEstimadoDeViagem(4);
        request.setDataDeInicio(LocalDate.now());

        Viagem viagem = Viagem.builder()
                .localDeChegada(Local.valueOf(request.getLocalDeChegada()))
                .localDePartida(Local.valueOf(request.getLocalDeSaida()))
                .valorEstimado(request.getValorEstimado())
                .horaDoInicioDaViagem(String.valueOf(request.getHoraDeInicio()))
                .numeroMaximoDePassageiros(request.getNumeroMaximoDePassageiros())
                .dataInicioViagem(String.valueOf(request.getDataDeInicio()))
                .participantes(List.of())
                .build();

        // Mocka a conversão e a persistência
        when(viagemService.salvarViagem(any())).thenReturn(viagem);

        mockMvc.perform(post("/viagens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarErroPorNumeroMaximoDePassageirosExcedido() throws Exception {
        InserirViagemRequestDTO request = new InserirViagemRequestDTO();
        request.setLocalDeChegada(String.valueOf(Local.CANOAS));
        request.setLocalDeSaida(String.valueOf(Local.FLORIANOPOLIS));
        request.setValorEstimado(BigDecimal.valueOf(500));
        request.setHoraDeInicio(LocalTime.MIDNIGHT);
        request.setNumeroMaximoDePassageiros(5);
        request.setTempoEstimadoDeViagem(5);
        request.setDataDeInicio(LocalDate.now());

        Viagem viagem = Viagem.builder()
                .localDeChegada(Local.valueOf(request.getLocalDeChegada()))
                .localDePartida(Local.valueOf(request.getLocalDeSaida()))
                .valorEstimado(request.getValorEstimado())
                .horaDoInicioDaViagem(String.valueOf(request.getHoraDeInicio()))
                .numeroMaximoDePassageiros(request.getNumeroMaximoDePassageiros())
                .dataInicioViagem(String.valueOf(request.getDataDeInicio()))
                .participantes(List.of())
                .build();

        // Mocka a conversão e a persistência
        when(viagemService.salvarViagem(any())).thenReturn(viagem);

        mockMvc.perform(post("/viagens")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRemoverViagemComSucesso() throws Exception {
        String hash = "hashValido";

        Mockito.doNothing().when(viagemService).removerViagemPorHash(hash);

        mockMvc.perform(delete("/viagens/" + hash))
                .andExpect(status().isOk());
    }

    @Test
    void deveRetornarBadRequestQuandoHashForNuloOuEmBranco() throws Exception {
        String hash = "   ";

        mockMvc.perform(delete("/viagens/" + hash))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarNotFoundQuandoViagemNaoForEncontrada() throws Exception {
        String hash = "hashInexistente";

        Mockito.doThrow(new IllegalArgumentException("Viagem não encontrada"))
                .when(viagemService).removerViagemPorHash(hash);

        mockMvc.perform(delete("/viagens/" + hash))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarErroInternoQuandoOcorrerExcecao() throws Exception {
        String hash = "hashComErro";

        Mockito.doThrow(new RuntimeException("Erro interno"))
                .when(viagemService).removerViagemPorHash(hash);

        mockMvc.perform(delete("/viagens/" + hash))
                .andExpect(status().isInternalServerError());
    }



}
