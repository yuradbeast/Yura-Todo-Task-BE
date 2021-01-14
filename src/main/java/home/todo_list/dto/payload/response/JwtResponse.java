package home.todo_list.dto.payload.response;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String accessToken;
	@Builder.Default
	private String type = "Bearer";
	private String id;
	private String username;
	private List<String> roles;


}
