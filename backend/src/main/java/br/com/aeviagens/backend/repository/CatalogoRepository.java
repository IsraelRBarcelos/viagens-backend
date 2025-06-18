package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Participante;
import br.com.aeviagens.backend.domain.Viagem;

import java.util.Optional;

public interface CatalogoRepository {
    Optional<Catalogo> recuperarCatalogo(String hash);

    Catalogo criarCatalogo(String titulo, String descricao, Participante host);

    Viagem adicionarViagemAoCatalogo(Catalogo catalogo, Viagem novaViagem);
}
