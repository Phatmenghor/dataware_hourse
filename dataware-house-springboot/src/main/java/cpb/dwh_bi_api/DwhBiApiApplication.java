package cpb.dwh_bi_api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@SpringBootApplication
@EnableJpaAuditing
public class DwhBiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DwhBiApiApplication.class, args);

	}

}
