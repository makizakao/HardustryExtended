package jp.makizakao.content;

import arc.struct.Seq;
import arc.util.Log;
import jp.makizakao.type.ResultEntry;
import jp.makizakao.type.ResultRecipe;
import jp.makizakao.type.ResultStack;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;
import multicraft.Recipe;

import java.util.Objects;

public class HardRecipes {
    // crusher
    public static final Seq<ResultRecipe> CRUSHER_TIER_1 = Seq.with(
            new ResultBuilder()
                    .resultItems(HardItems.copperDust, 2, 1.0f)
                    .inputItems(HardItems.nativeCopper, 1)
                    .inputPower(0.6f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .resultItems(HardItems.galenaDust, 2, 1.0f)
                    .inputItems(HardItems.galena, 1)
                    .inputPower(0.4f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .resultItems(HardItems.tealliteDust, 1, 1.0f)
                    .inputItems(HardItems.teallite, 1)
                    .inputPower(0.6f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .resultItems(HardItems.tinDust, 1, 1.0f)
                    .inputItems(HardItems.tinIngot, 1)
                    .inputPower(0.4f)
                    .craftTime(60f)
                    .build(),
            new ResultBuilder()
                    .resultItems(HardItems.copperDust, 1, 1.0f, HardItems.zincDust, 1, 0.2f)
                    .inputItems(HardItems.tetrahedrite, 1)
                    .inputPower(0.6f)
                    .craftTime(60f)
                    .build()
    );
    // dust mixer
    public static final Seq<Recipe> DUST_MIXER_TIER_1 = Seq.with(
            new Builder()
                    .inputItems(HardItems.copperDust, 3, HardItems.tinDust, 1)
                    .inputPower(0.1f)
                    .outputItems(HardItems.bronzeDust, 4)
                    .craftTime(150f)
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
                    .inputPower(0.4f)
                    .outputHeat(1f)
                    .build()
    );

    private static class Builder {
        protected Seq<ItemStack> inputItems;
        protected Seq<LiquidStack> inputFluids;
        protected float inputPower;
        protected float inputHeat;
        protected Seq<ItemStack> outputItems;
        protected Seq<LiquidStack> outputFluids;
        protected float outputPower;
        protected float outputHeat;
        protected float time = 0;
        protected boolean isConsumePower = false;
        protected boolean isConsumeHeat = false;
        protected boolean isOutputPower = false;
        protected boolean isOutputHeat = false;

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

    private static class ResultBuilder extends Builder {
        protected Seq<ResultStack> resultItems;

        public ResultBuilder resultItems(Object... stacks) {
            resultItems = Seq.with(ResultStack.with(stacks));
            return this;
        }

        @Override
        public ResultBuilder craftTime(float craftTime) {
            super.craftTime(craftTime);
            return this;
        }

        @Override
        public ResultBuilder inputFluids(Object... stacks) {
            super.inputFluids(stacks);
            return this;
        }

        @Override
        public ResultBuilder inputHeat(float inputHeat) {
            super.inputHeat(inputHeat);
            return this;
        }

        @Override
        public ResultBuilder inputItems(Object... stacks) {
            super.inputItems(stacks);
            return this;
        }

        @Override
        public ResultBuilder inputPower(float inputPower) {
            super.inputPower(inputPower);
            return this;
        }

        @Override
        public ResultBuilder outputFluids(Object... stacks) {
            super.outputFluids(stacks);
            return this;
        }

        @Override
        public ResultBuilder outputHeat(float outputHeat) {
            super.outputHeat(outputHeat);
            return this;
        }

        @Override
        public Builder outputItems(Object... stacks) {
            return this;
        }

        @Override
        public ResultBuilder outputPower(float outputPower) {
            super.outputPower(outputPower);
            return this;
        }

        @Override
        public ResultRecipe build() {
            return new ResultRecipe() {{
                input = new IOEntry() {{
                    if(Objects.nonNull(inputItems)) items = inputItems;
                    if(Objects.nonNull(inputFluids)) fluids = inputFluids;
                    if(isConsumePower) power = inputPower;
                    if(isConsumeHeat) heat = inputHeat;
                }};
                output = new ResultEntry() {{
                    if(Objects.nonNull(resultItems)) items = resultItems;
                    if(Objects.nonNull(outputFluids)) fluids = inputFluids;
                    if(isOutputPower) power = outputPower;
                    if(isOutputHeat) heat = outputHeat;
                }};
                craftTime = time;
            }};
        }
    }
}
