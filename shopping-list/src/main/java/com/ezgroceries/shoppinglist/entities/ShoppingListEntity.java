package com.ezgroceries.shoppinglist.entities;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="shopping_list")public class ShoppingListEntity {

    @Id
    @Column(name="id")
    private UUID entityId;

    private String name;

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
}
