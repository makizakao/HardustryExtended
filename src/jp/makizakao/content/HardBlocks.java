package jp.makizakao.content;

import arc.util.Time;
import jp.makizakao.content.recipe.*;
import jp.makizakao.content.drawer.*;
import jp.makizakao.type.SmeltStack;
import jp.makizakao.world.blocks.distribution.*;
import jp.makizakao.world.blocks.power.*;
import jp.makizakao.world.blocks.production.*;
import jp.makizakao.world.blocks.storage.*;
import jp.makizakao.builder.mindustry.*;
import mindustry.content.Fx;
import mindustry.content.UnitTypes;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.gen.Sounds;
import mindustry.world.Block;

public class HardBlocks {
    public static Block
    // distribution
    copperConveyor, copperInvertedSorter, copperJunction, copperOverflowGate, copperRouter, copperSorter,
    copperUnderflowGate,
    // effect
    coreBasic, coreBronze, copperUnloader,
    // ore
    galenaOre, sphaleriteOre, tealliteOre, tetrahedriteOre, tinyCopperOre, tinyLeadOre,
    // power - battery
    basicBattery,
    // power - generator
    advancedWindTurbine, windTurbine,
    // power - node
    basicNode,
    // production - crafter - bending machine
    basicBendingMachine,
    // production - crafter - blast furnace
    primitiveBlastFurnace,
    // production - crafter - crusher
    copperCrusher, bronzeCrusher,
    // production - crafter - dustMixer
    copperDustMixer,
    // production - crafter - factory
    advancedBronzeFactory, basicBronzeFactory, bronzeFactory,
    // production - crafter - furnace
    copperFurnace,
    // production - crafter - heater
    basicElectricHeater, basicCoalHeater,
    // production - crafter - press
    advancedBronzePress, bronzePress,
    // production - crafter - steam boiler
    bronzeSteamBoiler,
    // production - drill
    quarry,
    // turret
    duo;


