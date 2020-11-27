package com.ezgroceries.shoppinglist.contracts.responses;

import java.util.UUID;

public class CocktailResponse {

    private UUID cocktailId;;

    public CocktailResponse() {
        this.cocktailId = cocktailId;
    }

    public UUID getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(UUID cocktailId) {
        this.cocktailId = cocktailId;
    }
}
