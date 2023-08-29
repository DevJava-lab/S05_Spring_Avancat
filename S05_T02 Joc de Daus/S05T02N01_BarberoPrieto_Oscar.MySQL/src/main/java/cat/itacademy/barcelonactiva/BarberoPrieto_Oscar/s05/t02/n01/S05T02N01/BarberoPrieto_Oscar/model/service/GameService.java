package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.service;

import java.util.List;

public interface GameService {

	String save(Long id) throws Exception;
	
	List<?> findAll() throws Exception;

	void delete(Long id) throws Exception;

}
