package demo.architecture.application.handler;

import demo.architecture.adapter.service.FooService;
import demo.architecture.adapter.service.requestpayload.AddFooRequestPayload;
import demo.architecture.application.ResponseUtil;
import demo.architecture.application.RestResponse;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;

public class AddFooHandler implements Handler<RoutingContext> {

  private final FooService fooService;

  @Inject
  public AddFooHandler(FooService fooService) {
    this.fooService = fooService;
  }

  @Override
  public void handle(RoutingContext routingContext) {
    var response = this.fooService.addFoo(this.prepareRequestPayload(routingContext.body().asJsonObject()));
    if (response.failed()) {
      ResponseUtil.internalError(routingContext, RestResponse.error(response.cause().getMessage()));
    } else if (response.succeeded()) {
      ResponseUtil.ok(routingContext, RestResponse.ok(response.result()));
    }
  }

  private AddFooRequestPayload prepareRequestPayload(JsonObject jsonObject) {
    if (jsonObject == null || jsonObject.isEmpty()) {
      return new AddFooRequestPayload("", "");
    }
    return new AddFooRequestPayload(jsonObject.getString("id"), jsonObject.getString("name"));
  }
}
