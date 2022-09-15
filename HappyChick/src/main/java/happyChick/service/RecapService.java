package happyChick.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import happyChick.dao.IDAORecap;
import happyChick.exception.RecapException;
import happyChick.exception.RecapException;
import happyChick.model.Recap;
import happyChick.model.Recap;

@Service
public class RecapService {

	@Autowired
	private IDAORecap recapRepo;
	
	public Recap getById(Integer id) {
		return recapRepo.findById(id).orElseThrow(() -> {
			throw new RecapException("id Recap inconnu");
		});
	}
	public List<Recap> getAll() {

		return recapRepo.findAll();
	}
	
	public Recap create(Recap recap) {
		return recapRepo.save(recap);
	}

	public void delete(Integer id) {
		recapRepo.deleteById(id);
	}

	public void deleteByPoulailler(Integer id) {
		for (Recap recap : getAllByPoulailler(id)) {
			delete(recap.getId());
		}
	}
	public List<Recap> getAllByPoulailler(Integer id) {
		return recapRepo.findByPoulailler(id).orElseThrow(() -> {
			throw new RecapException("Le poulailler est inconnu");
		});
	}
}
