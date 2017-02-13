package com.ecommerce.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author rafael
 */
public class Discount {

    @JsonProperty(value = "article_id")
    private long articleId;

    private Type type;

    @JsonProperty(value = "value")
    private int valueInCents;

    public static enum Type {

        amount, percentage;

    }

    public Discount() {
    }

    public Discount(long articleId, Type type, int valueInCents) {
        this.articleId = articleId;
        this.type = type;
        this.valueInCents = valueInCents;
    }

    public int calculate(int totalInCents, int quantity) {
        switch (this.getType()) {
            case amount:
                return totalInCents - (this.getValueInCents() * quantity);
            case percentage: 
                return (int) (totalInCents - (totalInCents * (this.getValueInCents()) / 100.0f));
        }
        return totalInCents;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getValueInCents() {
        return valueInCents;
    }

    public void setValueInCents(int valueInCents) {
        this.valueInCents = valueInCents;
    }

}
