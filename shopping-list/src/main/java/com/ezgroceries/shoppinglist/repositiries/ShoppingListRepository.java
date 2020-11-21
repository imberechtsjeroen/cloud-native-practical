package com.ezgroceries.shoppinglist.repositiries;

import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface ShoppingListRepository extends Repository<ShoppingListEntity, UUID> {

    public ShoppingListEntity save(ShoppingListEntity shoppingListEntity);
    public ShoppingListEntity findById(UUID Id);

}
