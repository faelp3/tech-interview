package com.ecommerce.model;

import com.ecommerce.Main;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author rafael
 */
public class ArticleCartsTest {

    public ArticleCartsTest() {
    }

    @Test
    public void testCalculateTotals() throws IOException {
        ArticleCarts data = Main.getCartsFromDataInput(1);
        data = spy(data);

        data.calculateTotalsCarts();

        assertEquals(2000, data.getCarts().get(0).getTotalInCents());
        assertEquals(1400, data.getCarts().get(1).getTotalInCents());
        assertEquals(0, data.getCarts().get(2).getTotalInCents());

        verify(data, times(5)).priceTotalOfArticle(any());
        verify(data, never()).calculateTotalDeliveryFee(any());
    }

    @Test
    public void testPriceTotalOfArticle() throws IOException {
        ArticleCarts ac = new ArticleCarts();
        ac.getArticles().add(new Article(1L, "test-1", 1));
        ac.getArticles().add(new Article(2L, "test-2", 10));

        int total = ac.priceTotalOfArticle(new ItemQuantity(2L, 1));

        assertEquals(10, total);
    }

    @Test
    public void testCalculateTotalsWithDeliveryFeeCarts() throws IOException {
        ArticleCarts data = Main.getCartsFromDataInput(2);
        data = spy(data);

        data.calculateTotalsWithDeliveryFeeCarts();

        assertEquals(2000, data.getCarts().get(0).getTotalInCents());
        assertEquals(1800, data.getCarts().get(1).getTotalInCents());
        assertEquals(800, data.getCarts().get(2).getTotalInCents());

        verify(data, times(5)).priceTotalOfArticle(any());
        verify(data, times(3)).calculateTotalDeliveryFee(any());
    }

    @Test
    public void testCalculateTotalDeliveryFee() throws IOException {
        ArticleCarts ac = new ArticleCarts();
        ac.getDeliveryFees().add(new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(0, 1000), 800));
        ac.getDeliveryFees().add(new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(1000, 2000), 400));
        ac.getDeliveryFees().add(new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(2000, null), 0));

        assertEquals(800, ac.calculateTotalDeliveryFee(new Cart(1L, null, 0)));
        assertEquals(800 + 999, ac.calculateTotalDeliveryFee(new Cart(1L, null, 999)));
        assertEquals(400 + 1000, ac.calculateTotalDeliveryFee(new Cart(1L, null, 1000)));
        assertEquals(400 + 1999, ac.calculateTotalDeliveryFee(new Cart(1L, null, 1999)));
        assertEquals(0 + 2000, ac.calculateTotalDeliveryFee(new Cart(1L, null, 2000)));
        assertEquals(0 + 4000, ac.calculateTotalDeliveryFee(new Cart(1L, null, 4000)));
    }

    @Test
    public void testCalculateTotalsWithDeliveryFeeCartsAndDiscount() throws IOException {
        ArticleCarts data = Main.getCartsFromDataInput(3);
        data = spy(data);

        data.calculateTotalsWithDeliveryFeeCartsAndDiscount();

        int i = 0;
        assertEquals(2350, data.getCarts().get(i++).getTotalInCents());
        assertEquals(1775, data.getCarts().get(i++).getTotalInCents());
        assertEquals(1798, data.getCarts().get(i++).getTotalInCents());
        assertEquals(1083, data.getCarts().get(i++).getTotalInCents());
        assertEquals(1196, data.getCarts().get(i++).getTotalInCents());

        verify(data, times(9)).priceTotalOfArticle(any());
        verify(data, times(5)).calculateTotalDeliveryFee(any());
        verify(data, times(9)).discountOfArticle(any(), anyInt());
    }

}
