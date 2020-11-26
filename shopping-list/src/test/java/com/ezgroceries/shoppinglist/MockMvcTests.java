package com.ezgroceries.shoppinglist;

import com.ezgroceries.shoppinglist.controllers.CocktailDBCClient.CocktailDBClient;
import com.ezgroceries.shoppinglist.model.DrinkResource;
import com.ezgroceries.shoppinglist.resources.CocktailDBResponse;
import com.ezgroceries.shoppinglist.resources.CocktailResource;
import com.ezgroceries.shoppinglist.resources.ShoppingListResponse;
import com.ezgroceries.shoppinglist.services.CocktailService;
import com.ezgroceries.shoppinglist.services.ShoppingListService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
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
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
    @AutoConfigureMockMvc
    @ComponentScan({ "com.ezgroceries.shoppinglist", "config:" })
    public class MockMvcTests {

        @Autowired
        private MockMvc mockMvc;


    @MockBean
    private CocktailService cocktailService;
    @MockBean
    private ShoppingListService shoppingListService;


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

            given(cocktailService.searchCocktails("Russian")).willReturn(getMockedCocktails());
            mockMvc //
                    .perform(get("/shopping-lists") //
                            .accept(MediaType.parseMediaType("application/json"))) //
                    .andExpect(status().isOk()) //
                    .andExpect(content().contentType("application/json"))
                            .andExpect(jsonPath("$[1].ingredients[0]").value("Wodka"))
                    .andExpect(jsonPath("$[1].ingredients[1]").value("Blue Curacao"))
                    .andExpect(jsonPath("$[1].ingredients[2]").value("Lime juice"))
                    .andExpect(jsonPath("$[1].ingredients[3]").value("Salt"));
            verify(cocktailService).searchCocktails("Russian");

        }

        /**
         * Test a GET to specific /shoppinglist/{id}.
         * @throws Exception
         *             If anything fails.
         */
        @Test
        public void getOneShoppingListTest() throws Exception {
            given(shoppingListService.getList("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915")).willReturn(getOneShoppingListMock());
            this.mockMvc.perform(get("/shopping-lists/eb18bb7c-61f3-4c9f-981c-55b1b8ee8915") //
                    .accept(MediaType.parseMediaType("application/json"))) //
                    .andExpect(status().isOk()) //
                    .andExpect(jsonPath("$.shoppingListId").value("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"))
                    .andExpect(content().contentType("application/json"))
                    .andExpect(jsonPath("$.ingredients[3]").value("Salt"))
                    .andExpect(jsonPath("$.name").value("me, myself and I"));
            verify(shoppingListService).getList("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915");
        }

    private ShoppingListResponse getOneShoppingListMock() {
        ShoppingListResponse shoppingList = getShoppingListMock();
        shoppingList.setIngredients(Arrays.asList("Vodka",
                "Triple sec",
                "Lime juice",
                "Salt",
                "Blue Curacao"));
        return shoppingList;
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
    public void createShoppingListsTest() throws Exception {

        given(shoppingListService.addShoppingList("me, myself and I")).willReturn(getShoppingListMock());
        mockMvc
                .perform(post("/shopping-lists")
                        .accept(MediaType.parseMediaType("application/json"))
                        .content("{\"name\": \"me, myself and I\"}")
                        .contentType(MediaType.parseMediaType("application/json"))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("shoppingListId").value("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"))
                .andExpect(jsonPath("name").value("me, myself and I"));
        verify(shoppingListService).addShoppingList("me, myself and I");
    }

    private ShoppingListResponse getShoppingListMock() {
        return new ShoppingListResponse(UUID.fromString("eb18bb7c-61f3-4c9f-981c-55b1b8ee8915"), "me, myself and I");
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

        given(cocktailService.searchCocktails("Russian")).willReturn(getMockedCocktails());
        this.mockMvc //
                .perform(get("/cocktails") //
                        .param("search", "Russian")//
                        .accept(MediaType.parseMediaType("application/json"))) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].ingredients[2]").value("Lime juice"))
                .andExpect(jsonPath("$[1].ingredients[0]").value("Wodka"));

    }

    private List<CocktailResource> getMockedCocktails() {
        return Arrays.asList(
                new CocktailResource(UUID.randomUUID(), "Crazy Russian", "Moscow glass", "",
                        "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg", Arrays.asList(
                        "Wodka", "Triple sec", "Lime juice", "Salt"
                )),
                new CocktailResource(UUID.randomUUID(), "Putin's favourite", "Washington ass", "",
                        "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg", Arrays.asList(
                        "Wodka", "Blue Curacao", "Lime juice", "Salt"
                )));
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



