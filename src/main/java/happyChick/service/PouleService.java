package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import exception.PoulaillerException;
import happyChick.dao.IDAOPoule;
import happyChick.model.Poulailler;


public class PouleService {
	
	@Autowired
	private IDAOPoule pouleRepo;

	public Poulailler getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return pouleRepo.findById(id).orElseThrow(() -> {
			throw new PoulaillerException("id Poulailler inconnu");
		});
	}

	public List<Poulailler> getAll() {
		// poulaillerRepo.findAll().forEach(System.out::println);
//		poulaillerRepo.findAll().forEach((p) -> {
//			System.out.println(p);
//		});
		return poulaillerRepo.findAll();
	}
	
	public Poulailler create(Poulailler poulailler) {
		if (poulailler.getNom() == null || poulailler.getNom().isEmpty()) {
			throw new PoulaillerException("nom obligatoire");
		}
		return poulaillerRepo.save(poulailler);
	}
	
	public Poule update(Poulailler poulailler) {
		if (poulailler.getNom() == null || poulailler.getNom().isEmpty()) {
			throw new PoulaillerException("nom obligatoire");
		}
		Poulailler poulaillerEnBase = getById(poulailler.getId());
		poulaillerEnBase.setNom(poulailler.getNom());
		return poulaillerRepo.save(poulaillerEnBase);
	}

	public void delete(Poulailler poulailler) {
		pouleRepo.deleteByPoulailler(poulailler);
		poulaillerRepo.delete(poulailler);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
}
