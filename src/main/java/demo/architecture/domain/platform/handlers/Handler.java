package demo.architecture.domain.platform.handlers;

import demo.architecture.domain.platform.usecases.UseCase;
import demo.architecture.domain.platform.usecases.UseCaseRequest;
import demo.architecture.domain.platform.usecases.UseCaseResponse;
import io.vertx.core.Future;

public abstract class Handler<T extends UseCaseRequest, R extends UseCaseResponse> {
  public Future<R> result(T request, UseCase<T, R> useCase) {
    return Future.succeededFuture(useCase.execute(request));
  }
}
