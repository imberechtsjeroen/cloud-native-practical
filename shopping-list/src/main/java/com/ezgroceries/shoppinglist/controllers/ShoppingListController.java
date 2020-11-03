package com.ezgroceries.shoppinglist.controllers;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.CocktailResponse;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.resources.ShoppingListResponse;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingListController {

    ShoppingListService shoppingListService = new ShoppingListService();

    @PostMapping
    @RequestMapping(value = "/shopping-lists", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList addShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.addShoppingList(shoppingList);
    }

    @GetMapping
    @RequestMapping(value = "/shopping-lists/{id}", produces = "application/json")
    public ShoppingListResponse get(@RequestParam String search){
            return getDummyResources();
        }

        private ShoppingListResponse getDummyResources(){

        return new ShoppingListResponse(UUID.fromString("90689338-499a-4c49-af90-f1e73068ad4f"), "Stephanie's birthday",
                            Arrays.asList("Tequila", "Triple sec", "Salt", "Blue Curaçao"));
    }


}
