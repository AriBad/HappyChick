package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import exception.PouleException;
import happyChick.dao.IDAOPoule;
import happyChick.model.Poule;


public class PouleService {
	
	@Autowired
	private IDAOPoule pouleRepo;

	public Poule getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findById(id).orElseThrow(() -> {
			throw new PouleException("id Poulailler inconnu");
		});
	}

	public List<Poule> getAll() {
		// poulaillerRepo.findAll().forEach(System.out::println);
//		poulaillerRepo.findAll().forEach((p) -> {
//			System.out.println(p);
//		});
		return pouleRepo.findAll();
	}
	
	public Poule create(Poule poule) {
		return pouleRepo.save(poule);
	}
	
	public Poule update(Poule poule) {
		if (poule == null || pouleRepo.findById(poule.getId()) == null ) {
			throw new PouleException("Update une poule qui n'existe pas en base");
		}
		return pouleRepo.save(poule);
	}

	public void delete(Poule poule) {
		pouleRepo.delete(poule);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
}
