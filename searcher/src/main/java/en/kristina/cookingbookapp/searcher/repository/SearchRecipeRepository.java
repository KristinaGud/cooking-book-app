package en.kristina.cookingbookapp.searcher.repository;

import com.google.gson.Gson;
import en.kristina.cookingbookapp.searcher.entity.SearchRecipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class SearchRecipeRepository {

	private final Gson gson;
	private final RestHighLevelClient client;

	public void add(SearchRecipe recipe)  {
		String toJson = gson.toJson(recipe);
		IndexRequest request = new IndexRequest("recipe")
				.source(toJson, XContentType.JSON);

		IndexResponse indexResponse = null;
		try {
			indexResponse = client.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info(indexResponse != null ? indexResponse.toString() : null);
	}

	public Map<String, String> findAllRecipesByIngredient(String ingredient) throws IOException {
		SearchRequest search = new SearchRequest("recipe")
				.source(new SearchSourceBuilder()
						.query(QueryBuilders.termQuery("ingredients", ingredient)));

		SearchHits hits = client.search(search, RequestOptions.DEFAULT).getHits();

		Map<String, String> titleAndIngredients = new HashMap<>();
		for (int i = 0; i < hits.getHits().length; i++) {
			String title = hits.getAt(i).getSourceAsMap().get("name").toString();
			String ingredients = hits.getAt(i).getSourceAsMap().get("ingredients").toString();
			titleAndIngredients.put(title, ingredients);
		}

		return titleAndIngredients;

	}
}
