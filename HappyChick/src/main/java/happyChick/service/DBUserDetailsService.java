package happyChick.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;*/

import happyChick.dao.IDAOUtilisateur;
import happyChick.model.Role;
import happyChick.model.Utilisateur;

public class DBUserDetailsService /*implements UserDetailsService*/ {

	/*private final IDAOUtilisateur utilisateurRepository;

	public DBUserDetailsService(IDAOUtilisateur utilisateurRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Utilisateur> optUtilisateur = utilisateurRepository.findByLogin(username);

		if (!optUtilisateur.isPresent()) {
			throw new UsernameNotFoundException(username);
		}

		Utilisateur utilisateur = optUtilisateur.get();

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		for (Role role : utilisateur.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
		}
		User user = new User(utilisateur.getLogin(), utilisateur.getMotDePasse(), utilisateur.isActivated(), true, true, true, grantedAuthorities);

		return user;
	}*/

}
