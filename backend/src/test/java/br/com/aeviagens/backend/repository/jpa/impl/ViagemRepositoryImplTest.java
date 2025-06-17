package br.com.aeviagens.backend.repository.jpa.impl;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.jpa.ViagemJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ViagemRepositoryImplTest {

    private ViagemJPARepository viagemJPARepository;
    private ViagemRepositoryImpl viagemRepositoryImpl;

    @BeforeEach
    void setUp() {
        viagemJPARepository = mock(ViagemJPARepository.class);
        viagemRepositoryImpl = new ViagemRepositoryImpl(viagemJPARepository);
    }

    @Test
    void deveRecuperarViagemPorHashQuandoHashForValida() {
        String hash = "valido";
        Viagem viagem = new Viagem();

        when(viagemJPARepository.findByHash(hash)).thenReturn(Optional.of(viagem));

        Viagem resultado = viagemRepositoryImpl.recuperarViagemPorHash(hash);

        assertNotNull(resultado);
        assertEquals(viagem, resultado);
    }

    @Test
    void deveLancarExcecaoQuandoHashForInvalida() {
        String hash = "invalido";

        when(viagemJPARepository.findByHash(hash)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                viagemRepositoryImpl.recuperarViagemPorHash(hash)
        );

        assertEquals("hash inválida", ex.getMessage());
    }

    @Test
    void deveSalvarViagem() {
        Viagem viagem = new Viagem();

        viagemRepositoryImpl.salvarViagem(viagem);

        verify(viagemJPARepository).save(viagem);
    }

    @Test
    void deveRemoverViagemPorHash() {
        String hash = "valido";
        Viagem viagem = new Viagem();

        when(viagemJPARepository.findByHash(hash)).thenReturn(Optional.of(viagem));

        viagemRepositoryImpl.removerViagemPorHash(hash);

        verify(viagemJPARepository).delete(viagem);
    }
}
