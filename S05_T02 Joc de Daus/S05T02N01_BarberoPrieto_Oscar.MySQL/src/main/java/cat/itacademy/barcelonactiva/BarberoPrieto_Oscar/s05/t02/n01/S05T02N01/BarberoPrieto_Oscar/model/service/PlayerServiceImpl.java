
package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.dto.GameDTO;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Game;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Player;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override // Crear i guardar jugador no repetit
	public Player save(Player player) throws Exception {

		if (playerRepository.findByNom(player.getNom()) != null) {

			throw new Exception("Nom existent");
		}
		return playerRepository.save(player);
	}

	@Override // Llista de tots els jugadors
	public List<?> findAll() throws Exception {

		try {

			List<Player> entities = playerRepository.findAll();
			List<PlayerDTO> entitiesDTO = new ArrayList<>();

			for (Player p : entities) {
				PlayerDTO playerDTO = new PlayerDTO(p.getNom(), p.getPercentatge(), p.getPlays());
				entitiesDTO.add(playerDTO);
			}

			return entitiesDTO;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	@Override // Informació d'un jugador
	public Player findById(Long id) throws Exception {

		try {

			Optional<Player> entityOptional = playerRepository.findById(id);
			return entityOptional.get();

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	@Override // Editar un jugador
	public Player update(Long id, Player player) throws Exception {

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

	@Override // Eliminar un jugador
	public boolean delete(Long id) throws Exception {
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

	@Override // Partides d'un jugador
	public List<?> getAllGamesOnePlayer(Long id) throws Exception {

		try {

			Optional<Player> entityOptional = playerRepository.findById(id);
			Player player = entityOptional.get();

			List<Game> game = player.getGamesPlayed();
			List<GameDTO> gamesPlayedDTO = new ArrayList<>();

			for (Game g : game) {

				GameDTO gameDTO = new GameDTO(g.getValorDau1(), g.getValorDau2(), g.getResultat(),
						player.getPercentatge());
				gamesPlayedDTO.add(gameDTO);
			}

			return gamesPlayedDTO;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	@Override // Mitja percentatge de tots els jugadors
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

	@Override //  // Major percentatge d'èxit
	public PlayerDTO winner() throws Exception {

		try {

			List<Player> entities = playerRepository.findAll();

			double maxPercentage = 0;
			PlayerDTO playerDTOwinner = null;

	        for (Player p : entities) {
	        	
	            double currentPercentage = p.getPercentatge();
	            if (currentPercentage > maxPercentage) {
	                maxPercentage = currentPercentage;
	                playerDTOwinner=new PlayerDTO(p.getNom(),p.getPercentatge(),p.getPlays());
	            }
	        }

	        return playerDTOwinner;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}
	
	@Override //  // Menor percentatge d'èxit
	public PlayerDTO loser() throws Exception {
		
		try {
			
			List<Player> entities = playerRepository.findAll();
			
			double minPercentage = 100;
			PlayerDTO playerDTOloser = null;
			
			for (Player p : entities) {
				
				double currentPercentage = p.getPercentatge();
				if (currentPercentage < minPercentage) {
					minPercentage = currentPercentage;
					playerDTOloser=new PlayerDTO(p.getNom(),p.getPercentatge(),p.getPlays());
				}
			}
			
			return playerDTOloser;
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
	}
}
