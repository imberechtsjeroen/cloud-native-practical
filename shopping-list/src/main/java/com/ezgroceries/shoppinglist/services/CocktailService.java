package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.controllers.CocktailDBCClient.CocktailDBClient;
import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.model.DrinkResource;
import com.ezgroceries.shoppinglist.repositiries.CocktailRepository;
import com.ezgroceries.shoppinglist.resources.CocktailDBResponse;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class CocktailService {


    private final CocktailRepository cocktailRepository;
    private final CocktailDBClient cocktailDBClient;

    public CocktailService(CocktailRepository cocktailRepository, CocktailDBClient cocktailDBClient) {
        this.cocktailRepository = cocktailRepository;
        this.cocktailDBClient = cocktailDBClient;
    }

    public List<CocktailResource> searchCocktails(String search) {
        CocktailDBResponse dBresponse = cocktailDBClient.searchCocktails(search);
        return mergeCocktails(dBresponse.getDrinks());
    }

    public List<CocktailEntity> findAllById(List<String> cocktails) {
        return cocktailRepository.findAllByEntityIdIn(cocktails.stream().map(UUID::fromString).collect(Collectors.toList()));
    }

    private List<CocktailResource> mergeCocktails(List<DrinkResource> drinks) {
        // Get all the idDrink attributes
        List<String> ids = drinks.stream().map(DrinkResource::getIdDrink).collect(Collectors.toList());

        // Get all the ones we already have from our DB, use a Map for convenient lookup
        Map<String, CocktailEntity> existingEntityMap = cocktailRepository.findByIdDrinkIn(ids).stream()
                .collect(Collectors.toMap(CocktailEntity::getIdDrink, object -> object, (existing, replacing) -> existing));

        // Stream over all the drinks, map them to the existing ones, persist a new one if not existing
        Map<String, CocktailEntity> allEntityMap = drinks.stream().map(drinkResource -> {
            CocktailEntity cocktailEntity = existingEntityMap.get(drinkResource.getIdDrink());
            if (cocktailEntity == null) {
                CocktailEntity newCocktailEntity = new CocktailEntity(UUID.randomUUID(), drinkResource.getIdDrink(), drinkResource.getStrDrink(),
                        getIngredients(drinkResource));
                cocktailEntity = cocktailRepository.save(newCocktailEntity);
            }
            return cocktailEntity;
        }).collect(Collectors.toMap(CocktailEntity::getIdDrink,object-> object, (existing, replacing) -> existing));

        // Merge drinks and our entities, transform to CocktailResource instances
        return mergeAndTransform(drinks, allEntityMap);
    }

    private List<CocktailResource> mergeAndTransform(List<DrinkResource> drinks, Map<String, CocktailEntity> allEntityMap) {
        return drinks.stream()
                .map(drinkResource -> new CocktailResource(allEntityMap.get(drinkResource.getIdDrink()).getEntityId(), drinkResource.getStrDrink(),
                        drinkResource.getStrGlass(),
                        drinkResource.getStrInstructions(), drinkResource.getStrDrinkThumb(), getListIngredients(drinkResource)))
                .collect(Collectors.toList());
    }

    private Set<String> getIngredients(DrinkResource drinkResource) {
        return Stream.of(
                drinkResource.getStrIngredient1(),
                drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(),
                drinkResource.getStrIngredient4(),
                drinkResource.getStrIngredient5(),
                drinkResource.getStrIngredient6(),
                drinkResource.getStrIngredient7()
        ).filter(StringUtils::isNotBlank).collect(Collectors.toSet());
    }
    private List<String> getListIngredients(DrinkResource drinkResource) {
        return Stream.of(
                drinkResource.getStrIngredient1(),
                drinkResource.getStrIngredient2(),
                drinkResource.getStrIngredient3(),
                drinkResource.getStrIngredient4(),
                drinkResource.getStrIngredient5(),
                drinkResource.getStrIngredient6(),
                drinkResource.getStrIngredient7()
        ).filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }


}
