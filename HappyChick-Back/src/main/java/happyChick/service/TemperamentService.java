package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import exception.TemperamentException;
import happyChick.dao.IDAOPoule;
import happyChick.dao.IDAOTemperament;
import happyChick.model.Temperament;


public class TemperamentService {
	
	@Autowired
	private IDAOTemperament temperamentRepo;
	
	@Autowired
	private IDAOPoule pouleRepo;

	public Temperament getById(Integer id) {
		// return temperamentRepo.findById(id).orElseThrow(TemperamentException::new);
		return temperamentRepo.findById(id).orElseThrow(() -> {
			throw new TemperamentException("id Temperament inconnu");
		});
	}

	public List<Temperament> getAll() {
		// temperamentRepo.findAll().forEach(System.out::println);
//		temperamentRepo.findAll().forEach((p) -> {
//			System.out.println(p);
//		});
		return temperamentRepo.findAll();
	}
	
	public Temperament create(Temperament temperament) {
		return temperamentRepo.save(temperament);
	}
	
	public Temperament update(Temperament temperament) {
		Temperament temperamentEnBase = getById(temperament.getId());
		return temperamentRepo.save(temperamentEnBase);
	}

	public void delete(Temperament temperament) {
		pouleRepo.deleteByTemperament(temperament);
		temperamentRepo.delete(temperament);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
}
