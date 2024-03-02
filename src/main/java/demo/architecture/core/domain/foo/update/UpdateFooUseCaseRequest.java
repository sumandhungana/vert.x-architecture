package demo.architecture.core.domain.foo.update;

import demo.architecture.core.platform.usecases.UseCaseRequest;

public record UpdateFooUseCaseRequest(String id, String name) implements UseCaseRequest {
}
