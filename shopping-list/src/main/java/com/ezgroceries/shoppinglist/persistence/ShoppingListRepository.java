package com.ezgroceries.shoppinglist.persistence;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface ShoppingListRepository extends CrudRepository<ShoppingListEntity, UUID> {
    List<ShoppingListEntity> findAll();
}
