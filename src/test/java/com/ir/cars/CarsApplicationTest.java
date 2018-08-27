package com.ir.cars;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarsApplicationTest {

    private JSONParser parser = new JSONParser();

    private String jsonString = "{\n" +
                        "\t\"licensePlate\":\"ECP-4221\",\n" +
                        "\t\"fabricationYear\":2009,\n" +
                        "\t\"maker\":\"Ford\",\n" +
                        "\t\"carModel\": \"Focus Ghia Hatch\",\n" +
                        "\t\"color\":\"Black\",\n" +
                        "\t\"parked\":false\n" +
                        "}";

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        this.mockMvc.perform(delete("/")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnBlankList() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("[]"));
    }

    @Test
    public void shouldCreateAndRetrieveCar() throws Exception {
        this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andDo(print()).andExpect(status().isCreated());
        MvcResult result = this.mockMvc.perform(get("/ECP-4221")).andDo(print()).andExpect(status().isOk()).andReturn();

        JSONObject car = (JSONObject) parser.parse(result.getResponse().getContentAsString());

        Assert.assertEquals("ECP-4221", car.get("licensePlate"));
        Assert.assertEquals("Focus Ghia Hatch", car.get("carModel"));
        Assert.assertEquals("Ford", car.get("maker"));
        Assert.assertEquals("Black", car.get("color"));
        Assert.assertEquals(false, car.get("parked"));
        Assert.assertEquals(new Long(2009), car.get("fabricationYear"));
    }

    @Test(expected = java.lang.Exception.class)
    public void shouldNotAllowDuplicateEntry() throws Exception {
        this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andDo(print()).andExpect(status().isCreated());
        this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonString));
    }

    @Test
    public void shouldParkAndCheckoutCar() throws Exception {
        this.mockMvc.perform(post("/register").contentType(MediaType.APPLICATION_JSON).content(jsonString)).andDo(print()).andExpect(status().isCreated());
        this.mockMvc.perform(put("/park/ECP-4221")).andDo(print()).andExpect(status().isNoContent());
        MvcResult parkResult = this.mockMvc.perform(get("/ECP-4221")).andDo(print()).andExpect(status().isOk()).andReturn();
        JSONObject car = (JSONObject) parser.parse(parkResult.getResponse().getContentAsString());
        Assert.assertEquals(true, car.get("parked"));

        this.mockMvc.perform(put("/checkout/ECP-4221")).andDo(print()).andExpect(status().isNoContent());
        MvcResult checkoutResult = this.mockMvc.perform(get("/ECP-4221")).andDo(print()).andExpect(status().isOk()).andReturn();
        car = (JSONObject) parser.parse(checkoutResult.getResponse().getContentAsString());
        Assert.assertEquals(false, car.get("parked"));
    }
}
