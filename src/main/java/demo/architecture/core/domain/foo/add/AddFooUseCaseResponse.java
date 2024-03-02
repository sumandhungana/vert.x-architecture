package demo.architecture.core.domain.foo.add;

import demo.architecture.core.platform.usecases.UseCaseResponse;

public record AddFooUseCaseResponse(String id) implements UseCaseResponse {
}
