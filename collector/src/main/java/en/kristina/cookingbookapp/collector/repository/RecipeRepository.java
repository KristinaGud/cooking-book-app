package en.kristina.cookingbookapp.collector.repository;

import en.kristina.cookingbookapp.collector.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
