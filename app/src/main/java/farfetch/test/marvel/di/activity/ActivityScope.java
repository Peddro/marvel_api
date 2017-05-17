package farfetch.test.marvel.di.activity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Pedro Glória on 13/05/2017.
 */

@Scope
@Documented
@Retention(RUNTIME)
public @interface ActivityScope {}
