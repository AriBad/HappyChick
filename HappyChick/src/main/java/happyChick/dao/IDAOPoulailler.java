package happyChick.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happyChick.model.Poulailler;

public interface IDAOPoulailler extends JpaRepository<Poulailler, Integer> {
	@Query("SELECT p from Poulailler p join fetch p.listePoules where p.id=:id")
	public Optional<Poulailler> findByIdWithPoules(@Param("id") Integer id );
	
	@Query("SELECT p from Poulailler p join fetch p.listePoules")
	public List<Poulailler> findAllWithPoules();
	
	@Query("SELECT p from Poulailler p join fetch p.listePoules where p.utilisateur.id=:id")
	public List<Poulailler> findAllByUserWithPoules(Integer id);
	
	
	
}

