package uz.pdp.SpringSecurity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import uz.pdp.SpringSecurity.utils.SecurityUtils;


import javax.sql.DataSource;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecurityApplication {
	private final SecurityUtils securityUtils;

    public SpringSecurityApplication(SecurityUtils securityUtils) {
        this.securityUtils = securityUtils;
    }


    public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner insertQuery(DataSource dataSource){
		return args->{
			Resource resource = new ClassPathResource("query.sql");
			System.out.println("=================================================================");
			ScriptUtils.executeSqlScript(dataSource.getConnection(),resource);
		};
	}
	@Bean
	public AuditorAware<Integer> auditorAware(){
		return ()-> Optional.ofNullable(securityUtils.getUser());
	}
}
