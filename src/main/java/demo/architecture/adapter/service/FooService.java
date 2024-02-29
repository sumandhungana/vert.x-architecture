package demo.architecture.adapter.service;

import demo.architecture.adapter.service.requestpayload.AddFooRequestPayload;
import demo.architecture.adapter.service.requestpayload.UpdateFooRequestPayload;
import demo.architecture.adapter.service.response.AddFooResponse;
import demo.architecture.adapter.service.response.UpdateFooResponse;
import io.vertx.core.Future;


public interface FooService {
  Future<AddFooResponse> addFoo(AddFooRequestPayload payload);
  Future<UpdateFooResponse> updateFoo(UpdateFooRequestPayload payload);

}
