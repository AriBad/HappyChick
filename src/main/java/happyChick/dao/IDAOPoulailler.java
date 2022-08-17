package happyChick.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import happyChick.model.Poulailler;
import happyChick.model.User;

public interface IDAOPoulailler extends JpaRepository<Poulailler, Integer> {
	@Query("SELECT p from Poulailler p join fetch p.listePoules where p.id=:id")
	public Poulailler findByIdWithPoule(@Param("id") Integer id );

	public void deleteByUser(User user);
}
