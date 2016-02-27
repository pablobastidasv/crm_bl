package co.bassan.app.config.producers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Collections;

/**
 * Singleton EJB to have only one mongo client for the app
 * to avoid multiple connections.
 *
 * @author pbastidas
 */
@Singleton
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MongoClientProducer {

    @Inject
    @Property(value = "MONGO_URL")
    private String urlEnvNameProperty;

    @Inject
    @Property(value = "MONGO_HOST")
    private String hostEnvNameProperty;

    @Inject
    @Property(value = "MONGO_PORT")
    private String portEnvNameProperty;

    @Inject
    @Property(value = "MONGO_USERNAME")
    private String userEnvNameProperty;

    @Inject
    @Property(value = "MONGO_PASSWORD")
    private String passEnvNameProperty;

    @Inject
    @Property(value = "DOCKER_APP_NAME")
    private String appNameEnvNameProperty;

    private MongoClient mongoClient;

    @Produces
    @Property
    @Lock(LockType.READ)
    public MongoClient getMongoClient() {
        return mongoClient;
    }

    @PostConstruct
    public void init(){
        String host = System.getenv(hostEnvNameProperty);
        Integer port = Integer.valueOf(System.getenv(portEnvNameProperty));
        String user = System.getenv(userEnvNameProperty);
        String pass = System.getenv(passEnvNameProperty);
        String url = System.getenv(urlEnvNameProperty);
        String appName = System.getenv(appNameEnvNameProperty);

        if(StringUtils.isNotEmpty(url)){
            mongoClient = new MongoClient(new MongoClientURI(url));
        } else if(StringUtils.isNotEmpty(user)){
            final MongoCredential credential = MongoCredential.createMongoCRCredential(user,
                    appName,
                    pass.toCharArray());

            final ServerAddress server = new ServerAddress(host, port);

            mongoClient = new MongoClient(server, Collections.singletonList(credential));
        } else{
            mongoClient = new MongoClient(host, port);
        }
    }

}
