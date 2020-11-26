package com.ezgroceries.shoppinglist.controllers;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.CocktailResponse;
import com.ezgroceries.shoppinglist.resources.ShoppingList;
import com.ezgroceries.shoppinglist.resources.ShoppingListResponse;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
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
@RequestMapping(value = "/shopping-lists", produces = "application/json")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    public ShoppingListController(ShoppingListService shoppingListService) {
        this.shoppingListService = shoppingListService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ShoppingListResponse addShoppingList(@RequestBody Map<String, String> shoppingListBody) {
        ShoppingListResponse shoppingList = shoppingListService.addShoppingList(shoppingListBody.get("name"));
        return shoppingList;
    }

    @GetMapping (value = "/{id}")
    public ShoppingListResponse getList(@PathVariable String id) {
            return shoppingListService.getList(id);}


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ShoppingListResponse> getAllShoppingLists() {
        return shoppingListService.getAllShoppingLists();
    }

    @PostMapping(value = "/{id}/cocktails",consumes = "application/json" )
    @ResponseStatus(HttpStatus.OK)
    public ShoppingListResponse addCocktails(@PathVariable String id, @RequestBody List<Map<String, String>> shoppingListBody) {
        List<String> cocktails = shoppingListBody.stream().map(map -> map.get("cocktailId")).collect(Collectors.toList());
        shoppingListService.addCocktails(id, cocktails);
        return shoppingListService.getList(id);    }

}








