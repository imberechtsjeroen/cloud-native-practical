package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.resources.ShoppingList;
import java.util.UUID;

public class ShoppingListService {

    ShoppingList shoppingList = new ShoppingList();

    public ShoppingList addShoppingList(ShoppingList shoppingList){
        shoppingList.setShoppingListId(UUID.randomUUID());
        return shoppingList;
    }
}
