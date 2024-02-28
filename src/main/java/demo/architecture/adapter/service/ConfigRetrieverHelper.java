package demo.architecture.adapter.service;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;

public class ConfigRetrieverHelper {
  private ConfigRetrieverHelper() {
  }

  private static final Logger LOG = LoggerFactory.getLogger(ConfigRetrieverHelper.class);
  private static JsonObject configuration;

  public static JsonObject getConfigRetriever() {
    return configuration;
  }

  public static Future<Void> init(Vertx vertx) {
    var promise = Promise.<Void>promise();
    ConfigStoreOptions env = new ConfigStoreOptions()
      .setType("env");
    ConfigRetrieverOptions configRetrieverOptions = new ConfigRetrieverOptions()
      .setScanPeriod(5000)
      .addStore(env);
    ConfigRetriever configRetriever = ConfigRetriever.create(vertx, configRetrieverOptions);
    configRetriever.getConfig(config -> {
      if (config.succeeded()) {
        configuration = config.result();
        promise.complete();
      } else {
        promise.fail(config.cause());
        LOG.error("Unable to configure.", config.cause());
      }
    });
    return promise.future();
  }
}
