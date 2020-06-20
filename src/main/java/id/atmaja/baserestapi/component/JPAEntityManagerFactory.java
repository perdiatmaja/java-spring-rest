package id.atmaja.baserestapi.component;

import com.mysql.cj.jdbc.MysqlDataSource;
import id.atmaja.baserestapi.config.EnvConfig;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;

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

public class JPAEntityManagerFactory {

    private static final String JBDC_URL_KEY = "javax.persistence.jdbc.url";

    private static final String JBDC_URL = "jdbc:mysql://" + EnvConfig.DB_HOST + "/" + EnvConfig.DB_NAME
            + "?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC";

    private static final String JBDC_DIALECT_KEY = "hibernate.dialect";

    private static final String SHOW_SQL_KEY = "hibernate.show_sql";

    private static final String JBDC_USERNAME_KEY = "javax.persistence.jdbc.user";

    private static final String JBDC_PASSWORD_KEY = "javax.persistence.jdbc.password";

    private static final String DATASORUCE_KEY = "hibernate.connection.datasource";

    public static EntityManagerFactory getEntityManagerFactory() {
        return new HibernatePersistenceProvider().createContainerEntityManagerFactory(archiverPersistenceUnitInfo(),
                createMap());
    }

    private static Map<String, Object> createMap() {
        HashMap<String, Object> properties = new HashMap<>();

        properties.put(JBDC_DIALECT_KEY, MySQL5Dialect.class);
        properties.put(SHOW_SQL_KEY, true);
        properties.put(DATASORUCE_KEY, getMysqlDataSource());

        return properties;
    }

    private static PersistenceUnitInfo archiverPersistenceUnitInfo() {
        return new PersistenceUnitInfo() {
            @Override public String getPersistenceUnitName() {
                return "MAIN_UNIT";
            }

            @Override public String getPersistenceProviderClassName() {
                return "org.hibernate.jpa.HibernatePersistenceProvider";
            }

            @Override public PersistenceUnitTransactionType getTransactionType() {
                return PersistenceUnitTransactionType.RESOURCE_LOCAL;
            }

            @Override public DataSource getJtaDataSource() {
                return null;
            }

            @Override public DataSource getNonJtaDataSource() {
                return null;
            }

            @Override public List<String> getMappingFileNames() {
                return Collections.emptyList();
            }

            @Override public List<URL> getJarFileUrls() {
                try {
                    return Collections.list(this.getClass().getClassLoader().getResources(""));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }

            @Override public URL getPersistenceUnitRootUrl() {
                return null;
            }

            @Override public List<String> getManagedClassNames() {
                return Collections.emptyList();
            }

            @Override public boolean excludeUnlistedClasses() {
                return false;
            }

            @Override public SharedCacheMode getSharedCacheMode() {
                return null;
            }

            @Override public ValidationMode getValidationMode() {
                return null;
            }

            @Override public Properties getProperties() {
                return new Properties();
            }

            @Override public String getPersistenceXMLSchemaVersion() {
                return null;
            }

            @Override public ClassLoader getClassLoader() {
                return null;
            }

            @Override public void addTransformer(ClassTransformer transformer) {
            }

            @Override public ClassLoader getNewTempClassLoader() {
                return null;
            }
        };
    }

    private static DataSource getMysqlDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(JBDC_URL);
        mysqlDataSource.setUser(EnvConfig.DB_NAME);
        mysqlDataSource.setPassword(EnvConfig.DB_PASSWORD);

        return mysqlDataSource;
    }

}
