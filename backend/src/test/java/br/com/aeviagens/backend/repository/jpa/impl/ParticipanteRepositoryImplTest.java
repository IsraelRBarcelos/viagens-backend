package br.com.aeviagens.backend.repository.jpa.impl;

import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParticipanteRepositoryImplTest {

    private ViagemJPARepository viagemJPARepository;
    private ParticipanteRepositoryImpl participanteRepositoryImpl;

    @BeforeEach
    void setUp() {
        viagemJPARepository = mock(ViagemJPARepository.class);
        participanteRepositoryImpl = new ParticipanteRepositoryImpl(viagemJPARepository);
    }

    @Test
    void deveAdicionarParticipanteQuandoHashForValida() {
        String hash = "abc123";
        Participante participante = Participante.builder()
                .cpf("1234")
                .build();

        Viagem viagem = Viagem.builder()
                .participantes(new ArrayList<Participante>())
                .numeroMaximoDePassageiros(4)
                .build();
        viagem.getParticipantes().add(participante);

        when(viagemJPARepository.findByHash(hash)).thenReturn(Optional.of(viagem));
        when(viagemJPARepository.save(any(Viagem.class))).thenAnswer(i -> i.getArgument(0));

        Viagem resultado = participanteRepositoryImpl.adicionarParticipanteEmUmaViagem(hash, participante);

        assertNotNull(resultado);
        assertTrue(resultado.getParticipantes().contains(participante));

        verify(viagemJPARepository).save(viagem);
    }

    @Test
    void deveLancarExcecaoQuandoHashForInvalida() {
        // Arrange
        String hashInvalido = "hash_invalida";
        Participante participante = new Participante();

        when(viagemJPARepository.findByHash(hashInvalido)).thenReturn(Optional.empty());

        // Act + Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                participanteRepositoryImpl.adicionarParticipanteEmUmaViagem(hashInvalido, participante)
        );

        assertEquals("hash inválida", exception.getMessage());
    }
}
