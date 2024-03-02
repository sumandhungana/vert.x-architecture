package demo.architecture.adapter.repository.postgres;

import demo.architecture.core.ports.FooRepository;
import demo.architecture.core.ports.entities.FooEntity;
import io.vertx.sqlclient.SqlClient;
import io.vertx.sqlclient.Tuple;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FooRepositoryImpl implements FooRepository {

  private final SqlClient sqlClient;
  private final Logger logger = Logger.getLogger(FooRepositoryImpl.class.getName());

  private final String INSERT_INTO_FOO = """
    INSERT INTO foo(id, name)
    VALUES ($1,$2);
    """;

  @Inject
  public FooRepositoryImpl(SqlClient sqlClient) {
    this.sqlClient = sqlClient;
  }

  @Override
  public void save(FooEntity fooEntity) {
    List<Object> insertParameter = new ArrayList<>();
    insertParameter.add(fooEntity.getId());
    insertParameter.add(fooEntity.getName());
    sqlClient.preparedQuery(INSERT_INTO_FOO)
      .execute(Tuple.tuple(insertParameter))
      .onSuccess(successInsert -> {
        logger.info("<<::Successfully insert into transaction details::>>");
      }).onFailure(failedInsert -> {
        logger.info("Failed to insert into transaction details::>>" + failedInsert.getMessage());
      });
  }
}
