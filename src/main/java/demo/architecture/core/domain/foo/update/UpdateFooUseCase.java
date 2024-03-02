package demo.architecture.core.domain.foo.update;

import demo.architecture.core.platform.usecases.UseCase;

import java.util.ArrayList;
import java.util.List;

public class UpdateFooUseCase implements UseCase<UpdateFooUseCaseRequest, UpdateFooUseCaseResponse> {

  @Override
  public UpdateFooUseCaseResponse execute(UpdateFooUseCaseRequest request) {
    this.validateUpdateRequest(request);
    var value = this.updateIntoArrayList(request);
    return new UpdateFooUseCaseResponse(value.getFirst());
  }

  private void validateUpdateRequest(UpdateFooUseCaseRequest request) {
    if (request.id() == null || request.id().isBlank())
      throw new IllegalArgumentException("id is required");

    if (request.name() == null || request.name().isBlank())
      throw new IllegalArgumentException("name is required");
  }

  private List<String> updateIntoArrayList(UpdateFooUseCaseRequest request) {
    List<String> saveValue = new ArrayList<>();
    saveValue.add(request.id());
    return saveValue;
  }
}
