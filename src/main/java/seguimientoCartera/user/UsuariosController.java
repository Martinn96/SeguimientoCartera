package seguimientoCartera.user;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import seguimientoCartera.config.PasswordBody;
import seguimientoCartera.login.AuthService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

	private UserService userService;
	private AuthService authService;
	
	public UsuariosController(UserService userService, AuthService authService) {
		this.userService = userService;
		this.authService = authService;
	}

	@GetMapping("/che")
	public ResponseEntity<String> login(){
		return ResponseEntity.status(HttpStatus.OK).body("que");
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestHeader Map<String, String> headers) {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/newuser")
	public ResponseEntity<String> add(@RequestBody Usuario usuario) {
		if (userService.save(usuario))
			return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado!");
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario ya existente!");
	}
	
	@PutMapping("/changepassword")
	public ResponseEntity<String> changePass(@RequestHeader Map<String, String> headers, @RequestBody PasswordBody password) {
		if (userService.modify(authService.decodeToUser(headers),password.getPassword()).isPresent())
			return ResponseEntity.status(HttpStatus.CREATED).body("Contraseña modificada!");
		else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Contraseña no modificada!");
	}
	
	@GetMapping("/session")
	public ResponseEntity<Usuario> getSession(){
		return ResponseEntity.ok(userService.getSession().getUsuario());
	}

}
