package home.todo_list.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;


@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Repository
@EqualsAndHashCode
@Document(collection = "TodoData")
public class TodoTask {
    @NotBlank
    @Size(max = 120)
    private String title;
    @NotBlank
    private Instant date;
    @NotBlank
    private Instant modified;
    @NotBlank
    private boolean done;
    @NotBlank
    @Size(max = 20)
    private String username;
    @Id
    private String id;

}
