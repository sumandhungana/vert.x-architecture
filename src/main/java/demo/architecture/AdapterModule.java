package demo.architecture;

import com.google.inject.AbstractModule;
import demo.architecture.adapter.service.FooService;
import demo.architecture.adapter.service.impl.FooServiceImpl;
import io.vertx.core.Vertx;

public class AdapterModule extends AbstractModule {

  private final Vertx vertx;

  public AdapterModule(Vertx vertx) {
    this.vertx = vertx;
  }

  @Override
  protected void configure() {
    bind(FooService.class).to(FooServiceImpl.class);
  }
}