    public static void load() {
        // distribution
        copperConveyor = HardConveyor.Builder.create("copper-conveyor", 30, 0.02f, 3f)
                .requirements(HardItems.copperIngot, 1, HardItems.leadIngot, 1)
                .powerConsume(0.0004f)
                .buildCostMultiplier(2f)
                .build();
        copperInvertedSorter = HardSorter.Builder.create("copper-inverted-sorter", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .invert()
                .build();
        copperJunction = HardJunction.Builder.create("copper-junction", 30, 1)
                .requirements(HardItems.copperIngot, 1, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .build();
        copperOverflowGate = HardOverflowGate.Builder.create("copper-overflow-gate", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .build();
        copperRouter = HardRouter.Builder.create("copper-router", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .build();
        copperSorter = HardSorter.Builder.create("copper-sorter", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .build();
        copperUnderflowGate = HardOverflowGate.Builder.create("copper-underflow-gate", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 1)
                .powerConsume(0.01f)
                .buildCostMultiplier(2f)
                .invert()
                .build();
        // effect
        coreBasic = HardCoreBlock.Builder.create("core-basic", 500, 1000, 3)
                .requirements(HardItems.copperIngot, 1000)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(1)
                .smeltList(SmeltStack.SMELT_TIER_1)
                .firstTier()
                .build();
        coreBronze = HardCoreBlock.Builder.create("core-bronze", 700, 2000, 3)
                .requirements(HardItems.bronzeIngot, 1000)
                .unitType(UnitTypes.alpha)
                .unitCapModifier(3)
                .smeltList(SmeltStack.SMELT_TIER_2)
                .build();
        copperUnloader = HardUnloader.Builder.create("copper-unloader", 30, 1, 3f)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 1)
                .powerConsume(0.02f)
                .buildCostMultiplier(2f)
                .build();
        // ore
        galenaOre = OreBlockBuilder.create("galena-ore", HardItems.galena).build();
        sphaleriteOre = OreBlockBuilder.create("sphalerite-ore", HardItems.sphalerite).build();
        tealliteOre = OreBlockBuilder.create("teallite-ore", HardItems.teallite).build();
        tetrahedriteOre = OreBlockBuilder.create("tetrahedrite-ore", HardItems.tetrahedrite).build();
        tinyCopperOre = OreBlockBuilder.create("tiny-copper-ore", HardItems.copperDust).build();
        tinyLeadOre = OreBlockBuilder.create("tiny-lead-ore", HardItems.leadDust).build();
        // power - battery
        basicBattery = HardBattery.Builder.create("basic-battery", 80, 1)
                .requirements(HardItems.copperIngot, 10, HardItems.leadIngot, 20)
                .capacity(1000f)
                .build();
        // power - generator
        advancedWindTurbine = WindGenerator.Builder.create("advanced-wind-turbine", 100, 2)
                .requirements(HardItems.bronzeIngot, 30, HardItems.copperIngot, 20, HardItems.leadIngot, 60)
                .powerProduction(1.2f)
                .efficiency(0.3f, 1f)
                .powerDuration(Time.toMinutes, Time.toMinutes * 5)
                .rotateSpeed(3f)
                .build();
        windTurbine = WindGenerator.Builder.create("wind-turbine", 100, 2)
                .requirements(HardItems.copperIngot, 20, HardItems.leadIngot, 20)
                .powerProduction(0.4f)
                .efficiency(0f, 1f)
                .powerDuration(Time.toMinutes, Time.toMinutes * 5)
                .rotateSpeed(2f)
                .build();
        // power - node
        basicNode = HardPowerNode.Builder.create("basic-node", 30, 1)
                .requirements(HardItems.copperIngot, 2, HardItems.leadIngot, 3)
                .laserRange(4f)
                .powerConsume(0.02f)
                .maxNodes(4)
                .build();
        // production - crafter - bending machine
        basicBendingMachine = RotateAnimatedCrafter.Builder.create("basic-bending-machine", 200, 2)
                .requirements(HardItems.lowElectricPiston, 10, HardItems.integratedLogicCircuit, 10,
                        HardItems.lowElectricMachineHull, 1, HardItems.lowElectricMotor, 10, HardItems.tinWire, 10)
                .rotate(0, 60f, -1.0f)
                .resolveRecipes(BendingMachineRecipes.BENDING_MACHINE_TIER_1)
                .itemCapacity(20)
                .build();
        // production - crafter - blast furnace
        primitiveBlastFurnace = ExplodableCrafter.Builder.create("primitive-blast-furnace", 500, 3)
                .requirements(HardItems.primitiveBlastFurnaceBlock, 30,
                        HardItems.primitiveBlastFurnaceController, 1)
                .explodeTemperature(1500f, 100, 5)
                .effect(Fx.explosion)
                .resolveRecipes(BlastFurnaceRecipes.BLAST_FURNACE_TIER_1)
                .itemCapacity(8)
                .ambientSound(Sounds.smelter, 0.6f)
                .drawer(HardDrawMultis.SMELT_FLAME)
                .build();
        // production - crafter - crusher
        copperCrusher = RotateAnimatedCrafter.Builder.create("copper-crusher", 100, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .rotate(0f, 360f, 1.0f)
                .resolveRecipes(CrusherRecipes.CRUSHER_TIER_1)
                .itemCapacity(10)
                .build();
        bronzeCrusher = RotateAnimatedCrafter.Builder.create("bronze-crusher", 150, 2)
                .requirements(HardItems.bronzeHull, 1, HardItems.bronzePiston, 2, HardItems.copperWire, 10,
                        HardItems.leadIngot, 30)
                .rotate(0f, 360f, 1.0f)
                .resolveRecipes(CrusherRecipes.CRUSHER_TIER_2)
                .itemCapacity(10)
                .build();
        // production - crafter - dustMixer
        copperDustMixer = RotateAnimatedCrafter.Builder.create("copper-dust-mixer", 100, 2)
                .requirements(HardItems.copperIngot, 20, HardItems.leadIngot, 10)
                .rotate(0f, 360f, 1.0f)
                .resolveRecipes(DustMixerRecipes.DUST_MIXER_TIER_1)
                .itemCapacity(10)
                .build();
        // production - crafter - factory
        basicBronzeFactory = HardMultiCrafter.Builder.create("basic-bronze-factory", 100, 2)
                .requirements(HardItems.bronzeIngot, 60, HardItems.copperIngot, 30, HardItems.leadIngot, 30)
                .resolveRecipes(HardFactoryRecipe.BRONZE_FACTORY_TIER_1)
                .itemCapacity(40)
                .drawer(HardDrawBlocks.ITEM_DISPLAY_DRAWER)
                .build();
        bronzeFactory = HardMultiCrafter.Builder.create("bronze-factory", 100, 2)
                .requirements(HardItems.bronzeHull, 1, HardItems.copperWire, 20, HardItems.leadIngot, 30,
                        HardItems.bronzeIngot, 20)
                .resolveRecipes(HardFactoryRecipe.BRONZE_FACTORY_TIER_2)
                .itemCapacity(100)
                .drawer(HardDrawBlocks.ITEM_DISPLAY_DRAWER)
                .build();
        // production - crafter - furnace
        copperFurnace = ExplodableCrafter.Builder.create("copper-furnace", 100, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .explodeTemperature(300f, 10, 5)
                .effect(Fx.explosion)
                .resolveRecipes(FurnaceRecipes.FURNACE_TIER_1)
                .itemCapacity(12)
                .ambientSound(Sounds.smelter, 0.4f)
                .drawer(HardDrawMultis.SMELT_FLAME)
                .build();
        // production - crafter - heater
        basicElectricHeater = HardMultiCrafter.Builder.create("basic-electric-heater", 30, 1)
                .requirements(HardItems.copperIngot, 20, HardItems.leadIngot, 30)
                .resolveRecipes(HeaterRecipes.ELECTRIC_HEATER_TIER_1)
                .drawer(HardDrawMultis.HEAT_OUTPUT)
                .build();
        basicCoalHeater = HardMultiCrafter.Builder.create("basic-coal-heater", 30, 1)
                .requirements(HardItems.copperIngot, 60, HardItems.leadIngot, 20)
                .resolveRecipes(HeaterRecipes.COAL_HEATER_TIER_1)
                .drawer(HardDrawMultis.HEAT_OUTPUT)
                .build();
        // production - crafter - press
        advancedBronzePress = RotateAnimatedCrafter.Builder.create("advanced-bronze-press", 100, 2)
                .requirements(HardItems.bronzeHull, 1, HardItems.bronzePiston, 2, HardItems.copperIngot, 30,
                        HardItems.leadIngot, 30)
                .rotate(0, 60f, -1.0f)
                .resolveRecipes(PressRecipes.BRONZE_PRESS_TIER_2)
                .itemCapacity(10)
                .build();
        bronzePress = RotateAnimatedCrafter.Builder.create("bronze-press", 100, 2)
                .requirements(HardItems.bronzeIngot, 60, HardItems.copperIngot, 20, HardItems.leadIngot, 30)
                .rotate(0, 60f, -1.0f)
                .resolveRecipes(PressRecipes.BRONZE_PRESS_TIER_1)
                .itemCapacity(10)
                .build();
        // production - crafter - steam boiler
        bronzeSteamBoiler = SteamBoiler.Builder.create("bronze-steam-boiler", 200, 2)
                .requirements(HardItems.bronzePlate, 30, HardItems.copperIngot, 15, HardItems.leadIngot, 10)
                .explodeTemperature(500, 100, 5)
                .effect(Fx.reactorExplosion)
                .resolveRecipes(SteamBoilerRecipes.STEAM_BOILER_TIER_1)
                .ambientSound(Sounds.smelter, 0.6f)
                .liquidCapacity(200f)
                .drawer(HardDrawMultis.BOILER_FLAME)
                .build();
        // production - drill
        quarry = TierDrill.Builder.create("quarry", 100, 2, 2)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .drillTime(3000f)
                .itemCapacity(10)
                .powerConsume(0.4f)
                .build();
        // turret
        duo = ItemTurretBuilder.create("duo", 200, 1)
                .requirements(HardItems.copperIngot, 50, HardItems.leadIngot, 30)
                .ammo(HardAmmoTypes.DUO_TIER_1)
                .shoot(new ShootAlternate(3.5f), 3f)
                .ammoUseEffect(Fx.casing1)
                .recoil(2, 0.5f)
                .reload(40f)
                .range(100f)
                .shootCone(15f)
                .inaccuracy(4f)
                .rotateSpeed(7.5f)
                .consumeCoolant(0)
                .powerConsume(0.4f)
                .drawer(HardDrawTurrets.DUO)
                .build();
    }
}
