package id.atmaja.baserestapi.base;

import id.atmaja.baserestapi.component.JPAEntityManagerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service public class BaseService {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        closeEntityManager();
        entityManager = JPAEntityManagerFactory.getEntityManagerFactory().createEntityManager();
        return entityManager;
    }

    protected void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
            entityManager = null;
        }
    }
}
