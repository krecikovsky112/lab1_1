/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

    // product
    private Product product;

    private int quantity;

    // money
    private Money totalCost;

    // discount
    private Discount discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
                     String productType,String productCurrency, int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType,productCurrency, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName, Date productSnapshotDate,
                     String productType, String productCurrency, int quantity, BigDecimal discount, String discountCause) {
        this.product = new Product(productId, new Money(productPrice,productCurrency), productName,productSnapshotDate,productType);

        this.quantity = quantity;

        this.discount = new Discount(discount, discountCause);

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

        BigDecimal resultofTotalCost = productPrice.multiply(new BigDecimal(quantity)).subtract(discountValue);

        this.totalCost = new Money(resultofTotalCost, productCurrency);
    }

    public String getProductId() {
        return product.getId();
    }

    public BigDecimal getProductPrice() {
        return product.getPrice();
    }

    public String getProductName() {
        return product.getName();
    }

    public Date getProductSnapshotDate() {
        return product.getProductSnapshotDate();
    }

    public String getProductType() {
        return product.getProductType();
    }

    public BigDecimal getTotalCost() {
        return totalCost.getAmount();
    }

    public String getTotalCostCurrency() {
        return totalCost.getCurrency();
    }

    public BigDecimal getDiscount() { return discount.getDiscount(); }

    public String getDiscountCause() { return discount.getDiscountCause(); }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (discount.getDiscount() == null ? 0 : discount.getDiscount().hashCode());
        result = prime * result + (product.getName() == null ? 0 : product.getName().hashCode());
        result = prime * result + (product.getPrice() == null ? 0 : product.getPrice().hashCode());
        result = prime * result + (product.getId() == null ? 0 : product.getId().hashCode());
        result = prime * result + (product.getProductType() == null ? 0 : product.getProductType().hashCode());
        result = prime * result + quantity;
        result = prime * result + (totalCost.getAmount() == null ? 0 : totalCost.getAmount().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        if (discount.getDiscount() == null) {
            if (other.discount.getDiscount() != null) {
                return false;
            }
        } else if (!discount.getDiscount().equals(other.discount.getDiscount())) {
            return false;
        }
        if (product.getName() == null) {
            if (other.product.getName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.product.getName())) {
            return false;
        }
        if (product.getPrice() == null) {
            if (other.product.getPrice() != null) {
                return false;
            }
        } else if (!product.getPrice().equals(other.product.getPrice())) {
            return false;
        }
        if (product.getId() == null) {
            if (other.product.getId() != null) {
                return false;
            }
        } else if (!product.getId().equals(other.product.getId())) {
            return false;
        }
        if (product.getProductType() != other.product.getProductType()) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        if (totalCost.getAmount() == null) {
            if (other.totalCost.getAmount() != null) {
                return false;
            }
        } else if (!totalCost.getAmount().equals(other.totalCost.getAmount())) {
            return false;
        }
        return true;
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product.getName() == null) {
            if (other.product.getName() != null) {
                return false;
            }
        } else if (!product.getName().equals(other.product.getName())) {
            return false;
        }
        if (product.getPrice() == null) {
            if (other.product.getPrice() != null) {
                return false;
            }
        } else if (!product.getPrice().equals(other.product.getPrice())) {
            return false;
        }
        if (product.getId() == null) {
            if (other.product.getId()!= null) {
                return false;
            }
        } else if (!product.getId().equals(other.product.getId())) {
            return false;
        }
        if (product.getProductType() != other.product.getProductType()) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max;
        BigDecimal min;
        if (totalCost.getAmount().compareTo(other.totalCost.getAmount()) > 0) {
            max = totalCost.getAmount();
            min = other.totalCost.getAmount();
        } else {
            max = other.totalCost.getAmount();
            min = totalCost.getAmount();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
