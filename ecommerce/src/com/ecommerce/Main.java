package com.ecommerce;

import com.ecommerce.model.ArticleCarts;
import com.ecommerce.model.Cart;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

/**
 *
 * @author rafael
 */
public class Main {

    private static ObjectMapper mapper;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            executeLevel1();
            executeLevel2();
            executeLevel3();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "cannot execute", ex);
        }
    }

    protected static String executeLevel1() throws IOException {
        ArticleCarts data = getCartsFromDataInput(1);
        data.calculateTotalsCarts();
        return printLikeEqualsOutputExpected(data, 1);
    }

    protected static String executeLevel2() throws IOException {
        ArticleCarts data = getCartsFromDataInput(2);
        data.calculateTotalsWithDeliveryFeeCarts();
        return printLikeEqualsOutputExpected(data, 2);
    }

    protected static String executeLevel3() throws IOException {
        ArticleCarts data = getCartsFromDataInput(3);
        data.calculateTotalsWithDeliveryFeeCartsAndDiscount();
        return printLikeEqualsOutputExpected(data, 3);
    }

    public static ArticleCarts getCartsFromDataInput(int level) throws IOException {
        return mapper().readValue(Main.class.getResourceAsStream("data/level" + level + "/data.json"), ArticleCarts.class);
    }

    private static ObjectMapper mapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
        }
        return mapper;
    }

    private static void prepareForOutput(ArticleCarts data) {
        data.getArticles().clear();
        for (Cart cart : data.getCarts()) {
            cart.getItems().clear();
        }
        data.getDeliveryFees().clear();
        data.getDiscounts().clear();
    }

    private static String printLikeEqualsOutputExpected(ArticleCarts data, int level) throws IOException {
        prepareForOutput(data);
        mapper().configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, false);
        String result = mapper().writeValueAsString(data);
        System.out.println("level" + level + ": " + result);
        return result;
    }

}
