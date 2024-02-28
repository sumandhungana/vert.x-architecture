package demo.architecture.domain.platform.usecases;

import demo.architecture.domain.platform.handlers.HandlerRequest;
import demo.architecture.domain.platform.handlers.HandlerResponse;

public interface UseCase<R extends UseCaseRequest, T extends UseCaseResponse> {
  T execute(R request);
}
