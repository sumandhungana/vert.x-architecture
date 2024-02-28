package demo.architecture.application;

import com.google.inject.Injector;

/**
 * @author suman dhungana
 */
public class GuiceInjectorHolder {

    private GuiceInjectorHolder() {
    }

    private static Injector injector;

    public static Injector getInjector() {
        return injector;
    }

    public static void setInjector(Injector injector) {
        GuiceInjectorHolder.injector = injector;
    }
}
