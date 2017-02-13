package com.ecommerce.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author rafael
 */
public class ArticleCarts {

    private List<Article> articles;

    private List<Cart> carts;

    @JsonProperty(value = "delivery_fees")
    private List<DeliveryFee> deliveryFees;

    private List<Discount> discounts;

    private static final Logger log = Logger.getLogger(ArticleCarts.class.getName());

    public void calculateTotalsCarts() {
        for (Cart cart : getCarts()) {
            calculateTotalPrice(cart);
        }
    }

    private void calculateTotalPrice(Cart cart) {
        int total = 0;
        for (ItemQuantity item : cart.getItems()) {
            try {
                total += this.discountOfArticle(item, this.priceTotalOfArticle(item));
            } catch (IOException ex) {
                log.log(Level.SEVERE, "cannot calculate price total for article item", ex);
            }
        }
        cart.setTotalInCents(total);
    }

    public void calculateTotalsWithDeliveryFeeCarts() throws IOException {
        for (Cart cart : getCarts()) {
            calculateTotalPrice(cart);
            cart.setTotalInCents(calculateTotalDeliveryFee(cart));
        }
    }

    public void calculateTotalsWithDeliveryFeeCartsAndDiscount() throws IOException {
        for (Cart cart : getCarts()) {
            calculateTotalPrice(cart);
            cart.setTotalInCents(calculateTotalDeliveryFee(cart));
        }
    }

    protected int calculateTotalDeliveryFee(Cart cart) throws IOException {
        for (DeliveryFee df : getDeliveryFees()) {
            Integer result = df.calculate(cart.getTotalInCents());
            if (result != null){
                return result;
            }
        }
        throw new IOException("cannot define delivey fee - total: " + cart.getTotalInCents());
    }

    protected int priceTotalOfArticle(ItemQuantity item) throws IOException {
        for (Article article : getArticles()) {
            if (article.getId() == item.getArticleId()) {
                return article.getPriceInCents() * item.getQuantity();
            }
        }
        throw new IOException("article not found - id:" + item.getArticleId());
    }

    protected int discountOfArticle(ItemQuantity item, int totalInCents) throws IOException {
        for (Discount discount : getDiscounts()) {
            if (discount.getArticleId() == item.getArticleId()) {
               return discount.calculate(totalInCents, item.getQuantity());
            }
        }
        return totalInCents;
    }

    public List<Article> getArticles() {
        if (articles == null) {
            articles = new ArrayList<>();
        }
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Cart> getCarts() {
        if (carts == null) {
            carts = new ArrayList<>();
        }
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<DeliveryFee> getDeliveryFees() {
        if (deliveryFees == null) {
            deliveryFees = new ArrayList<>();
        }
        return deliveryFees;
    }

    public void setDeliveryFees(List<DeliveryFee> deliveryFees) {
        this.deliveryFees = deliveryFees;
    }

    public List<Discount> getDiscounts() {
        if (discounts == null) {
            discounts = new ArrayList<>();
        }
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

}
