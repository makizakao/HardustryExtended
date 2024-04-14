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
            steelIngot, tinIngot, zincIngot,
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
        brassDust = createMaterialItem("brass-dust", 1f, DEFAULT_COLOR);
        bronzeDust = createMaterialItem("bronze-dust", 1f, BRONZE_COLOR);
        coalDust = new Item("coal-dust", DEFAULT_COLOR);
        copperDust = createMaterialItem("copper-dust", 0.5f, DEFAULT_COLOR);
        glassDust = createMaterialItem("glass-dust", 1f, DEFAULT_COLOR);
        leadDust = createMaterialItem("lead-dust", 0.5f, DEFAULT_COLOR);
        tealliteDust = createMaterialItem("teallite-dust", 0.7f, DEFAULT_COLOR);
        tinDust = createMaterialItem("tin-dust", 0.7f, DEFAULT_COLOR);
        woodPulp = new Item("wood-pulp", DEFAULT_COLOR);
        zincDust = createMaterialItem("zinc-dust", 0.7f, DEFAULT_COLOR);
        // integrated logic circuit
        coatedCircuitBoard = new Item("coated-circuit-board", DEFAULT_COLOR);
        glassTube = new Item("glass-tube", DEFAULT_COLOR);
        integratedLogicCircuit = createMaterialItem("integrated-logic-circuit", 5.0f, DEFAULT_COLOR);
        resistor = new Item("resistor", DEFAULT_COLOR);
        vacuumTube = new Item("vacuum-tube", DEFAULT_COLOR);
        woodPlank = new Item("wood-plank", DEFAULT_COLOR);
        // iron part
        ironBolt = new Item("iron-bolt", DEFAULT_COLOR);
        ironRod = new Item("iron-rod", DEFAULT_COLOR);
        ironSawBlade = createMaterialItem("iron-saw-blade", 20f, DEFAULT_COLOR);
        ironScrew = new Item("iron-screw", DEFAULT_COLOR);
        magneticIronRod = new Item("magnetic-iron-rod", DEFAULT_COLOR);
        // metal
        aluminumIngot = createMaterialItem("aluminum-ingot", 1f, DEFAULT_COLOR);
        brassIngot = createMaterialItem("brass-ingot", 0.7f, DEFAULT_COLOR);
        bronzeIngot = createMaterialItem("bronze-ingot", 0.7f, BRONZE_COLOR);
        copperIngot = createMaterialItem("copper-ingot", 0.5f, DEFAULT_COLOR);
        goldIngot = createMaterialItem("gold-ingot", 0.5f, DEFAULT_COLOR);
        IronIngot = createMaterialItem("iron-ingot", 0.8f, DEFAULT_COLOR);
        leadIngot = createMaterialItem("lead-ingot", 0.5f, DEFAULT_COLOR);
        silverIngot = createMaterialItem("silver-ingot", 1f, DEFAULT_COLOR);
        steelIngot = createMaterialItem("steel-ingot", 1.2f, DEFAULT_COLOR);
        tinIngot = createMaterialItem("tin-ingot", 0.6f, DEFAULT_COLOR);
        zincIngot = createMaterialItem("zinc-ingot", 0.6f, DEFAULT_COLOR);
        // ore
        aurostibite = createOreItem("aurostibite", 2, DEFAULT_COLOR);
        bauxite = createOreItem("bauxite", 4, DEFAULT_COLOR);
        clay = createOreItem("clay", 0, DEFAULT_COLOR);
        diamond = createOreItem("diamond", 4, DEFAULT_COLOR);
        galena = createOreItem("galena", 4, DEFAULT_COLOR);
        hematite = createOreItem("hematite", 3, DEFAULT_COLOR);
        magnetite = createOreItem("magnetite", 3, DEFAULT_COLOR);
        sphalerite = createOreItem("sphalerite", 3, DEFAULT_COLOR);
        teallite = createOreItem("teallite", 2, DEFAULT_COLOR);
        tetrahedrite = createOreItem("tetrahedrite", 2, DEFAULT_COLOR);
        // other

    }

    // costのみ指定のItemを生成する
    private static Item createMaterialItem(String pName, float pCost, Color pColor) {
        return new Item(pName, pColor) {{ cost = pCost; }};
    }

    // hardnessのみ指定のItemを生成する
    private static Item createOreItem(String pName, int pHardness, Color pColor) {
        return new Item(pName, pColor) {{ hardness = pHardness; }};
    }
}
