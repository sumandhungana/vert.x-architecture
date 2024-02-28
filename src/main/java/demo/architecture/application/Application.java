package demo.architecture.application;

import com.google.inject.Guice;
import demo.architecture.AdapterModule;
import demo.architecture.adapter.service.ConfigRetrieverHelper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

import java.util.logging.Logger;

public class Application extends AbstractVerticle {

  Logger logger = Logger.getLogger(Application.class.getName());

  @Override
  public void start(Promise<Void> startPromise) {
    try {
      ConfigRetrieverHelper.init(vertx)
        .onSuccess(success -> {
          this.setupDependencies();
          vertx.deployVerticle(new RestServer());
        })
        .onFailure(failedInitConfig -> logger.info("Failed to init config file::>>" + failedInitConfig.getMessage()));
    } catch (Exception e) {
      logger.info("Exception::>>" + e.getMessage());
    }
  }

  private void setupDependencies() {
    var injector = Guice.createInjector(new AdapterModule(vertx));
    GuiceInjectorHolder.setInjector(injector);
  }
}
