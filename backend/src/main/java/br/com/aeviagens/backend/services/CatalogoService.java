package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Catalogo;
import br.com.aeviagens.backend.domain.Viagem;
import br.com.aeviagens.backend.endpoints.dto.AdicionarViagemAoCatalogoRequestDTO;
import br.com.aeviagens.backend.endpoints.dto.CriarCatalogoRequestDTO;
import br.com.aeviagens.backend.exceptions.DomainException;

public interface CatalogoService {
    Catalogo recuperarCatalogoPorHash(String hash, Long idParticipante) throws DomainException;

    Catalogo criarCatalogo(CriarCatalogoRequestDTO criarCatalogoRequestDTO) throws DomainException;

    Viagem adicionarViagemAoCatalogo(AdicionarViagemAoCatalogoRequestDTO adicionarViagemAoCatalogoRequestDTO) throws DomainException;

    Catalogo verificarExistenciaDeCatalogo(String hash) throws DomainException;
}
