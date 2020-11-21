package com.ezgroceries.shoppinglist.repositiries;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.Repository;

public interface CocktailRepository extends Repository <CocktailEntity, UUID> {

    CocktailEntity save(CocktailEntity cocktailEntity);
    List<CocktailEntity> findByIdDrinkIn(List<String> ids);


}
