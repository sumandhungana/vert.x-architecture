package demo.architecture.application;

import demo.architecture.application.handler.AddFooHandler;
import demo.architecture.application.handler.UpdateFooHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.tracing.TracingPolicy;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class RestServer extends AbstractVerticle {
  private final Logger logger = Logger.getLogger(RestServer.class.getName());

  @Override
  public void start(Promise<Void> promise) {
    try {
      vertx.createHttpServer(new HttpServerOptions().setTracingPolicy(TracingPolicy.ALWAYS))

        .requestHandler(this.configureRoute())
        .listen(8082, http -> {
          if (http.succeeded()) {
            logger.info("::: Http server started on port >>> ::: " + http.result().actualPort());
          } else {
            logger.severe("::: Http server failed to start >>> ::: " + http.cause().getMessage());
          }
        });
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
  }

  private Router configureRoute() {
    Router router = Router.router(vertx);
    corsSupport(router);
    router.post()
      .handler(BodyHandler.create());
    router.route("/v1/foo/*")
      .subRouter(fooApi());
    return router;
  }

  private Router fooApi() {
    Router fooApi = Router.router(vertx);
    fooApi.get("/add")
      .handler(GuiceInjectorHolder.getInjector().getInstance(AddFooHandler.class));
    fooApi.get("/updaye")
      .handler(GuiceInjectorHolder.getInjector().getInstance(UpdateFooHandler.class));
    return fooApi;
  }


  private void corsSupport(Router router) {
    Set<String> allowHeaders = new HashSet<>();
    allowHeaders.add("x-requested-with");
    allowHeaders.add("Access-Control-Allow-Origin");
    allowHeaders.add("Access-Control-Allow-Headers");
    allowHeaders.add("origin");
    allowHeaders.add("Content-Type");
    allowHeaders.add("accept");
    Set<HttpMethod> allowMethods = new HashSet<>();
    allowMethods.add(HttpMethod.GET);
    allowMethods.add(HttpMethod.PUT);
    allowMethods.add(HttpMethod.OPTIONS);
    allowMethods.add(HttpMethod.POST);
    allowMethods.add(HttpMethod.DELETE);
    allowMethods.add(HttpMethod.PATCH);

    router.route()
      .handler(CorsHandler.create()
        .allowedHeaders(allowHeaders)
        .allowedMethods(allowMethods));
  }
}
