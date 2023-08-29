package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service;

import java.util.List;

import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.dto.PlayerDTO;
import cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities.Player;


public interface PlayerService {

	Player save(Player entity) throws Exception;
	
	List<?> findAll() throws Exception;

	Player findById(Long id) throws Exception;

	Player update(Long id, Player player) throws Exception;	

	boolean delete(Long id) throws Exception;

	List<?> getAllGamesOnePlayer(Long id) throws Exception;

	double average() throws Exception;

	PlayerDTO winner() throws Exception;

	PlayerDTO loser() throws Exception;



}
