package demo.architecture.core.domain.foo.add;

import demo.architecture.core.platform.usecases.UseCase;
import demo.architecture.core.ports.FooRepository;
import demo.architecture.core.ports.entities.FooEntity;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class AddFooUseCase implements UseCase<AddFooUseCaseRequest, AddFooUseCaseResponse> {

  private final FooRepository fooRepository;

  @Inject
  public AddFooUseCase(FooRepository fooRepository) {
    this.fooRepository = fooRepository;
  }

  @Override
  public AddFooUseCaseResponse execute(AddFooUseCaseRequest request) {
    this.validateRequest(request);
    var value = this.saveIntoArrayList(request);
    this.fooRepository.save(toEntity(request));
    return new AddFooUseCaseResponse(value.getFirst());
  }

  private FooEntity toEntity(AddFooUseCaseRequest request) {
    FooEntity fooEntity = new FooEntity();
    fooEntity.setId(request.id());
    fooEntity.setName(request.name());
    return fooEntity;
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
