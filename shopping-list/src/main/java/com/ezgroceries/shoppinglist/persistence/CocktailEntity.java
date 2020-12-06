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

    private String glass;

    private String instructions;

    @Column(name = "image_link")
    private String image;

    public CocktailEntity() {
        // default constructor needed to save entities
    }

    public CocktailEntity(UUID id, String idDrink, String name, Set<String> ingredients, String glass, String instructions, String image) {
        this.entityId = id;
        this.idDrink = idDrink;
        this.name = name;
        this.ingredients = ingredients;
        this.glass = glass;
        this.instructions = instructions;
        this.image = image;
    }

    public CocktailEntity(String name, String idDrink, Set<String> ingredients){
        this.name = name;
        this.idDrink = idDrink;
        this.ingredients = ingredients;
        this.entityId = UUID.randomUUID();
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

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
