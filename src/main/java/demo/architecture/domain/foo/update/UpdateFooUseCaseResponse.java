package demo.architecture.domain.foo.update;

import demo.architecture.domain.platform.usecases.UseCaseResponse;

public record UpdateFooUseCaseResponse(String id) implements UseCaseResponse {
}
