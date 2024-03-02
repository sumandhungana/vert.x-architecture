package demo.architecture.domain.platform.handlers;

import demo.architecture.domain.platform.usecases.UseCase;
import demo.architecture.domain.platform.usecases.UseCaseRequest;
import demo.architecture.domain.platform.usecases.UseCaseResponse;
import io.vertx.core.Future;
import jakarta.inject.Singleton;

@Singleton
public class BaseHandler extends Handler<UseCaseRequest, UseCaseResponse> {
  public Future<UseCaseResponse> handle(UseCaseRequest request, UseCase<UseCaseRequest, UseCaseResponse> useCase) {
    return result(request, useCase);
  }
}
