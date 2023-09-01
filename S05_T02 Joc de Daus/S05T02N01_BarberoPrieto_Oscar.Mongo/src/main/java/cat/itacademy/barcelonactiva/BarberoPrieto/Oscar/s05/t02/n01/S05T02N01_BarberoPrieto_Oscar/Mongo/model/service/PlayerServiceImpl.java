
package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Game;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Player;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private GameRepository gameRepository;
	
	// Crear i guardar jugador no repetit
	@Override 
	public Player save(Player player) throws Exception {

		if (playerRepository.findByNom(player.getNom()) != null) {
			throw new Exception("Nom existent");
		}
		return playerRepository.save(player);
	}

	// Llista de tots els jugadors
	@Override 
	public List<?> findAll() throws Exception {
		try {

			List<Player> entities = playerRepository.findAll();
			List<PlayerDTO> entitiesDTO = new ArrayList<>();

			for (Player p : entities) {
				PlayerDTO playerDTO = new PlayerDTO(p.getNom(), p.getPercentatge(), p.getRolls());
				entitiesDTO.add(playerDTO);
			}

			return entitiesDTO;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Informació d'un jugador
	@Override
	public Player findById(String id) throws Exception {
		
		try {
			
			Optional<Player> entityOptional = playerRepository.findById(id);
			return entityOptional.get();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Editar un jugador
	@Override 
	public Player update(String id, Player player) throws Exception {

		try {

			Player newPlayer = playerRepository.findById(id).orElse(null);
			newPlayer.setNom(player.getNom());
			if (playerRepository.findByNom(newPlayer.getNom()) != null) {

				throw new Exception("Nom existent");
			}

			return playerRepository.save(newPlayer);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Eliminar un jugador
	@Override 
	public boolean delete(String id) throws Exception {
		try {

			if (playerRepository.existsById(id)) {
				playerRepository.deleteById(id);
				return true;
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	// Partides d'un jugador
	@Override 
	public List<?> getAllGamesOnePlayer(String id) throws Exception {

		try {

			Optional<Player> entityOptional = playerRepository.findById(id);
			Player player = entityOptional.get();

			List<Game> game = player.getGamesPlayed();
			List<GameDTO> gamesPlayedDTO = new ArrayList<>();

			for (Game g : game) {

				GameDTO gameDTO = new GameDTO(g.getValorDau1(), g.getValorDau2(), g.getResultat());
				gamesPlayedDTO.add(gameDTO);
			}

			return gamesPlayedDTO;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}
	
	// Eliminar partidas d'un jugador
		@Override
		public void deleteGamesOnePlayer(String id) throws Exception {

			try {

				Optional<Player> entityOptional = playerRepository.findById(id);
				Player player = entityOptional.get();

				List<Game> gamesErase = player.getGamesPlayed();

				for (Game g : gamesErase) {

					gameRepository.delete(g);
				}

				player.clear();

				playerRepository.save(player);

			} catch (Exception e) {

				throw new Exception(e.getMessage());
			}
		}
	// Mitja percentatge de tots els jugadors
	@Override 
	public double average() throws Exception {

		try {

			List<Player> entities = playerRepository.findAll();

			double percentatgeTotal = 0;
			for (Player p : entities) {
				percentatgeTotal += p.getPercentatge();
			}

			return percentatgeTotal / entities.size();

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	// Major percentatge d'èxit
	@Override 
	public PlayerDTO winner() throws Exception {

		try {

			List<Player> entities = playerRepository.findAll();

			double maxPercentage = 0;
			PlayerDTO playerDTOwinner = null;

	        for (Player p : entities) {
	        	
	            double currentPercentage = p.getPercentatge();
	            if (currentPercentage > maxPercentage) {
	                maxPercentage = currentPercentage;
	                playerDTOwinner=new PlayerDTO(p.getNom(),p.getPercentatge(),p.getRolls());
	            }
	        }

	        return playerDTOwinner;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}
	
	// Menor percentatge d'èxit
	@Override 
	public PlayerDTO loser() throws Exception {
		
		try {
			
			List<Player> entities = playerRepository.findAll();
			
			double minPercentage = 100;
			PlayerDTO playerDTOloser = null;
			
			for (Player p : entities) {
				
				double currentPercentage = p.getPercentatge();
				if (currentPercentage < minPercentage) {
					minPercentage = currentPercentage;
					playerDTOloser=new PlayerDTO(p.getNom(),p.getPercentatge(),p.getRolls());
				}
			}
			
			return playerDTOloser;
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
	}

	

}
