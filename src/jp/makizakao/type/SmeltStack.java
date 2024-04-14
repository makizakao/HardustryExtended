package jp.makizakao.type;

import jp.makizakao.content.HardItems;
import mindustry.type.ItemStack;

import java.util.Arrays;
import java.util.List;

public class SmeltStack {
    private final ItemStack material;
    private final ItemStack product;
    private final int smeltTime;
    private int timeSmelted = 0;

    public SmeltStack(ItemStack material, ItemStack product, int smeltTime) {
        this.material = material;
        this.product = product;
        this.smeltTime = smeltTime;
    }

    public ItemStack material() {
        return material;
    }

    public ItemStack product() {
        return product;
    }

    public boolean smelted() {
        if(timeSmelted >= smeltTime) {
            timeSmelted = 0;
            return true;
        }
        timeSmelted++;
        return false;
    }


    public static final List<SmeltStack> SMELT_TIER_1 = Arrays.asList(
            new SmeltStack(new ItemStack(HardItems.copperDust, 1),
                    new ItemStack(HardItems.copperIngot, 1), 60 * 3),
            new SmeltStack(new ItemStack(HardItems.leadDust, 1),
                    new ItemStack(HardItems.leadIngot, 1), 60 * 3));
    public static final List<SmeltStack> SMELT_TIER_2 = Arrays.asList(
            new SmeltStack(new ItemStack(HardItems.copperDust, 1),
                    new ItemStack(HardItems.copperIngot, 1), 60),
            new SmeltStack(new ItemStack(HardItems.leadDust, 1),
                    new ItemStack(HardItems.leadIngot, 1), 60));
}
