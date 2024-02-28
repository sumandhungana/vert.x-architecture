package demo.architecture.adapter.service.impl;

import demo.architecture.adapter.service.FooService;
import demo.architecture.application.ResponseUtil;
import demo.architecture.domain.platform.foo.add.AddFooUseCase;
import demo.architecture.domain.platform.foo.add.AddFooUseCaseRequest;
import demo.architecture.domain.platform.foo.add.AddFooUseCaseResponse;
import demo.architecture.domain.platform.handlers.BaseHandler;
import demo.architecture.domain.platform.usecases.UseCaseRequest;
import demo.architecture.domain.platform.usecases.UseCaseResponse;
import io.vertx.core.Future;
import jakarta.inject.Inject;

public class FooServiceImpl implements FooService {
  public final BaseHandler baseHandler;

  @Inject
  public FooServiceImpl(BaseHandler baseHandler) {

    this.baseHandler = baseHandler;
  }

  @Override
  public Future<UseCaseResponse> addFoo() {
    return this.baseHandler.handle(new AddFooUseCaseRequest("10", "Suman"), this::executeFooAdapter);

  }

  private UseCaseResponse executeFooAdapter(UseCaseRequest useCaseRequest) {
    return new AddFooUseCase().execute((AddFooUseCaseRequest) useCaseRequest);
  }
}
