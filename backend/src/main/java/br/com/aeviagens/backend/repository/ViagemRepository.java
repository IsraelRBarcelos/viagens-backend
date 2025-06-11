package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, Long>, ViagemRepositoryCustom {
    Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException;
    void salvarViagem(Viagem viagem);
    void removerViagemPorHash(String hash);
}
