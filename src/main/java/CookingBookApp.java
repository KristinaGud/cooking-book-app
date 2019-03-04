import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class CookingBookApp {
    private static final Gson GSON = new Gson();

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            RecipeBody recipeForToday = parseRecipe(getAllRecipeText(args[0])).recipe;
            System.out.println(recipeForToday.title);
            System.out.println(recipeForToday.source_url);
            System.out.println(Arrays.toString(recipeForToday.ingredients));
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
}
