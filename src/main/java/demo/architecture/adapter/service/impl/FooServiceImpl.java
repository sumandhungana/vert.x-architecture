package demo.architecture.adapter.service.impl;

import demo.architecture.adapter.service.FooService;
import demo.architecture.adapter.service.requestpayload.AddFooRequestPayload;
import demo.architecture.adapter.service.requestpayload.UpdateFooRequestPayload;
import demo.architecture.adapter.service.response.AddFooResponse;
import demo.architecture.adapter.service.response.UpdateFooResponse;
import demo.architecture.domain.foo.add.AddFooUseCase;
import demo.architecture.domain.foo.add.AddFooUseCaseRequest;
import demo.architecture.domain.foo.add.AddFooUseCaseResponse;
import demo.architecture.domain.foo.update.UpdateFooUseCase;
import demo.architecture.domain.foo.update.UpdateFooUseCaseRequest;
import demo.architecture.domain.foo.update.UpdateFooUseCaseResponse;
import demo.architecture.domain.platform.handlers.BaseHandler;
import demo.architecture.domain.platform.usecases.UseCaseRequest;
import demo.architecture.domain.platform.usecases.UseCaseResponse;
import io.vertx.core.Future;
import jakarta.inject.Inject;

public record FooServiceImpl(BaseHandler baseHandler) implements FooService {
  @Inject
  public FooServiceImpl {

  }

  @Override
  public Future<AddFooResponse> addFoo(AddFooRequestPayload payload) {
    Future<UseCaseResponse> useCaseResponseFuture = this.baseHandler.handle(new AddFooUseCaseRequest(payload.id(), payload.name()), this::executeAddFooUseCase);
    return useCaseResponseFuture.map(useCaseResponse -> {
      AddFooUseCaseResponse addFooUseCaseResponse = (AddFooUseCaseResponse) useCaseResponse;
      return new AddFooResponse(addFooUseCaseResponse.id());
    });
  }

  @Override
  public Future<UpdateFooResponse> updateFoo(UpdateFooRequestPayload payload) {
    Future<UseCaseResponse> useCaseResponseFuture = this.baseHandler.handle(new AddFooUseCaseRequest(payload.id(), payload.name()), this::executeUpdateFooUseCase);
    return useCaseResponseFuture.map(useCaseResponse -> {
      UpdateFooUseCaseResponse updateFooResponse = (UpdateFooUseCaseResponse) useCaseResponse;
      return new UpdateFooResponse(updateFooResponse.id());
    });
  }

  private UseCaseResponse executeAddFooUseCase(UseCaseRequest useCaseRequest) {
    return new AddFooUseCase().execute((AddFooUseCaseRequest) useCaseRequest);
  }

  private UseCaseResponse executeUpdateFooUseCase(UseCaseRequest useCaseRequest) {
    return new UpdateFooUseCase().execute((UpdateFooUseCaseRequest) useCaseRequest);
  }
}
