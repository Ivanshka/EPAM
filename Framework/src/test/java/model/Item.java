package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.google.common.base.Preconditions.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Item {
    private String name;
    private String size;
    private float centPrice;
    private int amount;

    private Item(String name, String size, float cost, int amount){
        checkNotNull(name);
        checkNotNull(size);
        checkArgument(centPrice >= 0);
        checkArgument(amount > 0);

        this.name = name;
        this.size = size;
        this.centPrice = cost;
        this.amount = amount;
    }

    public void changeAmount(int amount) {
        this.amount = amount;
        this.centPrice = centPrice * amount;
    }

    public static Item of(String name, String size, float cost, int amount){
        return new Item(name, size, cost, amount);
    }

    public String getName() { return name; }
    public String getSize() { return size; }
    public int getAmount() {
        return amount;
    }
}