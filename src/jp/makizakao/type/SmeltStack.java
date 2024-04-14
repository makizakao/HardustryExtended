package jp.makizakao.type;

import jp.makizakao.content.HardItems;
import mindustry.type.ItemStack;

import java.util.List;

public record SmeltStack(ItemStack material, ItemStack product) {
    public static final List<SmeltStack> SMELT_TIER_1 = List.of(
            new SmeltStack(new ItemStack(HardItems.copperDust, 1),
                    new ItemStack(HardItems.copperIngot, 1)),
            new SmeltStack(new ItemStack(HardItems.leadDust, 1),
                    new ItemStack(HardItems.leadIngot, 1)));
}
