package jp.makizakao.content;

import jp.makizakao.type.SmeltStack;
import jp.makizakao.world.blocks.HardCoreBlock;
import mindustry.content.UnitTypes;
import mindustry.world.Block;

public class HardBlocks {
    public static Block
    // storage
    coreBasic, coreBronze;

    public static void load() {
        // storage
        coreBasic = HardCoreBlock.create("core-basic", 500, 1000, 3)
                .isEditorOnlyVisible(true)
                .with(HardItems.copperIngot, 1000)
                .alwaysUnlocked(true)
                .isFirstTier(true)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(1)
                .smeltTime(60 * 6)
                .smeltList(SmeltStack.SMELT_TIER_1)
                .build();
        coreBronze = HardCoreBlock.create("core-bronze", 700, 2000, 3)
                .with(HardItems.bronzeIngot, 1000)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(3)
                .smeltTime(60 * 2)
                .smeltList(SmeltStack.SMELT_TIER_1)
                .build();
    }
}
