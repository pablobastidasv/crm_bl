package co.bassan.app.config.producers;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by pbastidas on 1/29/16.
 */
@ApplicationScoped
public class PropertiesProducer {

    private Properties environment;

    @PostConstruct
    public void initProperties() {
        environment = new Properties();

        final String propertiesFile = "/attributes.properties";

        final InputStream is = getClass().getResourceAsStream(propertiesFile);

        if(is == null){
            throw new IllegalStateException(String.format("The file %s was not find", propertiesFile));
        }

        try {
            environment.load(is);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    @Produces
    @Property
    public String getString(InjectionPoint injectionPoint){
        final Property annotation = injectionPoint.getAnnotated().getAnnotation(Property.class);

        String key = annotation.value();

        if(Objects.isNull(key) || key.isEmpty()){
            throw new IllegalArgumentException("The value of the property annotation must can not be empty");
        }

        return environment.getProperty(key);
    }

}
