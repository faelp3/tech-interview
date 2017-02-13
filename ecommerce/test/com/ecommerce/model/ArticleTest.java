package com.ecommerce.model;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class ArticleTest {

    public ArticleTest() {
    }

    @Test
    public void testConvertFromJson() throws IOException {
        String json = "{ \"id\": 1, \"name\": \"water\", \"price\": 100 }";

        Article reloaded = new ObjectMapper().readValue(json, Article.class);

        assertEquals(1, reloaded.getId().longValue()); 
        assertEquals("water", reloaded.getName());
        assertEquals(100, reloaded.getPriceInCents());
    }
    
    @Test
    public void testConvertToJson() throws IOException {
        Article article = new Article();
        article.setId(5L);
        article.setName("test");
        article.setPriceInCents(2);
        
        String converted = new ObjectMapper().writeValueAsString(article);
        
        String json = "{\"id\":5,\"name\":\"test\",\"price\":2}";
        assertEquals(json, converted); 
    }

}
