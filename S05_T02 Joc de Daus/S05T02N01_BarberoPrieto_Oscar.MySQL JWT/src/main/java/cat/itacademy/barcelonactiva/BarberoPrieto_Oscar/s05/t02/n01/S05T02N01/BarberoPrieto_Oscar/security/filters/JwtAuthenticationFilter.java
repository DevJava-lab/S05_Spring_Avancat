package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.UserEntity;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter
		extends
			UsernamePasswordAuthenticationFilter {

	private JwtUtils jwtUtils;

	public JwtAuthenticationFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}

	// Processar una sol·licitud d'autenticació enviada pel client.
	// Extreure les dades, validar-les i si tot és correcte donar accés.
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {

		UserEntity userEntity = null;
		String username = "";
		String password = "";

		try {

			userEntity = new ObjectMapper().readValue(request.getInputStream(),
					UserEntity.class);
			username = userEntity.getUsername();
			password = userEntity.getPassword();

		} catch (StreamReadException e) {
			throw new RuntimeException(e);
		} catch (DatabindException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);

		return getAuthenticationManager().authenticate(authenticationToken);
	}

	// Resposta de sortida quan l'autenticació ha sigut correcte.
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		// Obtenir usuari autenticat.
		User user = (User) authResult.getPrincipal();

		// Crear un token nou.
		String token = jwtUtils.generateAccesToken(user.getUsername());

		// Mostrar el token per poder fer servir.
		response.addHeader("Authorization", token);

		// Mostrar les dades de resposta
		Map<String, Object> httpResponse = new HashMap<>();

		httpResponse.put("token", token);
		httpResponse.put("Message", "Autenticació Correcte");
		httpResponse.put("Username", user.getUsername());

		// Configuració capçaleres i resposta Http
		response.getWriter().write(new ObjectMapper().writeValueAsString(
				httpResponse));
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().flush();

		super.successfulAuthentication(request, response, chain, authResult);
	}
}
