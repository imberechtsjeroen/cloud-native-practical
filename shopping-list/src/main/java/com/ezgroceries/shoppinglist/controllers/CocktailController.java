package com.ezgroceries.shoppinglist.controllers;

import com.ezgroceries.shoppinglist.controllers.CocktailDBCClient.CocktailDBClient;
import com.ezgroceries.shoppinglist.model.DrinkResource;
import com.ezgroceries.shoppinglist.resources.CocktailDBResponse;
import com.ezgroceries.shoppinglist.resources.CocktailResponse;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.services.CocktailService;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @PostMapping
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

    private List<CocktailResource> getDummyResources() {
        return Arrays.asList(
                new CocktailResource(
                        UUID.fromString("23b3d85a-3928-41c0-a533-6538a71e17c4"), "Margerita",
                        "Cocktail glass",
                        "Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",
                        Arrays.asList("Tequila", "Triple sec", "Lime juice", "Salt")),
                new CocktailResource(
                        UUID.fromString("d615ec78-fe93-467b-8d26-5d26d8eab073"), "Blue Margerita",
                        "Cocktail glass",
                        "Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",
                        Arrays.asList("Tequila", "Blue Curacao", "Lime juice", "Salt")));
    }

}



