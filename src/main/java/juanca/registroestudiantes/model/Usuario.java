package juanca.registroestudiantes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Usuario(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username invalido");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password invalido");
        }
        this.username = username;
        this.password = password;
    }
}
