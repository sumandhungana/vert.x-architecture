package demo.architecture.core.ports;

import demo.architecture.core.ports.entities.FooEntity;

public interface FooRepository {
  void save(FooEntity entity);
}
