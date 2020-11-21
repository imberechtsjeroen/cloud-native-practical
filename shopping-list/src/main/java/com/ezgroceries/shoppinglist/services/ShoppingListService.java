package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.repositiries.ShoppingListRepository;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    ShoppingListEntity shoppingListEntity = new ShoppingListEntity();

    public ShoppingListEntity save(ShoppingListEntity shoppingListEntity) {
        return shoppingListEntity;
    }

    public ShoppingListEntity findbyId(UUID Id){
        return shoppingListEntity;
    }


}
