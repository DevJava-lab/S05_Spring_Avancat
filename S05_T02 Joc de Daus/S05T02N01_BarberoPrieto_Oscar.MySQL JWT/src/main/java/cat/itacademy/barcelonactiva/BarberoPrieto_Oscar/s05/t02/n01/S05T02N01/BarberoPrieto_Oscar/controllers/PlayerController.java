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

	// Guardar jugador
	@PostMapping("")
	public ResponseEntity<?> savePlayer(@RequestBody Player player) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.save(player));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Torna a probar un altre nom.");
		}
	}

	// Llista de jugadors
	@GetMapping("")
	public ResponseEntity<?> getAll() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.findAll());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. ");
		}
	}

	// Detalls d'un jugador
	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable Long id) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.findById(id));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Intenti més tard.");
		}
	}

	// Editar jugador
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Player player) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.update(id, player));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	// Eliminar jugador
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePlayer(@PathVariable Long id) {

		try {
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(playerService.delete(id));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	// Guardar partida a un jugador.
	@PostMapping("{id}/games")
	public ResponseEntity<?> saveGame(@PathVariable Long id) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(gameService.save(id));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	// Llista de totes les partides
	@GetMapping("/games")
	public ResponseEntity<List<?>> getGamesAll() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(gameService.getAllGames());
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Llista de les partides d'un jugador
	@GetMapping("/{id}/games")
	public ResponseEntity<List<?>> getGamesOne(@PathVariable Long id) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.getAllGamesOnePlayer(id));
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// Eliminar partides d'un jugador
	@DeleteMapping("/{id}/games")
	public ResponseEntity<?> deleteGamesOnePlayer(@PathVariable Long id) {
		try {
			playerService.deleteGamesOnePlayer(id);
			return ResponseEntity.status(HttpStatus.OK).body("Històric jugades deleted");
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}
	
	// Eliminar totes les partides
	@DeleteMapping("/games")
	public ResponseEntity<?> deleteGamesAllPlayers() {
		try {
			gameService.deleteGamesAll();
			return ResponseEntity.status(HttpStatus.OK).body("Històric jugades deleted");
		} catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. Intenti més tard.");
		}
	}

	// Percentatge mitjà de totes les partides
	@GetMapping("/ranking")
	public ResponseEntity<?> percentatgeAllGames() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.average());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}

	// Major percentatge d'èxit
	@GetMapping("/ranking/winner")
	public ResponseEntity<?> winner() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.winner());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}

	// Menor percentatge d'èxit
	@GetMapping("/ranking/loser")
	public ResponseEntity<?> loser() {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(playerService.loser());
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
		}
	}

}
