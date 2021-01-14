package home.todo_list.dto.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


@Getter
@Setter
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    private Set<String> roles;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
