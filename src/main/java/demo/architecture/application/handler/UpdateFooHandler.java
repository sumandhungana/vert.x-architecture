package demo.architecture.application.handler;

import demo.architecture.adapter.service.FooService;
import demo.architecture.adapter.service.requestpayload.UpdateFooRequestPayload;
import demo.architecture.application.ResponseUtil;
import demo.architecture.application.RestResponse;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import jakarta.inject.Inject;

public class UpdateFooHandler implements Handler<RoutingContext> {
  private final FooService fooService;

  @Inject
  public UpdateFooHandler(FooService fooService) {
    this.fooService = fooService;
  }

  @Override
  public void handle(RoutingContext routingContext) {
    var response = this.fooService.updateFoo(this.prepareRequestPayload(routingContext.body().asJsonObject()));
    if (response.failed()) {
      ResponseUtil.internalError(routingContext, RestResponse.error(response.cause().getMessage()));
    } else if (response.succeeded()) {
      ResponseUtil.ok(routingContext, RestResponse.ok(response.result()));
    }
  }

  private UpdateFooRequestPayload prepareRequestPayload(JsonObject jsonObject) {
    if (jsonObject == null || jsonObject.isEmpty()) {
      return new UpdateFooRequestPayload("", "");
    }
    return new UpdateFooRequestPayload(jsonObject.getString("id"), jsonObject.getString("name"));
  }
}
