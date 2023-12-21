package todo.ornot.todobackend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import todo.ornot.todobackend.user.dto.AuthResponseDto;
import todo.ornot.todobackend.user.dto.LoginDto;
import todo.ornot.todobackend.user.dto.RegisterDto;
import todo.ornot.todobackend.user.entity.Role;
import todo.ornot.todobackend.user.entity.UserEntity;
import todo.ornot.todobackend.user.repository.RoleRepository;
import todo.ornot.todobackend.user.repository.UserRepository;
import todo.ornot.todobackend.user.security.JwtGenerator;

import java.util.Collections;

@Controller
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if(userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registerd success!", HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> removeById(@PathVariable Long id){
        if (!userRepository.existsById(id)) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteUserRolesByUserId(id);
        userRepository.deleteById(id);
        return new ResponseEntity<>("User with id + ${id} + deleted", HttpStatus.OK);
    }

}
