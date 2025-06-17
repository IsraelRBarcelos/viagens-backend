package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.repository.ViagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ViagemServiceImplTest {

    private ViagemRepository viagemRepository;
    private ViagemServiceImpl viagemService;

    @BeforeEach
    void setUp() {
        viagemRepository = Mockito.mock(ViagemRepository.class);
        viagemService = new ViagemServiceImpl();
        viagemService.viagemRepository = viagemRepository;
    }

    @Test
    void deveRecuperarViagemPorHashComSucesso() {
        String hash = "abc123";
        Viagem viagem = new Viagem();
        when(viagemRepository.recuperarViagemPorHash(hash)).thenReturn(viagem);

        Viagem resultado = viagemService.recuperarViagemPorHash(hash);

        assertNotNull(resultado);
        verify(viagemRepository).recuperarViagemPorHash(hash);
    }

    @Test
    void deveLancarExcecaoQuandoHashForInvalido() {
        String hash = "invalido";
        when(viagemRepository.recuperarViagemPorHash(hash)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> viagemService.recuperarViagemPorHash(hash));

        assertEquals("hash invalido", exception.getMessage());
        verify(viagemRepository).recuperarViagemPorHash(hash);
    }

    @Test
    void deveSalvarViagemComSucesso() {
        Viagem viagem = new Viagem();

        viagemService.salvarViagem(viagem);

        verify(viagemRepository).salvarViagem(viagem);
    }

    @Test
    void deveRemoverViagemPorHashComSucesso() {
        String hash = "abc123";

        viagemService.removerViagemPorHash(hash);

        verify(viagemRepository).removerViagemPorHash(hash);
    }
}
