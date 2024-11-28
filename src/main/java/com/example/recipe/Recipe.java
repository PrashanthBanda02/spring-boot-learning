package com.example.recipe;

import java.util.ArrayList;

public class Recipe {
    private int recipeId;
    private String recipeName;
    private String recipeType;
    private ArrayList<String> ingredients;

    public Recipe(int recipeId, String recipeName, String recipeType, ArrayList<String> ingredients) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.recipeType = recipeType;
        this.ingredients = ingredients;
    }

    public int getRecipeId() {return this.recipeId;}
    public String getRecipeName() {return this.recipeName;}
    public String getRecipeType() {return this.recipeType;}
    public ArrayList<String> getIngredients() {return this.ingredients;}
    public void setRecipeId(int recipeId) {this.recipeId = recipeId;}
    public void setRecipeName(String recipeName) {this.recipeName = recipeName;}
    public void setRecipeType(String recipeType) {this.recipeType = recipeType;}
    public void setIngredients(ArrayList<String> ingredients) {this.ingredients = ingredients;}

}