package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service;

import java.util.List;

import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Game;

public interface GameService {

	Game save(Long id) throws Exception;
	
	List<?> getAllGames() throws Exception;

	void deleteGamesAll() throws Exception;

}
