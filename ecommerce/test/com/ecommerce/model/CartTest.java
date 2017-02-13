package com.ecommerce.model;

import java.io.IOException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class CartTest {

    public CartTest() {
    }

    @Test
    public void testConvertFromJson() throws IOException {
        String json = "{\n"
                + "      \"id\": 1,\n"
                + "      \"items\": [\n"
                + "        { \"article_id\": 1, \"quantity\": 6 },\n"
                + "        { \"article_id\": 2, \"quantity\": 2 },\n"
                + "        { \"article_id\": 4, \"quantity\": 1 }\n"
                + "      ]\n"
                + "    }";

        Cart reloaded = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(json, Cart.class);

        assertEquals(1, reloaded.getId().longValue());
        assertEquals(3, reloaded.getItems().size());
        assertEquals(1, reloaded.getItems().get(0).getArticleId());
        assertEquals(6, reloaded.getItems().get(0).getQuantity());
        assertEquals(4, reloaded.getItems().get(2).getArticleId());
        assertEquals(1, reloaded.getItems().get(2).getQuantity());
        assertEquals(0, reloaded.getTotalInCents());
    }

    @Test
    public void testConvertToJson() throws IOException {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.getItems().add(new ItemQuantity());
        cart.setTotalInCents(10);

        String converted = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false).writeValueAsString(cart);

        String json = "{\"id\":1,\"items\":[{\"quantity\":0,\"article_id\":0}],\"total\":10}";
        assertEquals(json, converted);
    }
    
    @Test
    public void testConvertToJsonWithouListEmpty() throws IOException {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setTotalInCents(10);

        String converted = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false).
                configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false).writeValueAsString(cart);

        String json = "{\"id\":1,\"total\":10}";
        assertEquals(json, converted);
    }

}
