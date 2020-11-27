package com.ezgroceries.shoppinglist.persistence;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="shopping_list")
public class ShoppingListEntity {

    @Id
    @Column(name="id")
    private UUID entityId;

    private String name;

    @ManyToMany
    @JoinTable( name="COCKTAIL_SHOPPING_LIST",
            joinColumns = @JoinColumn(name="SHOPPING_LIST_ID"),
            inverseJoinColumns = @JoinColumn(name = "COCKTAIL_ID"))
    private List<CocktailEntity> cocktails;


    public ShoppingListEntity(String name){
        this.name = name;
        this.entityId =  UUID.randomUUID();
    }

    public ShoppingListEntity(){}

    public UUID getEntityId() {
        return entityId;
    }

    public void setEntityId(UUID entityId) {
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CocktailEntity> getCocktails() {
        return cocktails;
    }

    public void setCocktails(List<CocktailEntity> cocktails) {
        this.cocktails = cocktails;
    }
}
