package happyChick;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import happyChick.dao.IDAOUtilisateur;
import happyChick.model.Role;
import happyChick.model.Utilisateur;


@SpringBootTest
class LoadTests {

	private final IDAOUtilisateur utilisateurRepository;

	@Autowired
	public LoadTests(IDAOUtilisateur utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}

	@Test
	public void load() {

		Utilisateur ariane = new Utilisateur("ariane", "123456");
		ariane.addRole(Role.USER);

		ariane = utilisateurRepository.save(ariane);

		Utilisateur alae = new Utilisateur("alae", "123456");
		alae.addRole(Role.ADMIN);

		alae = utilisateurRepository.save(alae);

		Utilisateur alban = new Utilisateur("alban", "123456");
		alban.addRole(Role.ADMIN);
		alban.addRole(Role.USER);

		alban = utilisateurRepository.save(alban);
	}

}
