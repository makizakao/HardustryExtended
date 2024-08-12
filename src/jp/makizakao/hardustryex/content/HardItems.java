package jp.makizakao.hardustryex.content;

import arc.graphics.Color;
import arc.util.Log;
import mindustry.content.Items;
import mindustry.type.Item;

import java.util.Optional;

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
            brassDust, bronzeDust, coalDust, copperDust, galenaDust, glassDust, leadDust, tealliteDust, tinDust, woodPulp, zincDust,
            // integrated logic circuit
            coatedCircuitBoard, glassTube, integratedLogicCircuit, resistor, vacuumTube, woodPlank,
            // iron part
            ironBolt, ironRod, ironSawBlade, ironScrew, magneticIronRod,
            // metal
            aluminumIngot, brassIngot, bronzeIngot, copperIngot, goldIngot, ironIngot, leadIngot, silverIngot,
            steelIngot, tinIngot, zincIngot,
            // ore
            aurostibite, bauxite, clay, diamond, galena, hematite, magnetite, nativeCopper, sphalerite, teallite,
            tetrahedrite,
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
            copperWire, fineCopperWire, tinWire, bundledTinWire;

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
        copperDust = Builder.create("copper-dust", DEFAULT_COLOR).cost(0.5f).hardness(1).build();
        galenaDust = Builder.create("galena-dust", DEFAULT_COLOR).build();
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
        copperIngot = create("copper-ingot", DEFAULT_COLOR)
                .cost(0.5f)
                .useTextureFromItem(Items.copper)
                .build();
        goldIngot = createMaterialItem("gold-ingot", 0.5f, DEFAULT_COLOR);
        ironIngot = createMaterialItem("iron-ingot", 0.8f, DEFAULT_COLOR);
        leadIngot = create("lead-ingot", DEFAULT_COLOR)
                .cost(0.5f)
                .useTextureFromItem(Items.lead)
                .build();
        silverIngot = createMaterialItem("silver-ingot", 1f, DEFAULT_COLOR);
        steelIngot = createMaterialItem("steel-ingot", 1.2f, DEFAULT_COLOR);
        tinIngot = createMaterialItem("tin-ingot", 0.6f, DEFAULT_COLOR);
        zincIngot = createMaterialItem("zinc-ingot", 0.6f, DEFAULT_COLOR);
        // ore
        aurostibite = createOreItem("aurostibite", 2, DEFAULT_COLOR);
        bauxite = createOreItem("bauxite", 4, DEFAULT_COLOR);
        clay = createOreItem("clay", 0, DEFAULT_COLOR);
        diamond = createOreItem("diamond", 4, DEFAULT_COLOR);
        galena = createOreItem("galena", 2, DEFAULT_COLOR);
        hematite = createOreItem("hematite", 3, DEFAULT_COLOR);
        magnetite = createOreItem("magnetite", 3, DEFAULT_COLOR);
        nativeCopper = createOreItem("native-copper", 3, DEFAULT_COLOR);
        sphalerite = createOreItem("sphalerite", 3, DEFAULT_COLOR);
        teallite = createOreItem("teallite", 2, DEFAULT_COLOR);
        tetrahedrite = createOreItem("tetrahedrite", 2, DEFAULT_COLOR);
        // other
        coke = new Item("coke", DEFAULT_COLOR);
        cokeOvenBrick = new Item("coke-oven-brick", DEFAULT_COLOR);
        crops = new Item("crops", DEFAULT_COLOR);
        fireBrick = new Item("fire-brick", DEFAULT_COLOR);
        wood = new Item("wood", DEFAULT_COLOR);
        // plate
        aluminumPlate = createMaterialItem("aluminum-plate", 1.25f, DEFAULT_COLOR);
        brassPlate = createMaterialItem("brass-plate", 0.9f, DEFAULT_COLOR);
        bronzePlate = createMaterialItem("bronze-plate", 0.9f, BRONZE_COLOR);
        compressedAluminumPlate = createMaterialItem("compressed-aluminum-plate", 1.5f, DEFAULT_COLOR);
        compressedBronzePlate = createMaterialItem("compressed-bronze-plate", 1.15f, BRONZE_COLOR);
        compressedSteelPlate = createMaterialItem("compressed-steel-plate", 1.4f, DEFAULT_COLOR);
        goldPlate = createMaterialItem("gold-plate", 0.5f, DEFAULT_COLOR);
        ironPlate = createMaterialItem("iron-plate", 1.0f, DEFAULT_COLOR);
        steelPlate = createMaterialItem("steel-plate", 1.15f, DEFAULT_COLOR);
        tinPlate = createMaterialItem("tin-plate", 0.9f, DEFAULT_COLOR);
        // steel part
        lowElectricMotor = createMaterialItem("low-electric-motor", 3.5f, DEFAULT_COLOR);
        lowElectricPiston = createMaterialItem("low-electric-piston", 9.65f, DEFAULT_COLOR);
        lowElectricMachineFrame = new Item("low-electric-machine-frame", DEFAULT_COLOR);
        lowElectricMachineHull = createMaterialItem("low-electric-machine-hull", 98f, DEFAULT_COLOR);
        smallSteelGear = createMaterialItem("small-steel-gear", 1.15f, DEFAULT_COLOR);
        steelDrillHead = createMaterialItem("steel-drill-head", 135f, DEFAULT_COLOR);
        steelRod = new Item("steel-rod", DEFAULT_COLOR);
        // tin part
        tinBolt = new Item("tin-bolt", DEFAULT_COLOR);
        tinRing = new Item("tin-ring", DEFAULT_COLOR);
        tinRod = new Item("tin-rod", DEFAULT_COLOR);
        tinRotor = createMaterialItem("tin-rotor", 46f, DEFAULT_COLOR);
        tinScrew = new Item("tin-screw", DEFAULT_COLOR);
        // wire
        copperWire = new Item("copper-wire", DEFAULT_COLOR);
        fineCopperWire = new Item("fine-copper-wire", DEFAULT_COLOR);
        tinWire = new Item("tin-wire", DEFAULT_COLOR);
        bundledTinWire = new Item("bundled-tin-wire", DEFAULT_COLOR);
    }

    // costのみ指定のItemを生成する
    private static Item createMaterialItem(String pName, float pCost, Color pColor) {
        return new Item(pName, pColor) {{ cost = pCost; }};
    }

    // hardnessのみ指定のItemを生成する
    private static Item createOreItem(String pName, int pHardness, Color pColor) {
        return new Item(pName, pColor) {{ hardness = pHardness; }};
    }

    private static Builder create(String name, Color color) {
        return Builder.create(name, color);
    }

    private static class Builder {
        private final String name;
        private final Color color;
        private float cost = 1f;
        private int hardness = 0;
        private Item textureItem = null;

        public static Builder create(String name, Color color) {
            return new Builder(name, color);
        }

        private Builder(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        public Builder cost(float pCost) {
            cost = pCost;
            return this;
        }

        public Builder hardness(int pHardness) {
            hardness = pHardness;
            return this;
        }

        public Builder useTextureFromItem(Item item) {
            textureItem = item;
            return this;
        }

        public Item build() {
            return new Item(name, color) {
                @Override
                public void load() {
                    super.load();
                    Optional.ofNullable(textureItem).ifPresent(i -> {
                        uiIcon = i.uiIcon;
                        fullIcon = i.fullIcon;
                    });
                }

                {
                    cost = Builder.this.cost;
                    hardness = Builder.this.hardness;
                }
            };
        }
    }
}
