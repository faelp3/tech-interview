package com.ecommerce.model;

import java.io.IOException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class ItemQuantityTest {

    public ItemQuantityTest() {
    }

    @Test
    public void testConvertFromJson() throws IOException {
        String json = "{\"article_id\":1,\"quantity\":6}";

        ItemQuantity reloaded = new ObjectMapper().readValue(json, ItemQuantity.class);

        assertEquals(1, reloaded.getArticleId());
        assertEquals(6, reloaded.getQuantity());
    }

    @Test
    public void testConvertToJson() throws IOException {
        ItemQuantity item = new ItemQuantity();
        item.setArticleId(1);
        item.setQuantity(6);
        
        String converted = new ObjectMapper().writeValueAsString(item);

        String json = "{\"quantity\":6,\"article_id\":1}";
        assertEquals(json, converted);
    }

}
