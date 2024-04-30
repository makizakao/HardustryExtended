package jp.makizakao.type;

import mindustry.type.Item;
import mindustry.type.ItemStack;

public class ResultStack extends ItemStack {
    public static final ResultStack[] empty = {};
    public float dropChance;

    public ResultStack(Item item, int amount, float dropChance) {
        super(item, amount);
        this.dropChance = dropChance;
    }

    public ResultStack() {
        super();
        this.dropChance = 1.0f;
    }

    @Override
    public ResultStack copy() {
        return new ResultStack(item, amount, dropChance);
    }

    public static ResultStack[] with(Object... items) {
        var stack = new ResultStack[items.length / 3];
        for (int i = 0; i < items.length; i += 3) {
            stack[i / 3] = new ResultStack((Item) items[i], (int) items[i + 1], (float) items[i + 2]);
        }
        return stack;
    }

    public ResultStack set(Item item, int amount, float dropChance) {
        super.set(item, amount);
        this.dropChance = dropChance;
        return this;
    }

    public int compareTo(ResultStack resultStack) {
        return item.compareTo(resultStack.item);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || (o instanceof ResultStack stack && stack.amount == amount && item == stack.item
                && dropChance == stack.dropChance);
    }

    public boolean equals(ResultStack other) {
        return other != null && other.item == item && other.amount == amount && other.dropChance == dropChance;
    }

    @Override
    public String toString() {
        return "ResultStack{" +
                "item=" + item +
                ", amount=" + amount +
                ", dropChance=" + dropChance +
                '}';
    }
}
