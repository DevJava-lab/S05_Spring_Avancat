package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Game;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Player;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.repository.PlayerRepository;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;

	// Guardar partida
	@Override
	public Game save(String id) throws Exception {

		try {

			Optional<Player> playerOptional = playerRepository.findById(id);
			Player player = playerOptional.get();

			Game game = gameRepository.save(new Game());

			player.addGame(game);

			playerRepository.save(player);

			return game;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	// Veure totes les partides
	@Override
	public List<?> getAllGames() throws Exception {

		try {

			return gameRepository.findAll();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Eliminar totes les partides
	@Override
	public void deleteGamesAll() throws Exception {

		try {

			List<Player> players=playerRepository.findAll();
			
			for(Player p : players) {
				
				p.clear();
				playerRepository.save(p);
			}
			
			gameRepository.deleteAll();
			

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

}
