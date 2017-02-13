package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author rafael
 */
public class Cart {

    private Long id;

    private List<ItemQuantity> items;

    @JsonProperty(value = "total")
    private int totalInCents;


    /**
     * Constructor for Jackson
     */
    public Cart() {
    }

    public Cart(Long id, List<ItemQuantity> items, int totalInCents) {
        this.id = id;
        this.items = items;
        this.totalInCents = totalInCents;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemQuantity> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<ItemQuantity> items) {
        this.items = items;
    }

    public int getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(int totalInCents) {
        this.totalInCents = totalInCents;
    }

}
