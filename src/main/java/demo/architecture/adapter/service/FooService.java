package demo.architecture.adapter.service;

import demo.architecture.domain.platform.foo.add.AddFooUseCaseResponse;
import demo.architecture.domain.platform.usecases.UseCaseResponse;
import io.vertx.core.Future;


public interface FooService {
  Future<UseCaseResponse> addFoo();
}
