package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Player;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service.GameService;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service.PlayerService;

@RestController
@RequestMapping(path = "/players")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

	@Autowired
	private GameService gameService;

	@PostMapping("") // Guardar jugador
	public ResponseEntity<?> savePlayer(@RequestBody Player player) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.save(player));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Torna a probar un altre nom.");
		}
	}
	
	@GetMapping("") // Llista de jugadors
	public ResponseEntity<?> getAll() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.findAll());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. ");
		}
	}

	@GetMapping("/{id}") // Detalls d'un jugador
	public ResponseEntity<?> getOne(@PathVariable Long id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(playerService.findById(id));
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Intenti més tard.");
		}
	}

	@PutMapping("/{id}") // Editar jugador
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Player player) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.update(id, player));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}
	
	@DeleteMapping("/{id}") // Eliminar jugador
	public ResponseEntity<?> deletePlayer(@PathVariable Long id) {

		try {
			playerService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Jugador : " + id + " eliminat.");
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	@PostMapping("{id}/games") // Guardar partida a un jugador.
	public ResponseEntity<?> saveGame(@PathVariable Long id) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(gameService.save(id));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	@GetMapping("/games") // Llista de totes les partides
	public ResponseEntity<List<?>> getGamesAll() {
		
		try {
		
			return ResponseEntity.status(HttpStatus.OK).body(gameService.findAll());
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@GetMapping("/{id}/games") // Llista de les partides d'un jugador
	public ResponseEntity<List<?>> getGamesOne(@PathVariable Long id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(playerService.getAllGamesOnePlayer(id));
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{id}/games") // Eliminar partidas d'un jugador
	public ResponseEntity<?> deleteGame(@PathVariable Long id) {
		try {
			gameService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Històric jugades deleted");
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	@GetMapping("/ranking") // Percentatge mitjà de totes les partides
	public ResponseEntity<?> percentatgeAllGames() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.average());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	@GetMapping("/ranking/winner") // Major percentatge d'èxit
	public ResponseEntity<?> winner() {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(playerService.winner());
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	@GetMapping("/ranking/loser") // Menor percentatge d'èxit
	public ResponseEntity<?> loser() {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(playerService.loser());
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}
	
	

}
