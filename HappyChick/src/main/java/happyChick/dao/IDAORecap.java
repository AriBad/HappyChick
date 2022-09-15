package happyChick.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import happyChick.model.Recap;

public interface IDAORecap extends JpaRepository<Recap, Integer> {

	@Query("SELECT r from Recap r where r.poulailler.id=:id")
	Optional <List<Recap>> findByPoulailler(Integer id);
}
