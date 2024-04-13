package jp.makizakao.content;

import arc.graphics.Color;
import mindustry.type.Item;

public class HardItems {
    private static Color bronzeColor = Color.valueOf("ff52000");
    private static Color defaultColor = Color.valueOf("ef6832");
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
        chargedPortableBattery = createMaterialItem("charged-portable-battery", 0.5f, defaultColor);
        portableBattery = createMaterialItem("portable-battery", 0.5f, defaultColor);
        // bronze part
        bronzeDrillHead = createMaterialItem("bronze-drill-head", 105f, bronzeColor);
        bronzeHull = createMaterialItem("bronze-hull", 72f, bronzeColor);
        bronzePiston = createMaterialItem("bronze-piston", 23f, bronzeColor);
        mediumBronzePipe = createMaterialItem("medium-bronze-pipe", 27f, bronzeColor);
        smallBronzePipe = createMaterialItem("small-bronze-pipe", 13.5f, bronzeColor);
        // building material
        cokeOvenBlock = createMaterialItem("coke-oven-block", 1f, defaultColor);
        cokeOvenController = createMaterialItem("coke-oven-controller", 80f, defaultColor);
        primitiveBlastFurnaceBlock = createMaterialItem("primitive-blast-furnace-block", 2f, defaultColor);
        primitiveBlastFurnaceController = createMaterialItem("primitive-blast-furnace-controller",
                38f, defaultColor);
        // dust
        brassDust = new Item("brass-dust", defaultColor);
        bronzeDust = new Item("bronze-dust", bronzeColor);
        coalDust = new Item("coal-dust", defaultColor);
        copperDust = new Item("copper-dust", defaultColor);
        glassDust = new Item("glass-dust", defaultColor);
        leadDust = new Item("lead-dust", defaultColor);
        tealliteDust = new Item("teallite-dust", defaultColor);
        tinDust = new Item("tin-dust", defaultColor);
        woodPulp = new Item("wood-pulp", defaultColor);
        zincDust = new Item("zinc-dust", defaultColor);
    }

    // Create material item with default cost
    private static Item createMaterialItem(String pName, float pCost, Color pColor) {
        return new Item(pName, pColor) {{
            cost = pCost;
        }};
    }
}
