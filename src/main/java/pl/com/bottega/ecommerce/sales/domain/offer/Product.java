package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private String id;

    private Money price;

    private String name;

    private Date SnapshotDate;

    private String type;

    public Product(String productId, Money price, String productName, Date productSnapshotDate, String productType) {
        this.id = productId;
        this.price = price;
        this.name = productName;
        this.SnapshotDate = productSnapshotDate;
        this.type = productType;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price.getAmount();
    }

    public String getName() {
        return name;
    }

    public Date getProductSnapshotDate() {
        return SnapshotDate;
    }

    public String getProductType() {
        return type;
    }
}
