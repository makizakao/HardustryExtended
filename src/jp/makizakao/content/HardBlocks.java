package jp.makizakao.content;

import arc.util.Time;
import jp.makizakao.content.DefaultBlockBuilders.*;
import jp.makizakao.type.SmeltStack;
import jp.makizakao.world.blocks.distribution.HardConveyor;
import jp.makizakao.world.blocks.distribution.HardJunction;
import jp.makizakao.world.blocks.power.HardBattery;
import jp.makizakao.world.blocks.power.HardPowerNode;
import jp.makizakao.world.blocks.power.WindGenerator;
import jp.makizakao.world.blocks.production.HardMultiCrafter;
import jp.makizakao.world.blocks.production.RotateAnimatedCrafter;
import jp.makizakao.world.blocks.production.TierDrill;
import jp.makizakao.world.blocks.storage.HardCoreBlock;
import mindustry.content.UnitTypes;
import mindustry.gen.Sounds;
import mindustry.world.Block;

import static jp.makizakao.content.HardDrawMultis.HEAT_OUTPUT;
import static jp.makizakao.content.HardDrawMultis.SMELT_FLAME;
import static jp.makizakao.content.HardRecipes.*;

public class HardBlocks {
    public static Block
    // distribution
    copperConveyor, copperJunction,
    // effect
    coreBasic, coreBronze,
    // power - battery
    basicBattery,
    // power - generator
    advancedWindTurbine, windTurbine,
    // power - node
    basicNode,
    // production - crafter - crusher
    copperCrusher,
    // production - crafter - dustMixer
    copperDustMixer,
    // production - crafter - furnace
    copperFurnace,
    // production - crafter - heater
    basicElectricHeater,
    // production - drill
    quarry,
    // ore
    galenaOre, sphaleriteOre, tealliteOre, tetrahedriteOre, tinyCopperOre, tinyLeadOre;

    public static void load() {
        // distribution
        copperConveyor = HardConveyor.create("copper-conveyor", 30, 0.02f, 3f)
                .requirements(HardItems.copperIngot, 1, HardItems.leadIngot, 1)
                .powerConsume(0.001f)
                .buildCostMultiplier(2f)
                .build();
        copperJunction = HardJunction.create("copper-junction", 30, 1)
                .requirements(HardItems.copperIngot, 1, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .build();
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
        // power - battery
        basicBattery = HardBattery.create("basic-battery", 80, 1)
                .requirements(HardItems.copperIngot, 10, HardItems.leadIngot, 20)
                .capacity(1000f)
                .build();
        // power - generator
        advancedWindTurbine = WindGenerator.create("advanced-wind-turbine", 100, 2)
                .requirements(HardItems.bronzeIngot, 30, HardItems.copperIngot, 20, HardItems.leadIngot, 60)
                .powerProduction(2.4f)
                .efficiency(0.3f, 1f)
                .powerDuration(Time.toMinutes, Time.toMinutes * 5)
                .rotateSpeed(3f)
                .build();
        windTurbine = WindGenerator.create("wind-turbine", 100, 2)
                .requirements(HardItems.copperIngot, 20, HardItems.leadIngot, 20)
                .powerProduction(0.8f)
                .efficiency(0f, 1f)
                .powerDuration(Time.toMinutes, Time.toMinutes * 5)
                .rotateSpeed(2f)
                .build();
        // power - node
        basicNode = HardPowerNode.create("basic-node", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 3)
                .laserRange(4f)
                .maxNodes(4)
                .consumePower(0.04f)
                .build();
        // production - crafter - crusher
        copperCrusher = RotateAnimatedCrafter.create("copper-crusher", 100, 2)
                .rotateSpeed(1f)
                .rotateAngle(0f, 360f)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .resolveRecipes(CRUSHER_TIER_1)
                .itemCapacity(10)
                .build();
        // production - crafter - dustMixer
        copperDustMixer = RotateAnimatedCrafter.create("copper-dust-mixer", 100, 2)
                .rotateSpeed(1f)
                .rotateAngle(0f, 360f)
                .requirements(HardItems.copperIngot, 20, HardItems.leadIngot, 10)
                .resolveRecipes(DUST_MIXER_TIER_1)
                .itemCapacity(10)
                .build();
        // production - crafter - furnace
        copperFurnace = HardMultiCrafter.create("copper-furnace", 100, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .resolveRecipes(FURNACE_TIER_1)
                .itemCapacity(12)
                .ambientSound(Sounds.smelter, 0.4f)
                .drawer(SMELT_FLAME)
                .build();
        // production - crafter - heater
        basicElectricHeater = HardMultiCrafter.create("basic-electric-heater", 30, 1)
                .requirements(HardItems.copperIngot, 20, HardItems.leadIngot, 30)
                .resolveRecipes(ELECTRIC_HEATER_TIER_1)
                .drawer(HEAT_OUTPUT)
                .build();
        // production - drill
        quarry = TierDrill.create("quarry", 100, 2, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .itemCapacity(10)
                .drillTime(3000)
                .consumePower(0.4f)
                .build();
        // ore
        galenaOre = OreBlockBuilder.create("galena-ore", HardItems.galena).build();
        sphaleriteOre = OreBlockBuilder.create("sphalerite-ore", HardItems.sphalerite).build();
        tealliteOre = OreBlockBuilder.create("teallite-ore", HardItems.teallite).build();
        tetrahedriteOre = OreBlockBuilder.create("tetrahedrite-ore", HardItems.tetrahedrite).build();
        tinyCopperOre = OreBlockBuilder.create("tiny-copper-ore", HardItems.copperDust).build();
        tinyLeadOre = OreBlockBuilder.create("tiny-lead-ore", HardItems.leadDust).build();
    }
}
