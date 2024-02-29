package demo.architecture.domain.foo.update;

import demo.architecture.domain.platform.usecases.UseCaseRequest;

public record UpdateFooUseCaseRequest(String id, String name) implements UseCaseRequest {
}
