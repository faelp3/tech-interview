package com.ecommerce;

import com.ecommerce.model.ArticleCarts;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class MainTest {

    public MainTest() {
    }

    @Test
    public void testExecuteLevel1() throws Exception {
        String json = "{\"carts\":[{\"id\":1,\"total\":2000},{\"id\":2,\"total\":1400},{\"id\":3,\"total\":0}]}";

        assertEquals(json, Main.executeLevel1());
    }

    @Test
    public void testExecuteLevel2() throws Exception {
        String json = "{\"carts\":[{\"id\":1,\"total\":2000},{\"id\":2,\"total\":1800},{\"id\":3,\"total\":800}]}";

        assertEquals(json, Main.executeLevel2());
    }

    @Test
    public void testExecuteLevel3() throws Exception {
        String json = "{\"carts\":[{\"id\":1,\"total\":2350},{\"id\":2,\"total\":1775},{\"id\":3,\"total\":1798},{\"id\":4,\"total\":1083},{\"id\":5,\"total\":1196}]}";

        assertEquals(json, Main.executeLevel3());
    }

    @Test
    public void testGetCartsFromDataInput() throws Exception {
        ArticleCarts arc = Main.getCartsFromDataInput(1);

        assertEquals(4, arc.getArticles().size());
        assertEquals(3, arc.getCarts().size());
        assertEquals(0, arc.getDeliveryFees().size());
        assertEquals(0, arc.getDiscounts().size());
        assertEquals("water", arc.getArticles().get(0).getName());
        assertEquals("tea", arc.getArticles().get(3).getName());
        assertEquals(1, arc.getArticles().get(0).getId().longValue());
        assertEquals(3, arc.getArticles().get(2).getId().longValue());

        arc = Main.getCartsFromDataInput(2);

        assertEquals(4, arc.getArticles().size());
        assertEquals(3, arc.getCarts().size());
        assertEquals(3, arc.getDeliveryFees().size());
        assertEquals(0, arc.getDiscounts().size());

        arc = Main.getCartsFromDataInput(3);

        assertEquals(8, arc.getArticles().size());
        assertEquals(5, arc.getCarts().size());
        assertEquals(3, arc.getDeliveryFees().size());
        assertEquals(5, arc.getDiscounts().size());

    }

}
