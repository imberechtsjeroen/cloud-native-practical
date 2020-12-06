package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.contracts.external.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.DrinkResource;
import com.ezgroceries.shoppinglist.persistence.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.CocktailRepository;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface CocktailDBCClient {

    @Component
    @FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1", fallback = CocktailDBClient.CocktailDBClientFallback.class)
    public interface CocktailDBClient {

        @GetMapping(value = "search.php")
        CocktailDBResponse searchCocktails(@RequestParam("s") String search);

        @Component
        class CocktailDBClientFallback implements CocktailDBClient {

            private final CocktailRepository cocktailRepository;

            public CocktailDBClientFallback(CocktailRepository cocktailRepository) {
                this.cocktailRepository = cocktailRepository;
            }

            @Override
            public CocktailDBResponse searchCocktails(String search) {
                List<CocktailEntity> cocktailEntities = cocktailRepository.findByNameContainingIgnoreCase(search);

                CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
                cocktailDBResponse.setDrinks(cocktailEntities.stream().map(cocktailEntity -> {
                    DrinkResource drinkResource = new DrinkResource();
                    drinkResource.setIdDrink(cocktailEntity.getIdDrink());
                    drinkResource.setStrDrink(cocktailEntity.getName());
                    drinkResource.setStrDrinkThumb(cocktailEntity.getImage());
                    drinkResource.setStrGlass(cocktailEntity.getGlass());
                    drinkResource.setStrInstructions(cocktailEntity.getInstructions());
                    HashSet<String> set = new HashSet<>(cocktailEntity.getIngredients());
                    Iterator ingred = set.iterator();
                    if (ingred.hasNext()) {
                        drinkResource.setStrIngredient1((String) ingred.next());
                    }
                    if (ingred.hasNext()) {
                        drinkResource.setStrIngredient2((String) ingred.next());
                    }
                    if (ingred.hasNext()) {
                        drinkResource.setStrIngredient3((String) ingred.next());
                    }
                    if (ingred.hasNext()) {
                        drinkResource.setStrIngredient4((String) ingred.next());
                    }
                    if (ingred.hasNext()) {
                        drinkResource.setStrIngredient5((String) ingred.next());
                    }
                    return drinkResource;
                }).collect(Collectors.toList()));

                return cocktailDBResponse;
            }
        }
    }
}



