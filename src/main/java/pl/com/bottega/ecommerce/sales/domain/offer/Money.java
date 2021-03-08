package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Money {

    private String currency;

    private BigDecimal amount;

    public Money(BigDecimal price, String currency) {
        this.amount = price;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
