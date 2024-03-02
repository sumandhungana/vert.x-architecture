package demo.architecture.core.platform.handlers;

import demo.architecture.core.platform.usecases.UseCaseResponse;
import demo.architecture.core.platform.usecases.UseCase;
import demo.architecture.core.platform.usecases.UseCaseRequest;
import io.vertx.core.Future;
import jakarta.inject.Singleton;

@Singleton
public class BaseHandler extends Handler<UseCaseRequest, UseCaseResponse> {
  public Future<UseCaseResponse> handle(UseCaseRequest request, UseCase<UseCaseRequest, UseCaseResponse> useCase) {
    return result(request, useCase);
  }
}
