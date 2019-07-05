package en.kristina.cookingbookapp.repository;

import en.kristina.cookingbookapp.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
