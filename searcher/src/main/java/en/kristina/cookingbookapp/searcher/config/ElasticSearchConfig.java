package en.kristina.cookingbookapp.searcher.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class ElasticSearchConfig {

	@Bean
	RestHighLevelClient client() {
		return new RestHighLevelClient(
				RestClient.builder(
						new HttpHost("localhost", 9200, "http")));
	}

}