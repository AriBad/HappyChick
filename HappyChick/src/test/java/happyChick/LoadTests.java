package happyChick;

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
		Utilisateur auriane = new Utilisateur("auriane", "azerty");
		auriane.addRole(Role.USER);

		auriane = utilisateurRepository.save(auriane);

		Utilisateur ghavesh = new Utilisateur("ghavesh", "123456");
		ghavesh.addRole(Role.ADMIN);

		ghavesh = utilisateurRepository.save(ghavesh);

		Utilisateur alae = new Utilisateur("alae", "123456");
		alae.addRole(Role.ADMIN);
		alae.addRole(Role.USER);

		alae = utilisateurRepository.save(alae);
	}

}
