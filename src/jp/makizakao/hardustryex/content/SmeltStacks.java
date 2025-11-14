package jp.makizakao.hardustryex.content;

import arc.struct.Seq;
import arc.util.Time;
import jp.makizakao.hardustryex.type.SmeltStack;

public class SmeltStacks {
    public static final Seq<SmeltStack> SMELT_TIER_1 = Seq.with(
            SmeltStack.of().material(HardItems.copperDust, 2)
                    .product(HardItems.copperIngot, 1)
                    .smeltTime(Time.toSeconds * 1)
                    .build(),
            SmeltStack.of().material(HardItems.leadDust, 2)
                    .product(HardItems.leadIngot, 1)
                    .smeltTime(Time.toSeconds * 1)
                    .build());
    public static final Seq<SmeltStack> SMELT_TIER_2 = Seq.with(
            SmeltStack.of().material(HardItems.copperDust, 2)
                    .product(HardItems.copperIngot, 1)
                    .smeltTime(Time.toSeconds / 2)
                    .build(),
            SmeltStack.of().material(HardItems.leadDust, 2)
                    .product(HardItems.leadIngot, 1)
                    .smeltTime(Time.toSeconds / 2)
                    .build());
}
