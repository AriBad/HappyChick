package happyChick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import happyChick.dao.IDAOUtilisateur;
import happyChick.service.DBUserDetailsService;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

//@Bean
//public InMemoryUserDetailsManager userDetailsService() {
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("123456")
//				.roles("USER", "ADMIN").build();
//		
//		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123456").roles("USER").build();
//
//		return new InMemoryUserDetailsManager(user, admin);
//	}

	@Bean
	public DBUserDetailsService userDetailsService(IDAOUtilisateur utilisateurRepository) {
		return new DBUserDetailsService(utilisateurRepository);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((authz) -> {
			authz.anyRequest().authenticated();
		}).httpBasic();

		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
