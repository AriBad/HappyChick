package happyChick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "happyChick.dao.IDAOPoule", repositoryImplementationPostfix = "CriteriaPouleImpl")
public class HappyChickApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappyChickApplication.class, args);
	}

}
