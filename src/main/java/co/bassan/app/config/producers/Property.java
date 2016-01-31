package co.bassan.app.config.producers;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by pbastidas on 1/29/16.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
    @Nonbinding
    String value() default "";
}
