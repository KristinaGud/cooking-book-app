package en.kristina.cookingbookapp.searcher.config;

import com.rabbitmq.client.AMQP;
import en.kristina.cookingbookapp.communicator.service.QueueConsumer;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

	@Bean
	RestHighLevelClient client() {
		return new RestHighLevelClient(
				RestClient.builder(
						new HttpHost("localhost", 9200, "http")));
	}

	@Bean
	public QueueConsumer queueConsumer() {
		return new QueueConsumer();
	}
}
