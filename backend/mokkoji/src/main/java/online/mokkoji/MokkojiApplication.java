package online.mokkoji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MokkojiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MokkojiApplication.class, args);
    }
}
