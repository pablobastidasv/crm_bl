package co.bassan.app.config.producers;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.commons.lang.StringUtils;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * Created by pbastidas on 1/30/16.
 */
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

    @Produces
    @Property
    public MongoClient getMongoClient(){
        String host = System.getenv(hostEnvNameProperty);
        Integer port = Integer.valueOf(System.getenv(portEnvNameProperty));
        String user = System.getenv(userEnvNameProperty);
        String pass = System.getenv(passEnvNameProperty);
        String url = System.getenv(urlEnvNameProperty);
        String appName = System.getenv(appNameEnvNameProperty);

        if(StringUtils.isNotEmpty(url)){
            return new MongoClient(new MongoClientURI(url));
        } else if(StringUtils.isNotEmpty(user)){
            final MongoCredential credential = MongoCredential.createMongoCRCredential(user,
                    appName,
                    pass.toCharArray());

            final ServerAddress server = new ServerAddress(host, port);

            return new MongoClient(server, Collections.singletonList(credential));
        } else{
            return new MongoClient(host, port);
        }
    }

}
