package com.ecommerce.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author rafael
 */
public class DeliveryFee {

    @JsonProperty(value = "eligible_transaction_volume")
    private EligibleTransactionVolume eligibleTransactionVolume;

    @JsonProperty(value = "price")
    private int priceInCents;

    public static class EligibleTransactionVolume {

        @JsonProperty(value = "min_price")
        private Integer minPrice;

        @JsonProperty(value = "max_price")
        private Integer maxPrice;

        /**
         * Constructor for Jackson
         */
        public EligibleTransactionVolume() {
        }

        public EligibleTransactionVolume(Integer minPrice, Integer maxPrice) {
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
        }

        public Integer getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(Integer minPrice) {
            this.minPrice = minPrice;
        }

        public Integer getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(Integer maxPrice) {
            this.maxPrice = maxPrice;
        }

    }

    /**
     * Constructor for Jackson
     */
    public DeliveryFee() {
    }

    public DeliveryFee(EligibleTransactionVolume eligibleTransactionVolume, int priceInCents) {
        this.eligibleTransactionVolume = eligibleTransactionVolume;
        this.priceInCents = priceInCents;
    }

    public Integer calculate(int totalInCents) {
        DeliveryFee.EligibleTransactionVolume etv = this.getEligibleTransactionVolume();
        if (Integer.compare(totalInCents, etv.getMinPrice()) > -1
                && (etv.getMaxPrice() == null || Integer.compare(totalInCents, etv.getMaxPrice()) < 0)) {
            return totalInCents + this.getPriceInCents();
        }
        return null;
    }

    public EligibleTransactionVolume getEligibleTransactionVolume() {
        return eligibleTransactionVolume;
    }

    public void setEligibleTransactionVolume(EligibleTransactionVolume eligibleTransactionVolume) {
        this.eligibleTransactionVolume = eligibleTransactionVolume;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }

}
