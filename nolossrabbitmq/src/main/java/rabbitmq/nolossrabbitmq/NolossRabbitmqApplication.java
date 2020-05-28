package rabbitmq.nolossrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NolossRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(NolossRabbitmqApplication.class, args);
    }

}
