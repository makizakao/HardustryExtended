package jp.makizakao.content;

import arc.util.Time;
import jp.makizakao.type.SmeltStack;
import jp.makizakao.world.blocks.power.WindGenerator;
import jp.makizakao.world.blocks.storage.HardCoreBlock;
import mindustry.content.UnitTypes;
import mindustry.world.Block;

public class HardBlocks {
    public static Block
    // effect
    coreBasic, coreBronze,
    // power
    windGenerator;

    public static void load() {
        // effect
        coreBasic = HardCoreBlock.create("core-basic", 500, 1000, 3)
                .editorOnlyVisible(true)
                .with(HardItems.copperIngot, 1000)
                .alwaysUnlocked(true)
                .isFirstTier(true)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(1)
                .smeltList(SmeltStack.SMELT_TIER_1)
                .build();
        coreBronze = HardCoreBlock.create("core-bronze", 700, 2000, 3)
                .with(HardItems.bronzeIngot, 1000)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(3)
                .smeltList(SmeltStack.SMELT_TIER_2)
                .build();
        // power
        windGenerator = WindGenerator.create("wind-turbine", 100, 2)
                .with(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .powerProduction(0.4f)
                .efficiency(0f, 1f)
                .powerDuration(Time.toMinutes, Time.toMinutes * 5)
                .build();
    }
}
