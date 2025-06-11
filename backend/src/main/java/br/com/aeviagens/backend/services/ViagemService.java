package br.com.aeviagens.backend.services;

import br.com.aeviagens.backend.domain.Viagem;

public interface ViagemService {

    public Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException;
    public void salvarViagem(Viagem viagem);
    public void removerViagemPorHash(String hash);
}