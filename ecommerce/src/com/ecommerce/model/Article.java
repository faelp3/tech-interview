package com.ecommerce.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author rafael
 */
public class Article {

    private Long id;
    private String name;

    @JsonProperty(value = "price")
    private int priceInCents;

    /**
     * Constructor for Jackson
     */
    public Article() {
    }

    public Article(Long id, String name, int priceInCents) {
        this.id = id;
        this.name = name;
        this.priceInCents = priceInCents;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

}
