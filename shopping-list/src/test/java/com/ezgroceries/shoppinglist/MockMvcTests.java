package com.ezgroceries.shoppinglist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
    @AutoConfigureMockMvc
    @ComponentScan({ "com.ezgroceries.shoppinglist", "config:" })
    public class MockMvcTests {

        @Autowired
        private MockMvc mockMvc;

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
                    .andExpect(content().contentType("application/json"));
        }

    /**
     * Test a GET to /shoppingList.
     * <p>
     * We tell the request that we will accept HTML then run the request by calling
     *
     * @throws Exception
     *             If anything fails.
     */
    @Test
    public void getAllCocktailsTest() throws Exception {

        this.mockMvc //
                .perform(get("/cocktails") //
                        .accept(MediaType.parseMediaType("application/json"))) //
                .andExpect(status().isOk()) //
                .andExpect(content().contentType("application/json"));
    }




    }



