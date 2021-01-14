package home.todo_list.service;

import home.todo_list.dto.TaskDTO;

import java.util.Map;

public interface TodoTaskService {

    Map<String, TaskDTO> getAllTodoTasksByUserName(String userName);

    boolean deleteTask(String taskID, String username);

    boolean deleteAllTasks(String username);

    TaskDTO createNewTask(TaskDTO taskDTO, String username);

    TaskDTO updateTask(TaskDTO taskDTO, String username);
}
