package com.ezgroceries.shoppinglist.resources;

import java.util.UUID;

public class ShoppingList {

    private UUID shoppingListId;
    private String name;

    public ShoppingList(){
        this.shoppingListId = (UUID.randomUUID());
        this.name = name;
    }

    public UUID getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(UUID shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
