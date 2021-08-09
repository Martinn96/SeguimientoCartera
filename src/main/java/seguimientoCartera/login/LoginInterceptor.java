package seguimientoCartera.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import seguimientoCartera.config.SessionInfo;
import seguimientoCartera.user.UserService;
import seguimientoCartera.user.Usuario;


@Component
public class LoginInterceptor implements HandlerInterceptor {

	private AuthService authService;
	private UserService userService;
		
	@Autowired
	private SessionInfo sessionInfo;

	public LoginInterceptor(AuthService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!authService.authorize(request)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			return false;
		}
		
		Usuario usuario = authService.decodeToUser(request);
		
		usuario = userService.getId(usuario).get();
		request.getSession().setAttribute("LOGGEDIN_USER", usuario.getId());		
		
		sessionInfo.setUsuario(usuario);
		return true;
	}

}
