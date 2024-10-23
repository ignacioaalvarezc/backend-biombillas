package com.sys.bio.back.controllers.user;

// IMPORTS
import com.sys.bio.back.infra.exceptions.UserDisabledException;
import com.sys.bio.back.infra.exceptions.UserNotFoundException;
import com.sys.bio.back.models.user.User;
import com.sys.bio.back.infra.security.JwtToken.JwtRequest;
import com.sys.bio.back.infra.security.JwtToken.JwtResponse;
import com.sys.bio.back.infra.security.JwtToken.JwtUtils;
import com.sys.bio.back.services.user.UserService;
import com.sys.bio.back.services.user.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "https://softbio.cl")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            User user = userService.getUserByUsername(jwtRequest.getUsername());
            if (!user.isEnabled()) {
                throw new UserDisabledException("Usuario bloqueado");
            }
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
            String token = this.jwtUtils.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserNotFoundException | UserDisabledException e) {
            log.error("Error al generar el token: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException exception) {
            throw new Exception("Usuario deshabilitado " + exception.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales inválidas " + e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(Principal principal) {
        try {
            if (principal == null) {
                throw new UserNotFoundException("No se ha autenticado ningún usuario");
            }
            String username = principal.getName();
            User currentUser = (User) this.userDetailsService.loadUserByUsername(username);
            return ResponseEntity.ok(currentUser);
        } catch (UserNotFoundException e) {
            log.error("Error al obtener el usuario actual: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
        }
    }
}
