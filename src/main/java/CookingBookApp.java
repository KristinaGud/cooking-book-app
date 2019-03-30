import com.google.gson.Gson;
import entities.Recipe;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import org.hibernate.cfg.Configuration;

public class CookingBookApp {
    private static final Gson GSON = new Gson();
    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            for (int i = 0; i < 49; i++) {
                RecipeBody recipeForToday = parseRecipe(getAllRecipeText(args[0])).recipe;
                extractAndSave(recipeForToday);
            }
        } else {
            System.out.println("Add Your Api Key as an argument");
        }
    }

    private static int pickAtRandomRecipeId() {
        Random random = new Random();
        return random.nextInt(54690);
    }

    private static String getAllRecipeText(String apiKey) throws IOException{
        String urlToRecipe = "https://food2fork.com/api/get?key=" + apiKey + "&rId=" + pickAtRandomRecipeId();
        URL url = new URL(urlToRecipe);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }

    private static MyRecipe parseRecipe(String json) {
        return GSON.fromJson(json, MyRecipe.class);
    }

    private static void extractAndSave(RecipeBody recipeBody) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        Recipe recipeToAddToDb = new Recipe();

        if (!recipeBody.getRecipe_id().equals(recipeToAddToDb.getFork_id())) {
            recipeToAddToDb.setFork_id(recipeBody.recipe_id);
            recipeToAddToDb.setName(recipeBody.title);
            recipeToAddToDb.setUrl(recipeBody.source_url);
            recipeToAddToDb.setRecipe(Arrays.toString(recipeBody.ingredients));
            session.persist(recipeToAddToDb);
        }

        session.flush();
        session.close();
    }
}
