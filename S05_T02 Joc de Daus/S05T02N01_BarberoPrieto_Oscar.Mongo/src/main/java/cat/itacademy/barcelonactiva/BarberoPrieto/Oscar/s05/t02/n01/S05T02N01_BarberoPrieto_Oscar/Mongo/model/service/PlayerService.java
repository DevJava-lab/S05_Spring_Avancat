package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.service;

import java.util.List;

import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Player;


public interface PlayerService {

	Player save(Player entity) throws Exception;

	List<?> findAll() throws Exception;

	Player findById(String id) throws Exception;

	Player update(String id, Player player) throws Exception;

	boolean delete(String id) throws Exception;

	List<?> getAllGamesOnePlayer(String id) throws Exception;

	void deleteGamesOnePlayer(String id) throws Exception;
	
	double average() throws Exception;

	PlayerDTO winner() throws Exception;

	PlayerDTO loser() throws Exception;


}
