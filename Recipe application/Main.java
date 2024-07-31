import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Recipe> recipes = new ArrayList<>();
    private static final ArrayList<Bookmark> bookmarks = new ArrayList<>();
    private static final ArrayList<CommunityInteraction> communityInteractions = new ArrayList<>();
    private static int userId;
    private static int recipeIdCounter = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Recipe Star");

        System.out.print("Enter your user ID: ");
        userId = getIntInput();

        while (true) {
            printMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addRecipe();
                case 2 -> searchRecipe();
                case 3 -> displayRecipes();
                case 4 -> bookmarkRecipe();
                case 5 -> showBookmarks();
                case 6 -> rateRecipe();
                case 7 -> switchUser();
                case 8 -> communityInteraction();
                case 9 -> showInteractions();
                case 10 -> {
                    System.out.println("Thank You!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add a new Recipe");
        System.out.println("2. Search for a recipe");
        System.out.println("3. Display Recipes");
        System.out.println("4. Bookmark Recipe");
        System.out.println("5. Show Bookmarks");
        System.out.println("6. Rate a recipe");
        System.out.println("7. Switch User");
        System.out.println("8. Community Interaction");
        System.out.println("9. Show Interactions");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addRecipe() {
        System.out.print("Enter the name of the recipe: ");
        String name = sc.next();
        System.out.print("Enter the category: ");
        String category = sc.next();
        System.out.print("Enter the ingredients: ");
        String ingredients = sc.next();

        recipeIdCounter++;
        Recipe recipe = new Recipe(name, category, ingredients, recipeIdCounter);
        recipes.add(recipe);

        System.out.println("\nYour Recipe Added Successfully\n");
    }

    private static void searchRecipe() {
        System.out.print("\nEnter the recipe ID to search: ");
        int recipeId = getIntInput();

        for (Recipe recipe : recipes) {
            if (recipe.getId() == recipeId) {
                recipe.display();
                return;
            }
        }
        System.out.println("Recipe not found!");
    }

    private static void displayRecipes() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        for (Recipe recipe : recipes) {
            recipe.display();
        }
    }

    private static void bookmarkRecipe() {
        System.out.print("\nEnter the Recipe ID to Bookmark: ");
        int recipeId = getIntInput();

        for (Recipe recipe : recipes) {
            if (recipe.getId() == recipeId) {
                Bookmark bookmark = new Bookmark(recipe);
                bookmarks.add(bookmark);
                System.out.println("Bookmarked Successfully");
                return;
            }
        }
        System.out.println("Recipe not found!");
    }

    private static void showBookmarks() {
        if (bookmarks.isEmpty()) {
            System.out.println("No bookmarks available.");
            return;
        }

        for (Bookmark bookmark : bookmarks) {
            bookmark.display();
        }
    }

    private static void rateRecipe() {
        displayRecipes();
        System.out.print("Select a recipe by ID to rate: ");
        int recipeId = getIntInput();

        for (Recipe recipe : recipes) {
            if (recipe.getId() == recipeId) {
                System.out.print("Enter your rating for the recipe (1-10): ");
                int rating = getIntInput();
                if (rating >= 1 && rating <= 10) {
                    recipe.setRating(rating);
                    System.out.println("Rating Added");
                } else {
                    System.out.println("Invalid rating! Please enter a value between 1 and 10.");
                }
                return;
            }
        }
        System.out.println("Recipe not found!");
    }

    private static void switchUser() {
        System.out.print("Enter your new user ID: ");
        userId = getIntInput();
        System.out.println("User switched successfully.");
    }

    private static void communityInteraction() {
        System.out.print("\nEnter the Recipe ID to interact: ");
        int recipeId = getIntInput();

        for (Recipe recipe : recipes) {
            if (recipe.getId() == recipeId) {
                System.out.print("Enter your Tips: ");
                String tips = sc.next();

                CommunityInteraction interaction = new CommunityInteraction(recipe, tips);
                communityInteractions.add(interaction);
                System.out.println("Interaction added successfully.");
                return;
            }
        }
        System.out.println("Recipe not found!");
    }

    private static void showInteractions() {
        if (communityInteractions.isEmpty()) {
            System.out.println("No interactions available.");
            return;
        }

        for (CommunityInteraction interaction : communityInteractions) {
            interaction.display();
        }
    }

    private static int getIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Invalid input! Please enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}

class Recipe {
    private final String recipeName;
    private final String category;
    private final String ingredients;
    private final int id;
    private int rating;

    public Recipe(String recipeName, String category, String ingredients, int id) {
        this.recipeName = recipeName;
        this.category = category;
        this.ingredients = ingredients;
        this.id = id;
        this.rating = 0;
    }

    public int getId() {
        return id;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Category: " + category);
        System.out.println("Ingredients: " + ingredients);
        System.out.println("Rating: " + rating);
        System.out.println();
    }
}

class Bookmark {
    private final String recipeName;
    private final int id;

    public Bookmark(Recipe recipe) {
        this.recipeName = recipe.getId() + ": " + recipe.toString();
        this.id = recipe.getId();
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Recipe Name: " + recipeName);
    }
}

class CommunityInteraction {
    private final String recipeName;
    private final int id;
    private final String tips;

    public CommunityInteraction(Recipe recipe, String tips) {
        this.recipeName = recipe.getId() + ": " + recipe.toString();
        this.id = recipe.getId();
        this.tips = tips;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Tips: " + tips);
        System.out.println();
    }
}
