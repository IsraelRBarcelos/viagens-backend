package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ViagemRepositoryImpl implements ViagemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Viagem recuperarViagemPorHash(String hash) {
        return entityManager.createQuery(
                        "SELECT v FROM Viagem v WHERE v.hash = :hash", Viagem.class)
                .setParameter("hash", hash)
                .getSingleResult();
    }
}
