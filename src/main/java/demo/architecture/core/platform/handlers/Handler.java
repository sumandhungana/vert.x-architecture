package demo.architecture.core.platform.handlers;

import demo.architecture.core.platform.usecases.UseCase;
import demo.architecture.core.platform.usecases.UseCaseRequest;
import demo.architecture.core.platform.usecases.UseCaseResponse;
import io.vertx.core.Future;

public abstract class Handler<T extends UseCaseRequest, R extends UseCaseResponse> {
  public Future<R> result(T request, UseCase<T, R> useCase) {
    return Future.succeededFuture(useCase.execute(request));
  }
}
