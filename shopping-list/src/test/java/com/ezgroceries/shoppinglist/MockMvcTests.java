package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.controllers.CocktailDBCClient.CocktailDBClient;
import com.ezgroceries.shoppinglist.model.DrinkResource;
import com.ezgroceries.shoppinglist.resources.CocktailDBResponse;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
    @AutoConfigureMockMvc
    @ComponentScan({ "com.ezgroceries.shoppinglist", "config:" })
    public class MockMvcTests {

        @Autowired
        private MockMvc mockMvc;

        final String expectedName = "Stephanie's birthday";

    @MockBean
    private CocktailDBClient cocktailDBClient;

    /**
         * Test a GET to /shoppingList.
         * <p>
         * We tell the request that we will accept HTML then run the request by calling
         *
         * @throws Exception
         *             If anything fails.
         */
        @Test
        public void getAllShoppingListsTest() throws Exception {

            this.mockMvc //
                    .perform(get("/shopping-lists") //
                            .accept(MediaType.parseMediaType("application/json"))) //
                    .andExpect(status().isOk()) //
                    .andExpect(content().contentType("application/json"));
        }

        /**
         * Test a GET to specific /shoppinglist/{id}.
         * @throws Exception
         *             If anything fails.
         */
        @Test
        public void getOneShoppingListTest() throws Exception {

            this.mockMvc.perform(get("/shopping-lists/5") //
                    .accept(MediaType.parseMediaType("application/json"))) //
                    .andExpect(status().isOk()) //
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.name").value(expectedName));

        }

    /**
     * Test a POST to /shoppingList.
     * <p>
     * We tell the request that we will accept HTML then run the request by calling
     *
     * @throws Exception
     *             If anything fails.
     */
    @Test
    public void addAllShoppingListsTest() throws Exception {

        this.mockMvc //
                .perform(post("/shopping-lists") //
                        .accept(MediaType.parseMediaType("application/json"))) //
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("application/json"));

    }


    /**
     * Test a GET to /cocktails.
     * <p>
     * We tell the request that we will accept HTML then run the request by calling
     *
     * @throws Exception
     *             If anything fails.
     */
    @Test
    public void getAllCocktailsTest() throws Exception {

        given(cocktailDBClient.searchCocktails("Russian")).willReturn(getMockedCocktails());
        this.mockMvc //
                .perform(get("/cocktails") //
                        .param("search", "Russian")//
                        .accept(MediaType.parseMediaType("application/json"))) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType("application/json"));
    }

    private CocktailDBResponse getMockedCocktails() {
        List<DrinkResource> drinks = new ArrayList<>();
        DrinkResource drinkResource = new DrinkResource();
        drinkResource.setStrDrink("Crazy Russian");
        drinkResource.setStrGlass("Moscow glass");
        drinkResource.setStrInstructions("shake it");
        drinkResource.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg");
        drinkResource.setStrIngredient1("Wodka");
        drinkResource.setStrIngredient2("Triple sec");
        drinkResource.setStrIngredient3("Lime juice");
        drinkResource.setStrIngredient4("Salt");
        drinks.add(drinkResource);
        drinkResource = new DrinkResource();
        drinkResource.setStrDrink("Poetin's favourite");
        drinkResource.setStrGlass("Washington ass");
        drinkResource.setStrInstructions("Shake it 5 mins");
        drinkResource.setStrDrinkThumb("https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg");
        drinkResource.setStrIngredient1("Wodka");
        drinkResource.setStrIngredient2("Blue Curacao");
        drinkResource.setStrIngredient3("Lime juice");
        drinkResource.setStrIngredient4("Salt");
        drinks.add(drinkResource);
        CocktailDBResponse cocktailDBResponse = new CocktailDBResponse();
        cocktailDBResponse.setDrinks(drinks);
        return cocktailDBResponse;
    }

    /**
     * Test a POST to /shoppingList/{id}/cocktails.
     * <p>
     * We tell the request that we will accept HTML then run the request by calling
     *
     * @throws Exception
     *             If anything fails.
     */
    @Test
    public void addCocktailsTest() throws Exception {

        this.mockMvc //
                .perform(post("/cocktails") //
                        .accept(MediaType.parseMediaType("application/json"))) //
                .andExpect(status().is2xxSuccessful()) //
                .andExpect(content().contentType("application/json"));

    }



    }



