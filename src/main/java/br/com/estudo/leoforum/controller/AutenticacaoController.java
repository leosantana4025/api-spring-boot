package br.com.estudo.leoforum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudo.leoforum.dto.TokenDTO;
import br.com.estudo.leoforum.form.LoginForm;
import br.com.estudo.leoforum.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm loginForm) {
		
		UsernamePasswordAuthenticationToken login = loginForm.toUsernamePassword();
		
		try {
			
			Authentication autenticacao = authenticationManager.authenticate(login);
			
			String token = tokenService.gerarToken(autenticacao);
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
			
		} catch (AuthenticationException authenticationException) {
			
			return ResponseEntity.badRequest().build();
			
		}
		
	}
	
}
