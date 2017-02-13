package com.ecommerce.model;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class DiscountTest {

    public DiscountTest() {
    }

    @Test
    public void testConvertFromJson() throws IOException {
        String json = "{ \"article_id\": 2, \"type\": \"amount\", \"value\": 25 }";

        Discount reloaded = new ObjectMapper().readValue(json, Discount.class);

        assertEquals(2, reloaded.getArticleId());
        assertEquals(Discount.Type.amount, reloaded.getType());
        assertEquals(25, reloaded.getValueInCents());
    }

    @Test
    public void testConvertToJson() throws IOException {
        Discount disc = new Discount();
        disc.setArticleId(1);
        disc.setType(Discount.Type.percentage);
        disc.setValueInCents(100);

        String converted = new ObjectMapper().writeValueAsString(disc);

        assertTrue(converted.contains("\"type\":\"percentage\""));
        assertTrue(converted.contains("\"article_id\":1"));
        assertTrue(converted.contains("\"value\":100"));
    }

    @Test
    public void testCalculate() {
        assertEquals(100 - 50, new Discount(1, Discount.Type.amount, 25).calculate(100, 2));
        assertEquals(180, new Discount(1, Discount.Type.percentage, 10).calculate(200, 2));
        assertEquals(699, new Discount(1, Discount.Type.percentage, 30).calculate(999, 2));
    }

}
