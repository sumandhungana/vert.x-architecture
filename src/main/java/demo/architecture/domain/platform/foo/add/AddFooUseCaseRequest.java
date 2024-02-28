package demo.architecture.domain.platform.foo.add;

import demo.architecture.domain.platform.usecases.UseCaseRequest;

public record AddFooUseCaseRequest(String id,
                                   String name) implements UseCaseRequest {
}
