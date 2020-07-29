package local.heftyb.howto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HowToApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(HowToApplication.class,
            args);
    }

}
