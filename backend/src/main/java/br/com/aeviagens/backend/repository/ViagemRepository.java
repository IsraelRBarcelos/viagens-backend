package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViagemRepository extends JpaRepository<Viagem, Long>, ViagemRepositoryCustom {
    public Viagem recuperarViagemPorHash(String hash);
}
