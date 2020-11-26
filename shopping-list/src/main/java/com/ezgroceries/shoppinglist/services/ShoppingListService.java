package com.ezgroceries.shoppinglist.services;

import com.ezgroceries.shoppinglist.entities.CocktailEntity;
import com.ezgroceries.shoppinglist.entities.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.HandledException;
import com.ezgroceries.shoppinglist.repositiries.ShoppingListRepository;
import com.ezgroceries.shoppinglist.resources.ShoppingListResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final CocktailService cocktailService;
    private static final Logger logger = Logger.getLogger(String.valueOf(ShoppingListService.class));


    public ShoppingListService(ShoppingListRepository shoppingListRepository, CocktailService cocktailService) {
        this.shoppingListRepository = shoppingListRepository;
        this.cocktailService = cocktailService;
    }


    public ShoppingListResponse getList(String id) {
        Optional<ShoppingListEntity> shoppingList = shoppingListRepository.findById(UUID.fromString(id));
        try {
            try {
                return shoppingList.map(this::mapShoppingListEntity).orElseThrow(() -> new Exception("Shopping list not found"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        }
        return null;
    }

    public List<ShoppingListResponse> getAllShoppingLists() {
        List<ShoppingListEntity> entity = shoppingListRepository.findAll();
        return entity.stream().map(this::mapShoppingListEntity).collect(Collectors.toList());
    }

    private ShoppingListResponse mapShoppingListEntity(ShoppingListEntity shoppingListEntity) {
        List<CocktailEntity> cocktails = shoppingListEntity.getCocktails();
        List<String> ids = cocktails.stream().map(CocktailEntity::getEntityId).map(UUID::toString).collect(Collectors.toList());
        List<String> ingredients = cocktailService.findAllById(ids).stream().map(CocktailEntity::getIngredients).flatMap(Set::stream).distinct().collect(Collectors.toList());
        return new ShoppingListResponse(shoppingListEntity.getEntityId(), shoppingListEntity.getName(), ingredients);}

    public ShoppingListResponse addShoppingList(String name) {
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity(name);
        shoppingListRepository.save(shoppingListEntity);
        return new ShoppingListResponse(shoppingListEntity.getEntityId(), shoppingListEntity.getName());
    }

    public ShoppingListResponse addCocktails(String shoppingListId, List<String> cocktailIds) {
        List<CocktailEntity> cocktailEntities = cocktailService.findAllById(cocktailIds);
        try{
            try{
               return shoppingListRepository.findById(UUID.fromString(shoppingListId)).map(shoppingList -> {
            shoppingList.setCocktails(cocktailEntities);
            shoppingListRepository.save(shoppingList);
            return mapShoppingListEntity(shoppingList);
            }).orElseThrow(() -> new Exception("Shopping list not found"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        }
        return null;
    }
    }
