package desafioapirest.dio.controller;

import desafioapirest.dio.Dtos.auth.AuthenticationDTO;
import desafioapirest.dio.Dtos.auth.LoginResponseDTO;
import desafioapirest.dio.Dtos.auth.RegisterDTO;
import desafioapirest.dio.domain.model.User;
import desafioapirest.dio.domain.repository.UserRepository;
import desafioapirest.dio.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data) {
        if (this.repository.findByLogin(data.login()) != null) return ResponseEntity.ok().build();

        String encryptedPassword =  new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword);

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
