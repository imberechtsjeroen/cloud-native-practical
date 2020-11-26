package com.ezgroceries.shoppinglist.repositiries;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {
    List<ShoppingListEntity> findAll();
}
