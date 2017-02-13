package com.ecommerce.model;

import com.ecommerce.model.DeliveryFee.EligibleTransactionVolume;
import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafael
 */
public class DeliveryFeeTest {

    public DeliveryFeeTest() {
    }

    @Test
    public void testConvertFromJson() throws IOException {
        String json = "{\"eligible_transaction_volume\": {\n"
                + "        \"min_price\": 0,\n"
                + "        \"max_price\": 1000\n"
                + "      },\n"
                + "      \"price\": 800\n"
                + "}";

        DeliveryFee reloaded = new ObjectMapper().readValue(json, DeliveryFee.class);

        assertEquals(0, reloaded.getEligibleTransactionVolume().getMinPrice().intValue());
        assertEquals(1000, reloaded.getEligibleTransactionVolume().getMaxPrice().intValue());
        assertEquals(800, reloaded.getPriceInCents());
    }

    @Test
    public void testConvertToJson() throws IOException {
        DeliveryFee del = new DeliveryFee();
        EligibleTransactionVolume el = new EligibleTransactionVolume();
        el.setMaxPrice(10);
        el.setMinPrice(50);
        del.setEligibleTransactionVolume(el);
        del.setPriceInCents(100);

        String converted = new ObjectMapper().writeValueAsString(del);

        assertTrue(converted.contains("\"price\":100"));
        assertTrue(converted.contains("\"eligible_transaction_volume\":{\"min_price\":50,\"max_price\":10"));
    }

    @Test
    public void testCalculate() {
        assertEquals(800 + 10, new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(0, 1000), 800).calculate(10).intValue());
        assertNull(new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(0, 1000), 800).calculate(1000));

        assertEquals(1999 + 200, new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(1000, 2000), 200).calculate(1999).intValue());
        assertNull(new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(1000, 2000), 0).calculate(999));

        assertEquals(2000, new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(2000, null), 0).calculate(2000).intValue());
        assertNull(new DeliveryFee(new DeliveryFee.EligibleTransactionVolume(2000, null), 0).calculate(1999));
    }

}
