package home.todo_list.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import home.todo_list.model.TodoTask;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.Instant;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TaskDTO {

    private String title;
    private Instant date;
    private Instant modified;
    private boolean done;
    private String id;

    public TaskDTO(TodoTask todoTask) {
        this.title = todoTask.getTitle();
        this.date = todoTask.getDate();
        this.modified = todoTask.getModified();
        this.done = todoTask.isDone();
        this.id = todoTask.getId();
    }
}
