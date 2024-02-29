package demo.architecture.domain.foo.add;

import demo.architecture.domain.platform.usecases.UseCaseResponse;

public record AddFooUseCaseResponse(String id) implements UseCaseResponse {
}
