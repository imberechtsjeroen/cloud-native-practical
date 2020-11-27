package com.ezgroceries.shoppinglist.persistence;

import com.ezgroceries.shoppinglist.utils.StringSetConverter;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cocktail")
public class CocktailEntity {

    @Id
    @Column(name="id")
    private UUID entityId;

    private String name;

    @Column(name="id_drink")
    private String idDrink;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailEntity() {
        // default constructor needed to save entities
    }

    public CocktailEntity(String name, String idDrink, Set<String> ingredients){
        this.name = name;
        this.idDrink = idDrink;
        this.ingredients = ingredients;
        this.entityId = UUID.randomUUID();
    }

    public CocktailEntity(UUID id, String idDrink, String name, Set<String> ingredients) {
        this.entityId = id;
        this.idDrink = idDrink;
        this.name = name;
        this.ingredients = ingredients;
    }


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

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
