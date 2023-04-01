package io.micronaut.aws.dynamodb.ecommerce.items;

import io.micronaut.aws.dynamodb.BaseItem;
import io.micronaut.aws.dynamodb.CompositeKey;
import io.micronaut.aws.dynamodb.GlobalSecondaryIndex1;
import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class OrderItemRow extends BaseItem implements GlobalSecondaryIndex1 {

    private final String gsi1Pk;
    private final String gsi1Sk;
    @NonNull
    @NotBlank
    private final String type;

    @NonNull
    @NotBlank
    private final String orderId;

    @NonNull
    @NotBlank
    private final String itemId;

    @NonNull
    @NotBlank
    private final String description;

    @NonNull
    @NotNull
    private final BigDecimal price;

    @NonNull
    @NotNull
    private final Integer amount;

    @NonNull
    @NotNull
    private final BigDecimal totalCost;

    @Creator
    public OrderItemRow(String pk,
                        String sk,
                        String gsi1Pk,
                        String gsi1Sk,
                        String type,
                        String orderId,
                        String itemId,
                        String description,
                        BigDecimal price,
                        Integer amount,
                        BigDecimal totalCost) {
        super(pk, sk);

        this.gsi1Pk = gsi1Pk;
        this.gsi1Sk = gsi1Sk;
        this.type = type;
        this.orderId = orderId;
        this.itemId = itemId;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.totalCost = totalCost;
    }

    public OrderItemRow(CompositeKey key,
                        GlobalSecondaryIndex1 gsi1,
                        String orderId,
                        String itemId,
                        String description,
                        BigDecimal price,
                        Integer amount,
                        BigDecimal totalCost) {
        this(key.getPk(),
            key.getSk(),
            gsi1.getGsi1Pk(),
            gsi1.getGsi1Sk(),
            OrderItemRow.class.getName(),
            orderId,
            itemId,
            description,
            price,
            amount,
            totalCost);
    }

    @Override
    @Nullable
    public String getGsi1Pk() {
        return gsi1Pk;
    }

    @Override
    @Nullable
    public String getGsi1Sk() {
        return gsi1Sk;
    }

    public String getType() {
        return type;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    @NonNull
    public static CompositeKey keyOf(@NonNull String orderId, String itemId) {
        final String value = "ORDER#" + orderId + "#ITEM#" + itemId;
        return new CompositeKey() {

            @Override
            public String getPk() {
                return value;
            }

            @Override
            public String getSk() {
                return value;
            }
        };
    }

    @NonNull
    public static GlobalSecondaryIndex1 gsi1Of(@NonNull String orderId, String itemId) {
        return new GlobalSecondaryIndex1() {
            @Override
            public String getGsi1Pk() {
                return "ORDER#" + orderId;
            }

            @Override
            public String getGsi1Sk() {
                return "ITEM#" + itemId;
            }
        };
    }
}
