package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.contracts.responses.CocktailResponse;
import com.ezgroceries.shoppinglist.contracts.resources.CocktailResource;
import com.ezgroceries.shoppinglist.services.CocktailService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CocktailController {

    private final CocktailService cocktailService;

    private CocktailController(CocktailService cocktailService){this.cocktailService = cocktailService;}


    @GetMapping
    @RequestMapping(value = "/cocktails", produces = "application/json")
    public List<CocktailResource> get(@RequestParam String search) {
        return cocktailService.searchCocktails(search);
        }

    @RequestMapping(value = "/shopping-lists/{id}/cocktails", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
      public List<CocktailResponse> addCocktailResource(@RequestBody List<CocktailResource>  cocktailResources){
        List<CocktailResponse> cocktailResponses = new ArrayList<>();
        int size = cocktailResources.size();
        for (int i = 0; i < size; i++) {
            CocktailResource cocktailResource = cocktailResources.get(i);
            CocktailResponse cocktailResponse = new CocktailResponse();
            cocktailResponse.setCocktailId(cocktailResource.getCocktailId());
            cocktailResponses.add(cocktailResponse);
        }
        return cocktailResponses;
    }

}



