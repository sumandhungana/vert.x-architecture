package demo.architecture.core.platform.usecases;

public interface UseCase<R extends UseCaseRequest, T extends UseCaseResponse> {
  T execute(R request);
}
