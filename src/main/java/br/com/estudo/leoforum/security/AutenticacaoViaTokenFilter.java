package br.com.estudo.leoforum.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.estudo.leoforum.modelo.Usuario;
import br.com.estudo.leoforum.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperaToken(request);
		
		boolean isValido = tokenService.isTokenValido(token);
		
		if (isValido) {
			
			autenticaCliente(token);
			
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void autenticaCliente(String token) {
		
		Long idUsuario = tokenService.getIdUsuario(token);
		
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		
	}

	private String recuperaToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			
			return null;
			
		}
		
		return token.substring(7, token.length());
	
	}

}
