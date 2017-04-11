package net.kon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SpringBootMultipleDatasourceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultipleDatasourceDemoApplication.class, args);
	}
}
