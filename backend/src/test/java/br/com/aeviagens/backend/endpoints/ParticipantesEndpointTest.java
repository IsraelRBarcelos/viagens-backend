package br.com.aeviagens.backend.endpoints;

import br.com.aeviagens.backend.domain.Local;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.endpoints.dto.DadosParticipanteDTO;
import br.com.aeviagens.backend.fixtures.DadosDeLocalizacaoFixture;
import br.com.aeviagens.backend.fixtures.DadosDoCartaoFixture;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import br.com.aeviagens.backend.repository.jpa.impl.ParticipanteRepositoryImpl;
import br.com.aeviagens.backend.services.ParticipanteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParticipantesEndpoint.class)
@AutoConfigureMockMvc(addFilters = false)
public class ParticipantesEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ParticipanteServiceImpl participanteService;

    @Mock
    private ViagemJPARepository viagemJPARepository;

    @MockBean
    private ParticipanteRepository participanteRepository;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public ParticipanteServiceImpl participanteService() {
            return Mockito.mock(ParticipanteServiceImpl.class);
        }
    }


    @Test
    void deveAdicionarParticipanteComSucesso() throws Exception {
        // Arrange
        DadosDeLocalizacaoFixture dadosDeLocalizacaoFixture = new DadosDeLocalizacaoFixture();
        DadosDoCartaoFixture dadosDoCartaoFixture = new DadosDoCartaoFixture();

        AdicionarParticipanteRequestDTO request = AdicionarParticipanteRequestDTO.builder()
                .hash("hash")
                .dadosParticipante(DadosParticipanteDTO.builder()
                        .dadosDoCartao(dadosDoCartaoFixture.recuperarDados())
                        .nome("ex")
                        .cpf("cpf")
                        .dadosDeLocalizacao(dadosDeLocalizacaoFixture.recuperarDados())
                        .build())
                .build();

        Viagem viagemMock = Viagem.builder()
                .localDeChegada(Local.CANOAS)
                .localDePartida(Local.FLORIANOPOLIS)
                .valorEstimado(BigDecimal.valueOf(500))
                .horaDoInicioDaViagem("2233")
                .numeroMaximoDePassageiros(5)
                .dataInicioViagem("26082006")
                .participantes(List.of())
                .build();

        // Mockar o comportamento do serviço
        Mockito.when(participanteService.adicionarParticipanteAViagem(Mockito.any()))
                .thenReturn(viagemMock);

        // Act + Assert
        mockMvc.perform(post("/participantes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
