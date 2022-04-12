package tw.elliot.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PubServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PubServerApplication.class, args);
	}

}
