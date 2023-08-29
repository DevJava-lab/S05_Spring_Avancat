
package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Game;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Player;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.repository.PlayerRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired 
	private GameRepository gameRepository;
	
	@Override // Guardar partida
	public String save(Long id) throws Exception {

		try {

			Optional<Player> playerOptional = playerRepository.findById(id);
			Player player = playerOptional.get();

			Game game = new Game();

			game.setPlayer(player);
			player.addGame(game);

			playerRepository.save(player);

			return game.getResultat();

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}
	
	@Override// Veure totes les partides
	public List<?> findAll() throws Exception{
		
		try {
			
			List<Game> games = gameRepository.findAll();
			
			return games;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override // Eliminar partidas d'un jugador
	public void delete(Long id) throws Exception {

		try {

			Optional<Player> playerOptional = playerRepository.findById(id);
			Player player = playerOptional.get();

			List<?> listPlayer = player.getGamesPlayed();
			listPlayer.removeAll(listPlayer);

			playerRepository.save(player);

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	
}
