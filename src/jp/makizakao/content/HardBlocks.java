package jp.makizakao.content;

import arc.util.Time;
import jp.makizakao.type.SmeltStack;
import jp.makizakao.world.blocks.power.WindGenerator;
import jp.makizakao.world.blocks.production.HardMultiCrafter;
import jp.makizakao.world.blocks.storage.HardCoreBlock;
import mindustry.content.UnitTypes;
import mindustry.gen.Sounds;
import mindustry.world.Block;

import static jp.makizakao.content.HardDrawMultis.SMELT_FLAME;
import static jp.makizakao.content.HardRecipes.FURNACE_TIER_1;

public class HardBlocks {
    public static Block
    // effect
    coreBasic, coreBronze,
    // power
    windGenerator,
    // crafter - furnace
    copperFurnace;

    public static void load() {
        // effect
        coreBasic = HardCoreBlock.create("core-basic", 500, 1000, 3)
                .editorOnlyVisible(true)
                .requirements(HardItems.copperIngot, 1000)
                .alwaysUnlocked(true)
                .isFirstTier(true)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(1)
                .smeltList(SmeltStack.SMELT_TIER_1)
                .build();
        coreBronze = HardCoreBlock.create("core-bronze", 700, 2000, 3)
                .requirements(HardItems.bronzeIngot, 1000)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(3)
                .smeltList(SmeltStack.SMELT_TIER_2)
                .build();
        // power
        windGenerator = WindGenerator.create("wind-turbine", 100, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .powerProduction(0.4f)
                .efficiency(0f, 1f)
                .powerDuration(Time.toMinutes, Time.toMinutes * 5)
                .build();
        // crafter - furnace
        copperFurnace = HardMultiCrafter.create("copper-furnace", 100, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .resolveRecipes(FURNACE_TIER_1)
                .itemCapacity(10)
                .ambientSound(Sounds.smelter, 0.7f)
                .drawer(SMELT_FLAME)
                .build();
    }


}
