package happyChick.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import happyChick.model.Temperament;

public interface IDAOTemperament extends JpaRepository<Temperament, Integer> {

}
