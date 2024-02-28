package demo.architecture.domain.platform.foo.add;

import demo.architecture.domain.platform.usecases.UseCaseResponse;

public record AddFooUseCaseResponse(String id) implements UseCaseResponse {
}
