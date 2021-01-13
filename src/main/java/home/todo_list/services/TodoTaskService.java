package home.todo_list.services;


import home.todo_list.dto.TaskDTO;
import home.todo_list.model.TodoTask;
import home.todo_list.repository.TodoTaskRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoTaskService {

    final TodoTaskRepository todoTaskRepository;

    public TodoTaskService(TodoTaskRepository todoTaskRepository) {
        this.todoTaskRepository = todoTaskRepository;
    }

    public Map<String, TaskDTO> getAllTodoTasksByUserName(String userName) {
        return todoTaskRepository.findAllByUsername(userName)
                .stream()
                .collect(Collectors.toMap(TodoTask::getId, TaskDTO::new));
    }


    public boolean deleteTask(String taskID, String username) {
        TodoTask todoTaskFromDB = getTaskIfItBelongToUserOrThrowException(taskID, username);
        todoTaskRepository.deleteById(todoTaskFromDB.getId());
        return true;
    }

    public boolean deleteAllTasks(String username) {
        todoTaskRepository.deleteAllByUsername(username);
        return true;
    }

    public TaskDTO createNewTask(TaskDTO taskDTO, String username) {
        TodoTask todoTask = TodoTask.builder()
                .username(username)
                .date(taskDTO.getDate())
                .modified(taskDTO.getModified())
                .done(taskDTO.isDone())
                .title(taskDTO.getTitle())
                .build();

        TodoTask created = todoTaskRepository.save(todoTask);

        return new TaskDTO(created);
    }

    public TaskDTO updateTask(TaskDTO taskDTO, String username) {

        TodoTask todoTaskFromDB = getTaskIfItBelongToUserOrThrowException(taskDTO.getId(), username);

        TodoTask todoTaskToPersist = TodoTask.builder()
                .date(taskDTO.getDate())
                .modified(taskDTO.getModified())
                .done(taskDTO.isDone())
                .title(taskDTO.getTitle())
                .id(todoTaskFromDB.getId())
                .username(username)
                .build();

        if (todoTaskToPersist.equals(todoTaskFromDB)) {
            throw new RuntimeException("No changes for modification found");
        }

        TodoTask createdTask = todoTaskRepository.save(todoTaskToPersist);
        return new TaskDTO(createdTask);

    }

    private TodoTask getTaskIfItBelongToUserOrThrowException(String taskId, String username) {
        Optional<TodoTask> todoTaskOptional = todoTaskRepository.findById(taskId);
        if (!todoTaskOptional.isPresent() || !todoTaskOptional.get().getUsername().equals(username)) {
            throw new RuntimeException("Task was not found or does not belong to you");
        }
        return todoTaskOptional.get();
    }

}
