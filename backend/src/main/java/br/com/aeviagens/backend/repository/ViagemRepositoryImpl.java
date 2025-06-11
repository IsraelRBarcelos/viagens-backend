package br.com.aeviagens.backend.repository;

import br.com.aeviagens.backend.domain.Viagem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ViagemRepositoryImpl implements ViagemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Viagem recuperarViagemPorHash(String hash) throws IllegalArgumentException {
        List<Viagem> viagem = entityManager.createQuery(
                        "SELECT v FROM Viagem v WHERE v.hash = :hash", Viagem.class)
                .setParameter("hash", hash)
                .getResultList();

        if (!viagem.isEmpty()) {
            return viagem.getFirst();
        }

        throw new IllegalArgumentException("hash invalida");

    }

    @Override
    @Transactional
    public void salvarViagem(Viagem viagem) {
        entityManager.persist(viagem);
    }

    @Override
    public void removerViagemPorHash(String hash) {
        Viagem viagemASerRemovida = this.recuperarViagemPorHash(hash);
        entityManager.remove(viagemASerRemovida);
    }
}
