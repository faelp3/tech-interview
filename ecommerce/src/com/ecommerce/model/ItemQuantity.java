package com.ecommerce.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author rafael
 */
public class ItemQuantity {

    @JsonProperty(value = "article_id")
    private long articleId;

    private int quantity;

    public ItemQuantity() {
    }
    
    public ItemQuantity(long articleId, int quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }
    
    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
