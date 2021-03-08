package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {
    private String discountCause;

    private BigDecimal discount;

    public Discount(BigDecimal discount, String discountCause) {
        this.discount = discount;
        this.discountCause = discountCause;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public void setDiscountCause(String discountCause) {
        this.discountCause = discountCause;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
