package demo.architecture.core.domain.foo.add;

import demo.architecture.core.platform.usecases.UseCaseRequest;

public record AddFooUseCaseRequest(String id,
                                   String name) implements UseCaseRequest {
}
