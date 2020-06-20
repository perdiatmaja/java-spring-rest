package id.atmaja.baserestapi.base;

import id.atmaja.baserestapi.component.JpaEntityManagerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service public class BaseService {

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        closeEntityManager();
        entityManager = new JpaEntityManagerFactory().getEntityManager();
        return entityManager;
    }

    protected void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
            entityManager = null;
        }
    }

}
