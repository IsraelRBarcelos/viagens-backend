package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.*;
import br.com.aeviagens.backend.endpoints.dto.AdicionarParticipanteRequestDTO;
import br.com.aeviagens.backend.endpoints.dto.DadosParticipanteDTO;
import br.com.aeviagens.backend.repository.ParticipanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParticipanteServiceImplTest {

    @InjectMocks
    private ParticipanteServiceImpl participanteService;

    @Mock
    private ParticipanteRepository participanteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveAdicionarParticipanteAViagemComSucesso() {
        // Arrange
        String hash = "abc123";
        DadosParticipanteDTO participanteRequestDTO = new DadosParticipanteDTO();
        participanteRequestDTO.setCpf("323232");
        participanteRequestDTO.setNome("João da Silva");
        participanteRequestDTO.setDadosDoCartao(DadosDoCartao.builder()
                        .numeroDoCartao(23232L)
                        .nomeTitular("321321321")
                        .dataValidade("232023")
                        .bandeira(Bandeira.ELO)
                        .cvv(123)
                .build());
        participanteRequestDTO.setDadosDeLocalizacao(DadosDeLocalizacao.builder()
                        .estado("x")
                        .pais("x")
                        .cidade("x")
                        .bairro("x")
                        .rua("x")
                        .cep(123321321L)
                        .numero(123)
                .build());

        AdicionarParticipanteRequestDTO requestDTO = new AdicionarParticipanteRequestDTO();
        requestDTO.setHash(hash);
        requestDTO.setDadosParticipante(participanteRequestDTO);

        Participante participante = new Participante();
        participante.setNome("João da Silva");

        Viagem viagemEsperada = new Viagem();

        when(participanteRepository.adicionarParticipanteEmUmaViagem(eq(hash), any(Participante.class)))
                .thenReturn(viagemEsperada);

        // Act
        Viagem viagemRetornada = participanteService.adicionarParticipanteAViagem(requestDTO);

        // Assert
        assertNotNull(viagemRetornada);
        assertEquals(viagemEsperada, viagemRetornada);
        verify(participanteRepository, times(1)).adicionarParticipanteEmUmaViagem(eq(hash), any(Participante.class));
    }
}
