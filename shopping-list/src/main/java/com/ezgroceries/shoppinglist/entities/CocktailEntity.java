package com.ezgroceries.shoppinglist.entities;

import com.ezgroceries.shoppinglist.converters.StringSetConverter;
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

    public CocktailEntity(){}

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
