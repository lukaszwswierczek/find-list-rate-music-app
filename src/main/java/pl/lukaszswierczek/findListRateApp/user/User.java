package pl.lukaszswierczek.findListRateApp.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 60)
    @NotBlank(message = "Username field must be filled.")
    @Size(min = 5, message = "Must contain at least 5 characters.")
    private String username;
    @NotBlank(message = "Password field must be filled.")
    @Size(min = 5, message = "Must contain at least 5 characters.")
    private String password;
    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
