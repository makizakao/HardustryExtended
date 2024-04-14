package jp.makizakao.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class HardItems {
    private static final Color BRONZE_COLOR = Color.valueOf("ff52000");
    private static final Color DEFAULT_COLOR = Color.valueOf("ef6832");
    public static Item
            // battery
            chargedPortableBattery, portableBattery,
            // bronze part
            bronzeDrillHead, bronzeHull, bronzePiston, mediumBronzePipe, smallBronzePipe,
            // building material
            cokeOvenBlock, cokeOvenController, primitiveBlastFurnaceBlock, primitiveBlastFurnaceController,
            // dust
            brassDust, bronzeDust, coalDust, copperDust, glassDust, leadDust, tealliteDust, tinDust, woodPulp, zincDust,
            // integrated logic circuit
            coatedCircuitBoard, glassTube, integratedLogicCircuit, resistor, vacuumTube, woodPlank,
            // iron part
            ironBolt, ironRod, ironSawBlade, ironScrew, magneticIronRod,
            // metal
            aluminumIngot, brassIngot, bronzeIngot, copperIngot, goldIngot, IronIngot, leadIngot, silverIngot,
            steelIngot, tinIngot, titaniumIngot, zincIngot,
            // ore
            aurostibite, bauxite, clay, diamond, galena, hematite, magnetite, sphalerite, teallite, tetrahedrite,
            // other
            coke, cokeOvenBrick, crops, fireBrick, wood,
            // plate
            aluminumPlate, brassPlate, bronzePlate, compressedAluminumPlate, compressedBronzePlate,
            compressedSteelPlate, goldPlate, ironPlate, steelPlate, tinPlate,
            // steel part
            lowElectricMotor, lowElectricPiston, lowElectricMachineFrame, lowElectricMachineHull, smallSteelGear,
            steelDrillHead, steelRod,
            // tin part
            tinBolt, tinRing, tinRod, tinRotor, tinScrew,
            // wire
            copperWire, fineCopperWire, tinWire, bundledWire;

    public static void load() {
        // battery
        chargedPortableBattery = createMaterialItem("charged-portable-battery", 0.5f, DEFAULT_COLOR);
        portableBattery = createMaterialItem("portable-battery", 0.5f, DEFAULT_COLOR);
        // bronze part
        bronzeDrillHead = createMaterialItem("bronze-drill-head", 105f, BRONZE_COLOR);
        bronzeHull = createMaterialItem("bronze-hull", 72f, BRONZE_COLOR);
        bronzePiston = createMaterialItem("bronze-piston", 23f, BRONZE_COLOR);
        mediumBronzePipe = createMaterialItem("medium-bronze-pipe", 27f, BRONZE_COLOR);
        smallBronzePipe = createMaterialItem("small-bronze-pipe", 13.5f, BRONZE_COLOR);
        // building material
        cokeOvenBlock = createMaterialItem("coke-oven-block", 1f, DEFAULT_COLOR);
        cokeOvenController = createMaterialItem("coke-oven-controller", 80f, DEFAULT_COLOR);
        primitiveBlastFurnaceBlock = createMaterialItem("primitive-blast-furnace-block", 2f, DEFAULT_COLOR);
        primitiveBlastFurnaceController = createMaterialItem("primitive-blast-furnace-controller",
                38f, DEFAULT_COLOR);
        // dust
        brassDust = new Item("brass-dust", DEFAULT_COLOR);
        bronzeDust = new Item("bronze-dust", BRONZE_COLOR);
        coalDust = new Item("coal-dust", DEFAULT_COLOR);
        copperDust = new Item("copper-dust", DEFAULT_COLOR);
        glassDust = new Item("glass-dust", DEFAULT_COLOR);
        leadDust = new Item("lead-dust", DEFAULT_COLOR);
        tealliteDust = new Item("teallite-dust", DEFAULT_COLOR);
        tinDust = new Item("tin-dust", DEFAULT_COLOR);
        woodPulp = new Item("wood-pulp", DEFAULT_COLOR);
        zincDust = new Item("zinc-dust", DEFAULT_COLOR);
        // metal
        bronzeIngot = createMaterialItem("bronze-ingot", 1f, BRONZE_COLOR);
        copperIngot = createMaterialItem("copper-ingot", 1f, DEFAULT_COLOR);
    }

    // costのみ指定のItemを生成する
    private static Item createMaterialItem(String pName, float pCost, Color pColor) {
        return new Item(pName, pColor) {{ cost = pCost; }};
    }
}
