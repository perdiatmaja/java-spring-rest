package id.atmaja.baserestapi.component;

import com.mysql.cj.jdbc.MysqlDataSource;
import id.atmaja.baserestapi.config.EnvConfig;
import id.atmaja.baserestapi.rest.example.Table1;
import id.atmaja.baserestapi.rest.example.Table2;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.jpa.boot.internal.PersistenceUnitInfoDescriptor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class JpaEntityManagerFactory {

    private static final String DB = "jdbc:mysql://" + EnvConfig.DB_HOST + "/" + EnvConfig.DB_NAME;
    private static final String DB_USER_NAME = EnvConfig.DB_USERNAME;
    private static final String DB_PASSWORD = EnvConfig.DB_PASSWORD;
    private Class[] entityClasses;

    public JpaEntityManagerFactory() {
        setEntityClasses();
    }

    private void setEntityClasses() {
        this.entityClasses = new Class[]{Table1.class, Table2.class};
    }

    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    protected EntityManagerFactory getEntityManagerFactory() {
        PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo(
                getClass().getSimpleName());
        Map<String, Object> configuration = new HashMap<>();
        return new EntityManagerFactoryBuilderImpl(
                new PersistenceUnitInfoDescriptor(persistenceUnitInfo), configuration)
                .build();
    }

    protected HibernatePersistenceUnitInfo getPersistenceUnitInfo(String name) {
        return new HibernatePersistenceUnitInfo(name, getEntityClassNames(), getProperties());
    }

    protected List<String> getEntityClassNames() {
        return Arrays.asList(getEntities())
                .stream()
                .map(Class::getName)
                .collect(Collectors.toList());
    }

    protected Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.id.new_generator_mappings", false);
        properties.put("hibernate.connection.datasource", getMysqlDataSource());
        return properties;
    }

    protected Class[] getEntities() {
        return entityClasses;
    }

    protected DataSource getMysqlDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(DB);
        mysqlDataSource.setUser(DB_USER_NAME);
        mysqlDataSource.setPassword(DB_PASSWORD);
        return mysqlDataSource;
    }
}
