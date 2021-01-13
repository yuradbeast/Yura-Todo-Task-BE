package home.todo_list.controller;

import home.todo_list.dto.TaskDTO;
import home.todo_list.repository.TodoTaskRepository;
import home.todo_list.model.TodoTask;
import home.todo_list.services.TodoTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/todoTasks")
public class TodoTasksController {

    private final TodoTaskService todoTaskService;

    public TodoTasksController(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    @GetMapping("/allTasks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String ,TaskDTO>> getUsersTodoTasks(Principal principal) {
        Map<String ,TaskDTO> allTodoTasksByUserName = todoTaskService.getAllTodoTasksByUserName(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(allTodoTasksByUserName);
    }

    @PostMapping("/createTask")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> createTask(Principal principal, @RequestBody TaskDTO taskDTO) {
        TaskDTO newTask = todoTaskService.createNewTask(taskDTO, principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(newTask);
    }

    @PostMapping("/updateTask")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> updateTask(Principal principal, @RequestBody TaskDTO taskDTO) {
        TaskDTO updatedTask = todoTaskService.updateTask(taskDTO, principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
    }


    @DeleteMapping("/deleteTask")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteTask(Principal principal, @RequestParam String taskId) {
        Boolean isDeleted = todoTaskService.deleteTask(taskId, principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }

    @DeleteMapping("/deleteAllTasks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Boolean> deleteAllTasks(Principal principal) {
        Boolean isDeleted = todoTaskService.deleteAllTasks(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(isDeleted);
    }




}
