package com.ezgroceries.shoppinglist.contracts.external;

import com.ezgroceries.shoppinglist.model.DrinkResource;
import java.util.List;

public class CocktailDBResponse {

    private List<DrinkResource> drinks;

    public List<DrinkResource> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkResource> drinks) {
        this.drinks = drinks;
    }
}
