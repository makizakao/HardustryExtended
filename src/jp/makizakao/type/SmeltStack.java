package jp.makizakao.type;

import arc.util.Time;
import jp.makizakao.content.HardItems;
import mindustry.type.ItemStack;

import java.util.Arrays;
import java.util.List;

public class SmeltStack {
    private final ItemStack material;
    private final ItemStack product;
    private final float smeltTime;
    private float timeSmelted = 0;

    public SmeltStack(ItemStack material, ItemStack product, float smeltTime) {
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

    public boolean smelted(float delta) {
        if(timeSmelted >= smeltTime) {
            timeSmelted = 0;
            return true;
        }
        timeSmelted += delta;
        return false;
    }


    public static final List<SmeltStack> SMELT_TIER_1 = Arrays.asList(
            new SmeltStack(new ItemStack(HardItems.copperDust, 2),
                    new ItemStack(HardItems.copperIngot, 1), Time.toSeconds * 3),
            new SmeltStack(new ItemStack(HardItems.leadDust, 1),
                    new ItemStack(HardItems.leadIngot, 2), Time.toSeconds * 3));
    public static final List<SmeltStack> SMELT_TIER_2 = Arrays.asList(
            new SmeltStack(new ItemStack(HardItems.copperDust, 2),
                    new ItemStack(HardItems.copperIngot, 2), Time.toSeconds),
            new SmeltStack(new ItemStack(HardItems.leadDust, 1),
                    new ItemStack(HardItems.leadIngot, 1), Time.toSeconds));
}
