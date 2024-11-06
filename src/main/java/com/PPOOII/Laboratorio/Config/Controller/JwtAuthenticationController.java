package com.PPOOII.Laboratorio.Config.Controller;

import com.PPOOII.Laboratorio.Config.CustomUserDetailsService;
import com.PPOOII.Laboratorio.Config.JWTAuthtenticationConfig;
import com.PPOOII.Laboratorio.Config.Model.JwtRequest;
import com.PPOOII.Laboratorio.Config.Model.JwtResponse;
import com.PPOOII.Laboratorio.Entities.Usuario;
import com.PPOOII.Laboratorio.Servicio.UsuarioServicioImp;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private UsuarioServicioImp usuarioServiceImp;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @RequestMapping(
            value = "/authenticate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtRequest authenticationRequest,
            @RequestHeader("APIkey") String APIKey) throws Exception {

        try {
            System.out.println("********************************************************************");
            System.out.println("authenticationRequest.getUsername():[" + authenticationRequest.getUsername() + "]");
            System.out.println("authenticationRequest.getPassword():[" + authenticationRequest.getPassword() + "]");
            System.out.println("APIKey:[" + APIKey + "]");
            System.out.println("********************************************************************");

            Usuario user = usuarioServiceImp.findByUsernameANDAPIKey(authenticationRequest.getUsername(), APIKey);
            if (user == null) {
                throw new Exception("Usuario o APIKey incorrectos");
            }

            //final UserDetails userDetails = jwtInMemoryUserDetailsService.loadUserByUsername(user.getLogin());
            final UserDetails userDetails = customUserDetailsService.loadUserByUsernameAndApiKey(authenticationRequest.getUsername(), APIKey);
            if (userDetails == null) {
                System.out.println("No se pudieron cargar los detalles del usuario.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error al cargar detalles del usuario.");
            }

            //System.out.println("UserDetails: " + userDetails.getUsername());

            final String token = jwtAuthtenticationConfig.getJWTToken(user.getLogin());
            System.out.println("********************************************************************");
            System.out.println("token:[" + token + "]");
            System.out.println("********************************************************************");

            return ResponseEntity.ok(new JwtResponse(token));

        } catch (Exception e) {
            System.err.println("Error en la autenticación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida: " + e.getMessage());
        }
    }

    /*@PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtRequest authenticationRequest,
            @RequestHeader("APIkey") String APIKey,
            HttpServletResponse response) throws Exception {

        // Tu lógica para autenticar al usuario y generar el token JWT
        Usuario user = usuarioServiceImp.findByUsernameANDAPIKey(authenticationRequest.getUsername(), APIKey);
        final String token = jwtAuthtenticationConfig.getJWTToken(user.getLogin());

        // Agregar el token JWT a una cookie
        Cookie jwtCookie = new Cookie("JWT_TOKEN", token);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(24 * 60 * 60); // 1 día, o el tiempo que prefieras

        // Agregar la cookie a la respuesta
        response.addCookie(jwtCookie);

        return ResponseEntity.ok(new JwtResponse(token));
    }*/

}
