package com.ezgroceries.shoppinglist.contracts.resources;

import java.util.UUID;

public class ShoppingListResource {

    private UUID shoppingListId;
    private String name;

    public ShoppingListResource(){
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
