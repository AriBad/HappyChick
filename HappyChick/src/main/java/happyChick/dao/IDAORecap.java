package happyChick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Recap;

public interface IDAORecap extends JpaRepository<Recap, Integer> {

}
