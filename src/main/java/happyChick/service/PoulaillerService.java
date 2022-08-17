package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import exception.PoulaillerException;
import exception.PouleException;
import happyChick.dao.IDAOPoulailler;
import happyChick.dao.IDAOPoule;
import happyChick.model.Poulailler;

@Service
public class PoulaillerService {
	@Autowired
	private IDAOPoulailler poulaillerRepo;

	@Autowired
	private IDAOPoule pouleRepo;

	public Poulailler getById(Integer id) {
		// return poulaillerRepo.findById(id).orElseThrow(PoulaillerException::new);
		return poulaillerRepo.findById(id).orElseThrow(() -> {
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
	
	public Poulailler getByIdWithPoules(Integer id) {
		return poulaillerRepo.findByIdWithPoule(id).orElseThrow(() -> {
			throw new PoulaillerException("id Poulailler inconnu");
		});
	}

	public Poulailler create(Poulailler poulailler) {
		if (poulailler.getNom() == null || poulailler.getNom().isEmpty()) {
			throw new PoulaillerException("nom obligatoire");
		}
		return poulaillerRepo.save(poulailler);
	}

	public Poulailler update(Poulailler poulailler) {
		if (poulailler == null || pouleRepo.findById(poulailler.getId()) == null ) {
			throw new PoulaillerException("Update un poulailler qui n'existe pas en base");
		}
		return poulaillerRepo.save(poulailler);
	}

	public void delete(Poulailler poulailler) {
		pouleRepo.deleteByPoulailler(poulailler);
		poulaillerRepo.delete(poulailler);
	}

	public void deleteById(Integer id) {
		delete(getById(id));
	}
}
