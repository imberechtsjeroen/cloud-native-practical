package com.ezgroceries.shoppinglist.controllers;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shopping-lists", produces = "application/json", consumes = "application/json")
public class ShoppingListController {

    ShoppingListService shoppingListService = new ShoppingListService();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingList addShoppingList(@RequestBody ShoppingList shoppingList) {
        return shoppingListService.addShoppingList(shoppingList);
    }

}
