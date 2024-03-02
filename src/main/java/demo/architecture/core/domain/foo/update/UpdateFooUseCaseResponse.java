package demo.architecture.core.domain.foo.update;

import demo.architecture.core.platform.usecases.UseCaseResponse;

public record UpdateFooUseCaseResponse(String id) implements UseCaseResponse {
}
