package jp.makizakao.builder;

import arc.struct.Seq;
import jp.makizakao.type.recipe.HardRecipe;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;

import java.util.Objects;

public class RecipeBuilder {

    private RecipeBuilder() {}

    public static InputBuilder input() {
        return new InputBuilder();
    }

    public static class InputBuilder {
        protected Seq<ItemStack> inputItems;
        protected Seq<LiquidStack> inputFluids;
        protected float inputPower;
        protected float inputHeat;
        protected boolean isConsumePower = false;
        protected boolean isConsumeHeat = false;

        protected InputBuilder() {}

        public InputBuilder items(Object... stacks) {
            inputItems = Seq.with(ItemStack.with(stacks));
            return this;
        }

        public InputBuilder fluids(Object... stacks) {
            inputFluids = Seq.with(LiquidStack.with(stacks));
            return this;
        }

        public InputBuilder power(float inputPower) {
            this.isConsumePower = true;
            this.inputPower = inputPower;
            return this;
        }

        public InputBuilder heat(float inputHeat) {
            this.isConsumeHeat = true;
            this.inputHeat = inputHeat;
            return this;
        }

        public OutputBuilder output() {
            return new OutputBuilder(this);
        }
    }

    public static class OutputBuilder {
        protected InputBuilder input;
        protected Seq<ItemStack> outputItems;
        protected Seq<LiquidStack> outputFluids;
        protected float outputPower;
        protected float outputHeat;
        protected float craftTime;
        protected boolean isOutputPower = false;
        protected boolean isOutputHeat = false;

        protected OutputBuilder() {}

        private OutputBuilder(InputBuilder input) {
            this.input = input;
        }

        public OutputBuilder items(Object... stacks) {
            outputItems = Seq.with(ItemStack.with(stacks));
            return this;
        }

        public OutputBuilder fluids(Object... stacks) {
            outputFluids = Seq.with(LiquidStack.with(stacks));
            return this;
        }

        public OutputBuilder power(float outputPower) {
            this.isOutputPower = true;
            this.outputPower = outputPower;
            return this;
        }

        public OutputBuilder heat(float outputHeat) {
            this.isOutputHeat = true;
            this.outputHeat = outputHeat;
            return this;
        }

        public Builder craftTime(float craftTime) {
            this.craftTime = craftTime;
            return new Builder(this);
        }
    }

    public static class Builder {
        protected OutputBuilder outputBuilder;
        protected InputBuilder inputBuilder;
        protected Builder() {}

        private Builder(OutputBuilder output) {
            this.outputBuilder = output;
            this.inputBuilder = output.input;
        }

        public HardRecipe build() {
            return new HardRecipe() {{
                input = new IOEntry() {{
                    if(Objects.nonNull(inputBuilder.inputItems)) items = inputBuilder.inputItems;
                    if(Objects.nonNull(inputBuilder.inputFluids)) fluids = inputBuilder.inputFluids;
                    if(inputBuilder.isConsumePower) power = inputBuilder.inputPower;
                    if(inputBuilder.isConsumeHeat) heat = inputBuilder.inputHeat;

                }};
                output = new IOEntry() {{
                    if(Objects.nonNull(outputBuilder.outputItems)) items = outputBuilder.outputItems;
                    if(Objects.nonNull(outputBuilder.outputFluids)) fluids = outputBuilder.outputFluids;
                    if(outputBuilder.isOutputPower) power = outputBuilder.outputPower;
                    if(outputBuilder.isOutputHeat) heat = outputBuilder.outputHeat;
                }};
                craftTime = outputBuilder.craftTime;
            }};
        }
    }
}