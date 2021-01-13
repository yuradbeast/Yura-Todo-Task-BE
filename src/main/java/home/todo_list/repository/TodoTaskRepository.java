package home.todo_list.repository;

import home.todo_list.model.TodoTask;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoTaskRepository extends MongoRepository<TodoTask,String> {

    List<TodoTask> findAllByUsername(String username);


    Long deleteAllByUsername(String username);
//
//    List<Todo> findByQuoteContainsAllIgnoreCase(String author);

}
