package myhr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyhrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyhrApplication.class, args);
	}

}
