package jp.makizakao.content;

import arc.struct.Seq;
import jp.makizakao.type.ResultRecipe;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;
import multicraft.Recipe;

import java.util.Objects;

public class HardRecipes {
    // bending machine
    public static final Seq<Recipe> BENDING_MACHINE_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.brassIngot, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.brassPlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzeIngot, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.bronzePlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzePlate, 3)
                    .inputPower(1.0f)
                    .outputItems(HardItems.compressedBronzePlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.steelPlate, 3)
                    .inputPower(1.0f)
                    .outputItems(HardItems.compressedSteelPlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.goldIngot, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.goldPlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.ironIngot, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.ironPlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.steelIngot, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.steelPlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.tinIngot, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.tinPlate, 1)
                    .craftTime(180f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.tinRod, 1)
                    .inputPower(0.8f)
                    .outputItems(HardItems.tinRing, 1)
                    .craftTime(180f)
                    .build()
    );
    // blast furnace
    public static final Seq<Recipe> BLAST_FURNACE_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.ironIngot, 2, HardItems.coke, 1)
                    .inputHeat(15f)
                    .outputItems(HardItems.steelIngot, 1)
                    .craftTime(3600f)
                    .build()
    );
    // crusher
    public static final Seq<Recipe> CRUSHER_TIER_1 = Seq.with(
            new ResultBuilder()
                    .inputItems(HardItems.nativeCopper, 1)
                    .inputPower(0.3f)
                    .resultItems(HardItems.copperDust, 2)
                    .dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .inputItems(HardItems.galena, 1)
                    .inputPower(0.2f)
                    .resultItems(HardItems.galenaDust, 2)
                    .dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .inputItems(HardItems.teallite, 1)
                    .inputPower(0.3f)
                    .resultItems(HardItems.tealliteDust, 2)
                    .dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .inputItems(HardItems.tinIngot, 1)
                    .inputPower(0.2f)
                    .resultItems(HardItems.tinDust, 1)
                    .dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .inputItems(HardItems.tetrahedrite, 1)
                    .inputPower(0.3f)
                    .resultItems(HardItems.copperDust, 2, HardItems.zincDust, 1)
                    .dropChances(1.0f, 0.2f)
                    .craftTime(60f)
                    .build()
    );
    // dust mixer
    public static final Seq<Recipe> DUST_MIXER_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.copperDust, 3, HardItems.tinDust, 1)
                    .inputPower(0.05f)
                    .outputItems(HardItems.bronzeDust, 4)
                    .craftTime(150f)
                    .build()
    );
    // factory
    public static final Seq<Recipe> BRONZE_FACTORY_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.portableBattery, 1)
                    .inputPower(0.4f)
                    .outputItems(HardItems.chargedPortableBattery, 1)
                    .craftTime(1200f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzePlate, 40, HardItems.brassPlate, 20)
                    .inputPower(0.4f)
                    .outputItems(HardItems.bronzeHull, 1)
                    .craftTime(1000f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5)
                    .inputPower(0.4f)
                    .outputItems(HardItems.bronzePiston, 1)
                    .craftTime(600f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzePlate, 15)
                    .inputPower(0.4f)
                    .outputItems(HardItems.smallBronzePipe, 1)
                    .craftTime(400f)
                    .build()
    );
    public static final Seq<Recipe> BRONZE_FACTORY_TIER_2 = Seq.with(
            new Builder()
                    .inputItems(HardItems.portableBattery, 1)
                    .inputPower(0.6f)
                    .outputItems(HardItems.chargedPortableBattery, 1)
                    .craftTime(900f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzePlate, 40, HardItems.brassPlate, 20)
                    .inputPower(0.6f)
                    .outputItems(HardItems.bronzeHull, 1)
                    .craftTime(800f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5)
                    .inputPower(0.6f)
                    .outputItems(HardItems.bronzePiston, 1)
                    .craftTime(400f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzePlate, 15)
                    .inputPower(0.6f)
                    .outputItems(HardItems.smallBronzePipe, 1)
                    .craftTime(300f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.tinIngot, 5, HardItems.leadIngot, 10, HardItems.copperWire, 2)
                    .inputPower(0.6f)
                    .outputItems(HardItems.portableBattery, 1)
                    .craftTime(300f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzeIngot, 100)
                    .inputPower(0.6f)
                    .outputItems(HardItems.bronzePiston, 1)
                    .craftTime(1200f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.cokeOvenBrick, 20)
                    .inputPower(0.6f)
                    .outputItems(HardItems.cokeOvenBlock, 1)
                    .craftTime(300f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.ironPlate, 40, HardItems.copperWire, 10)
                    .inputPower(0.6f)
                    .outputItems(HardItems.cokeOvenController, 1)
                    .craftTime(800f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.fireBrick, 20)
                    .inputPower(0.6f)
                    .outputItems(HardItems.primitiveBlastFurnaceBlock, 1)
                    .craftTime(300f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5)
                    .inputPower(0.6f)
                    .outputItems(HardItems.bronzePiston, 1)
                    .craftTime(400f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .inputPower(0.6f)
                    .outputItems(HardItems.bronzePiston, 1)
                    .craftTime(800f)
                    .build()
    );
    // furnace
    public static final Seq<Recipe> FURNACE_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.copperDust, 4)
                    .inputHeat(2f)
                    .outputItems(HardItems.copperIngot, 2)
                    .craftTime(90)
                    .build(),
            new Builder()
                    .inputItems(HardItems.leadDust, 4)
                    .outputItems(HardItems.leadIngot, 2)
                    .inputHeat(2f)
                    .craftTime(90)
                    .build(),
            new Builder()
                    .inputItems(HardItems.galenaDust, 2)
                    .outputItems(HardItems.leadIngot, 1)
                    .inputHeat(3f)
                    .craftTime(60)
                    .build(),
            new Builder()
                    .inputItems(HardItems.tealliteDust, 2)
                    .outputItems(HardItems.tinIngot, 1)
                    .inputHeat(3f)
                    .craftTime(90)
                    .build(),
            new Builder()
                    .inputItems(HardItems.tinDust, 2)
                    .outputItems(HardItems.tinIngot, 1)
                    .inputHeat(4f)
                    .craftTime(90)
                    .build(),
            new Builder()
                    .inputItems(HardItems.bronzeDust, 2)
                    .outputItems(HardItems.bronzeIngot, 1)
                    .inputHeat(4f)
                    .craftTime(90)
                    .build()
    );
    // heater
    public static final Seq<Recipe> ELECTRIC_HEATER_TIER_1 = Seq.with(
            new Builder()
                    .inputPower(0.2f)
                    .outputHeat(1f)
                    .build()
    );

    private static class Builder {
        private Seq<ItemStack> inputItems;
        private Seq<LiquidStack> inputFluids;
        private float inputPower;
        private float inputHeat;
        private Seq<ItemStack> outputItems;
        private Seq<LiquidStack> outputFluids;
        private float outputPower;
        private float outputHeat;
        private float time = 0;
        private boolean isConsumePower = false;
        private boolean isConsumeHeat = false;
        private boolean isOutputPower = false;
        private boolean isOutputHeat = false;

        public Builder inputItems(Object... stacks) {
            this.inputItems = Seq.with(ItemStack.with(stacks));
            return this;
        }

        public Builder inputFluids(Object... stacks) {
            this.inputFluids = Seq.with(LiquidStack.with(stacks));
            return this;
        }

        public Builder inputPower(float inputPower) {
            this.isConsumePower = true;
            this.inputPower = inputPower;
            return this;
        }

        public Builder inputHeat(float inputHeat) {
            this.isConsumeHeat = true;
            this.inputHeat = inputHeat;
            return this;
        }

        public Builder outputItems(Object... stacks) {
            this.outputItems = Seq.with(ItemStack.with(stacks));
            return this;
        }

        public Builder outputFluids(Object... stacks) {
            this.outputFluids = Seq.with(LiquidStack.with(stacks));
            return this;
        }

        public Builder outputPower(float outputPower) {
            this.isOutputPower = true;
            this.outputPower = outputPower;
            return this;
        }

        public Builder outputHeat(float outputHeat) {
            this.isOutputHeat = true;
            this.outputHeat = outputHeat;
            return this;
        }

        public Builder craftTime(float craftTime) {
            this.time = craftTime;
            return this;
        }

        public Recipe build() {
            return new Recipe() {{
                input = new IOEntry() {{
                    if(Objects.nonNull(inputItems)) items = inputItems;
                    if(Objects.nonNull(inputFluids)) fluids = inputFluids;
                    if(isConsumePower) power = inputPower;
                    if(isConsumeHeat) heat = inputHeat;

                }};
                output = new IOEntry() {{
                    if(Objects.nonNull(outputItems)) items = outputItems;
                    if(Objects.nonNull(outputFluids)) fluids = outputFluids;
                    if(isOutputPower) power = outputPower;
                    if(isOutputHeat) heat = outputHeat;
                }};
                craftTime = time;
            }};
        }
    }

    private static class ResultBuilder {
        private Seq<ItemStack> inputItems;
        private Seq<LiquidStack> inputFluids;
        private float inputPower;
        private float inputHeat;
        private Seq<ItemStack> resultItems;
        private float[] dropChances;
        private Seq<LiquidStack> outputFluids;
        private float outputPower;
        private float outputHeat;
        private float time = 0;
        private boolean isConsumePower = false;
        private boolean isConsumeHeat = false;
        private boolean isOutputPower = false;
        private boolean isOutputHeat = false;

        public ResultBuilder inputItems(Object... stacks) {
            this.inputItems = Seq.with(ItemStack.with(stacks));
            return this;
        }

        public ResultBuilder inputFluids(Object... stacks) {
            this.inputFluids = Seq.with(LiquidStack.with(stacks));
            return this;
        }

        public ResultBuilder inputPower(float inputPower) {
            this.isConsumePower = true;
            this.inputPower = inputPower;
            return this;
        }

        public ResultBuilder inputHeat(float inputHeat) {
            this.isConsumeHeat = true;
            this.inputHeat = inputHeat;
            return this;
        }

        public ResultBuilder resultItems(Object... stacks) {
            this.resultItems = Seq.with(ItemStack.with(stacks));
            return this;
        }

        public ResultBuilder dropChances(float... chances) {
            this.dropChances = chances;
            return this;
        }

        public ResultBuilder outputFluids(Object... stacks) {
            this.outputFluids = Seq.with(LiquidStack.with(stacks));
            return this;
        }

        public ResultBuilder outputPower(float outputPower) {
            this.isOutputPower = true;
            this.outputPower = outputPower;
            return this;
        }

        public ResultBuilder outputHeat(float outputHeat) {
            this.isOutputHeat = true;
            this.outputHeat = outputHeat;
            return this;
        }

        public ResultBuilder craftTime(float craftTime) {
            this.time = craftTime;
            return this;
        }

        public ResultRecipe build() {
            return new ResultRecipe() {{
                input = new IOEntry() {{
                    if(Objects.nonNull(inputItems)) items = inputItems;
                    if(Objects.nonNull(inputFluids)) fluids = inputFluids;
                    if(isConsumePower) power = inputPower;
                    if(isConsumeHeat) heat = inputHeat;

                }};
                output = new IOEntry() {{
                    if(Objects.nonNull(resultItems)) items = resultItems;
                    if(Objects.nonNull(outputFluids)) fluids = outputFluids;
                    if(isOutputPower) power = outputPower;
                    if(isOutputHeat) heat = outputHeat;
                }};
                dropChances = ResultBuilder.this.dropChances;
                craftTime = time;
            }};
        }
    }
}
