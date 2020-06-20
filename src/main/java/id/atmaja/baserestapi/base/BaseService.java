package id.atmaja.baserestapi.base;

import id.atmaja.baserestapi.component.JpaEntityManagerFactory;
import id.atmaja.baserestapi.rest.example.Table1;
import id.atmaja.baserestapi.rest.example.Table2;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service public class BaseService {

    private EntityManagerFactory entityManagerFactory;

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
