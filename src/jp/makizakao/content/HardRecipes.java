package jp.makizakao.content;

import arc.struct.Seq;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;
import multicraft.Recipe;

import java.util.Objects;

public class HardRecipes {
    // crusher
    public static final Seq<Recipe> CRUSHER_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.nativeCopper, 1)
                    .inputPower(0.4f)
                    .outputItems(HardItems.copperDust, 2)
                    .craftTime(60f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.galena, 1)
                    .inputPower(0.6f)
                    .outputItems(HardItems.galenaDust, 2)
                    .craftTime(60f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.teallite, 1)
                    .inputPower(0.6f)
                    .outputItems(HardItems.tealliteDust, 1)
                    .craftTime(60f)
                    .build(),
            new Builder()
                    .inputItems(HardItems.tinIngot, 1)
                    .inputPower(0.6f)
                    .outputItems(HardItems.tinDust, 1)
                    .craftTime(60f)
                    .build()
    );
    // dust mixer
    public static final Seq<Recipe> DUST_MIXER_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.copperDust, 3, HardItems.tinDust, 1)
                    .inputPower(0.2f)
                    .outputItems(HardItems.bronzeDust, 4)
                    .craftTime(150f)
                    .build()
    );
    // furnace
    public static final Seq<Recipe> FURNACE_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.copperDust, 2)
                    .inputHeat(2f)
                    .outputItems(HardItems.copperIngot, 1)
                    .craftTime(60)
                    .build(),
            new Builder()
                    .inputItems(HardItems.leadDust, 2)
                    .outputItems(HardItems.leadIngot, 1)
                    .inputHeat(2f)
                    .craftTime(60)
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
}
