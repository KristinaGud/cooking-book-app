package entities;

import javax.persistence.*;

@Entity
@Table (name = "recipes")

public class Recipe {
    @Column
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String fork_id;

    @Column
    String name;

    @Column
    String url;

    @Column
    String recipe;

    public Recipe() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFork_id() {
        return fork_id;
    }

    public void setFork_id(String fork_id) {
        this.fork_id = fork_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }
}
