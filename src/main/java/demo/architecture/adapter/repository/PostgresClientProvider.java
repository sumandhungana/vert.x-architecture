package demo.architecture.adapter.repository;

import com.google.inject.Provider;
import demo.architecture.adapter.service.ConfigRetrieverHelper;
import io.vertx.core.Vertx;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.sqlclient.Pool;
import io.vertx.sqlclient.PoolOptions;
import io.vertx.sqlclient.SqlClient;

import java.util.logging.Logger;

public class PostgresClientProvider implements Provider<SqlClient> {
  private final Vertx vertx;
  private final Logger logger = Logger.getLogger(PostgresClientProvider.class.getName());

  public PostgresClientProvider(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  public SqlClient get() {
    var config = ConfigRetrieverHelper.getConfigRetriever();
    PgConnectOptions connectOptions = new PgConnectOptions()
      //fetch all from environment variable
      .setPort(config.getInteger("DB_PORT"))
      .setHost(config.getString("DB_HOST"))
      .setDatabase(config.getString("DB_NAME"))
      .setUser(config.getString("DB_USER"))
      .setPassword(config.getString("DB_PASSWORD"));
    PoolOptions poolOptions = new PoolOptions()
      .setShared(true)
      .setPoolCleanerPeriod(10000)
      .setMaxSize(12);
    logger.info("Postgres client initialize successfully");

    return Pool.pool(vertx, connectOptions, poolOptions);
  }
}
