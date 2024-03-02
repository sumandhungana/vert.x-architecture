package demo.architecture.adapter;

import com.google.inject.AbstractModule;
import demo.architecture.adapter.repository.PostgresClientProvider;
import demo.architecture.adapter.repository.postgres.FooRepositoryImpl;
import demo.architecture.adapter.service.FooService;
import demo.architecture.adapter.service.impl.FooServiceImpl;
import demo.architecture.core.ports.FooRepository;
import io.vertx.core.Vertx;
import io.vertx.sqlclient.SqlClient;

public class AdapterModule extends AbstractModule {

  private final Vertx vertx;

  public AdapterModule(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  protected void configure() {
    //register sql client
    bind(SqlClient.class).toProvider(new PostgresClientProvider(vertx)).asEagerSingleton();

    bind(FooService.class).to(FooServiceImpl.class);

    bind(FooRepository.class).to(FooRepositoryImpl.class);
  }
}
