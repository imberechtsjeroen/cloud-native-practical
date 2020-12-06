package com.ezgroceries.shoppinglist;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static java.util.Optional.of;


import com.ezgroceries.shoppinglist.persistence.CocktailEntity;
import com.ezgroceries.shoppinglist.persistence.ShoppingListEntity;
import com.ezgroceries.shoppinglist.persistence.ShoppingListRepository;
import com.ezgroceries.shoppinglist.contracts.responses.ShoppingListResponse;
import com.ezgroceries.shoppinglist.services.CocktailService;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

public class ShoppingListServiceTest {
    private ShoppingListService service;
    private ShoppingListRepository repository = mock(ShoppingListRepository.class);
    private CocktailService cocktailService = mock(CocktailService.class);

    @BeforeEach
    public void init() {
        service = new ShoppingListService(repository, cocktailService);
    }

    @Test
    public void testCreateShoppingList() {
        when(repository.save(any(ShoppingListEntity.class))).thenAnswer(a -> a.getArgument(0));
        ArgumentCaptor<ShoppingListEntity> argumentCaptor = ArgumentCaptor.forClass(ShoppingListEntity.class);

        ShoppingListResponse created = service.addShoppingList("myList");
        assertThat("Name is created", created.getName(), equalTo("myList"));

        verify(repository).save(argumentCaptor.capture());
        assertThat("Id is created", created.getShoppingListId(), equalTo(argumentCaptor.getValue().getEntityId()));
    }


    @Test
    public void testAddCocktails() {
        UUID id = UUID.randomUUID();
        UUID Russianuuid = UUID.randomUUID();
        UUID Margeritauuid = UUID.randomUUID();
        List<String> cocktails = Arrays.asList(Russianuuid.toString(), Margeritauuid.toString());
        Set<String> ingredients = new HashSet<>();
        ingredients.add("Vodka");
        ingredients.add("Pineapple");
        ingredients = Collections.unmodifiableSet(ingredients);
        ShoppingListEntity entity = new ShoppingListEntity("myFavourite");
        when(cocktailService.findAllById(cocktails)).thenReturn(
                Arrays.asList(
                        new CocktailEntity(Russianuuid, "ONE", "Moscow", ingredients, "thinyGlass", "shakeIt", "Lady"),
                        new CocktailEntity(Margeritauuid, "TWO", "Philly", ingredients, "small", "Slightly stir it up", "Monsieur")));
        when(repository.findById(id)).thenReturn(of(entity));
        service.addCocktails(id.toString(), cocktails);
        verify(repository).save(entity);
    }
}


