package juanca.registroestudiantes.controller;

import jakarta.validation.Valid;
import juanca.registroestudiantes.dto.LoginRequestDTO;
import juanca.registroestudiantes.dto.LoginResponseDTO;
import juanca.registroestudiantes.model.Usuario;
import juanca.registroestudiantes.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    public ResponseEntity<LoginResponseDTO> registrar(@Valid @RequestBody LoginRequestDTO dto) {
        Usuario usuario = authService.registrar(dto.getUsername(), dto.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                LoginResponseDTO.builder()
                        .id(usuario.getId())
                        .username(usuario.getUsername())
                        .mensaje("Usuario registrado exitosamente")
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return authService.login(dto.getUsername(), dto.getPassword())
                .map(u -> ResponseEntity.ok(
                        LoginResponseDTO.builder()
                                .id(u.getId())
                                .username(u.getUsername())
                                .mensaje("Login exitoso")
                                .build()
                ))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                        LoginResponseDTO.builder()
                                .mensaje("Credenciales incorrectas")
                                .build()
                ));
    }
}
