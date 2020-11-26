package com.ezgroceries.shoppinglist.resources;

import java.util.List;
import java.util.UUID;

public class ShoppingListResponse {

    private UUID shoppingListId;
    private String name;
    private List<String> ingredients;


    public ShoppingListResponse(UUID shoppingListId, String name, List<String> ingredients) {
        this.shoppingListId = shoppingListId;
        this.name = name;
        this.ingredients = ingredients;
    }

    public ShoppingListResponse() {
     }

    public ShoppingListResponse(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public ShoppingListResponse(UUID shoppingListId, String name) {
        this.shoppingListId = shoppingListId;
        this.name = name;
    }

    public void addIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public String getName() {
        return name;
    }
}
