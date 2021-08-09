package seguimientoCartera.login;

import java.util.Base64;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import seguimientoCartera.user.UserService;
import seguimientoCartera.user.Usuario;

@Service
public class AuthService {

	private UserService userService;

	public AuthService(UserService userService) {
		this.userService = userService;
	}

	public boolean authorize(Map<String, String> headers) {
		return userService.login(decodeToUser(headers));
	}
	
	public boolean authorize(HttpServletRequest request) {
		return userService.login(decodeToUser(request));
	}
	
	public Long requestId(HttpServletRequest request) {
		return Long.valueOf(request.getSession().getAttribute("LOGGEDIN_USER").toString());
	}
	
	public Usuario decodeToUser(HttpServletRequest request) {
		String encoded = request.getHeader("authorization").substring("Basic".length()).trim();
		return decodeToUser(encoded);
	}
	
	public Usuario decodeToUser(Map<String, String> headers) {
		String encoded = headers.get("authorization").substring("Basic".length()).trim();

		return decodeToUser(encoded);
	}
	
	public Usuario decodeToUser(String encoded) {
		String[] decodedCredentials = new String(Base64.getDecoder().decode(encoded)).split(":");

		return Usuario.builder()
				.username(decodedCredentials[0])
				.password(decodedCredentials[1])
				.build();
	}

}
