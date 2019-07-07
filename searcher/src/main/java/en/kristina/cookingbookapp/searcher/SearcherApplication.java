package en.kristina.cookingbookapp.searcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SearcherApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearcherApplication.class, args);
	}

}
