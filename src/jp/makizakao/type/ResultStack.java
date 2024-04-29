package jp.makizakao.type;

import mindustry.type.Item;
import mindustry.type.ItemStack;

public class ResultStack extends ItemStack {
    public static final ResultStack[] empty = {};
    public final float dropChance;

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
}
