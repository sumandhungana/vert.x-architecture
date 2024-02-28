package demo.architecture.domain.platform.foo.add;

import demo.architecture.domain.platform.usecases.UseCase;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class AddFooUseCase implements UseCase<AddFooUseCaseRequest, AddFooUseCaseResponse> {
  @Override
  public AddFooUseCaseResponse execute(AddFooUseCaseRequest request) {
    this.validateRequest(request);
    var value = this.saveIntoArrayList(request);
    return new AddFooUseCaseResponse(value.getFirst());
  }

  private void validateRequest(AddFooUseCaseRequest request) {
    if (request.id() == null || request.id().isBlank())
      throw new IllegalArgumentException("id is required");

    if (request.name() == null || request.name().isBlank())
      throw new IllegalArgumentException("name is required");
  }

  private List<String> saveIntoArrayList(AddFooUseCaseRequest request) {
    List<String> saveValue = new ArrayList<>();
    saveValue.add(request.id());
    return saveValue;
  }
}
