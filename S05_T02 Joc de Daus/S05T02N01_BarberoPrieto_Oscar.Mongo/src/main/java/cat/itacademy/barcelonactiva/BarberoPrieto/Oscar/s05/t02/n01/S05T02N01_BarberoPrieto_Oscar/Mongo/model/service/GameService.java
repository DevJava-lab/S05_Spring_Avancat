package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.service;

import java.util.List;

import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Game;

public interface GameService {

	Game save(String id) throws Exception;
	
	List<?> getAllGames() throws Exception;

	void deleteGamesAll() throws Exception;


}
