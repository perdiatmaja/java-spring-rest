package id.atmaja.baserestapi.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service public class BaseService {

    @Autowired private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        closeEntityManager();
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    protected void closeEntityManager() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
            entityManager = null;
        }
    }

}
