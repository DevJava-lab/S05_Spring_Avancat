package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player,String> {

	Player findByNom(String nom);

}
