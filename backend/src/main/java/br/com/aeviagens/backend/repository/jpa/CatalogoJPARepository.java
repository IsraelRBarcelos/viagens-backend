package br.com.aeviagens.backend.repository.jpa;

import br.com.aeviagens.backend.domain.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CatalogoJPARepository extends JpaRepository<Catalogo, Long> {
    Optional<Catalogo> findByHash(String hash);
}
