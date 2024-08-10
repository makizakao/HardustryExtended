package jp.makizakao.builder;

import jp.makizakao.type.entry.SmeltEntry;
import jp.makizakao.type.recipe.HardRecipe;
import multicraft.IOEntry;

import java.util.Objects;

public class SmeltRecipeBuilder {
    public static InputBuilder input() {
        return new InputBuilder();
    }

    public static class InputBuilder extends RecipeBuilder.InputBuilder {
        private float temperature = 0f;

        private InputBuilder() {}

        public InputBuilder temperature(float temperature) {
            this.temperature = temperature;
            return this;
        }

        @Override
        public InputBuilder fluids(Object... stacks) {
            super.fluids(stacks);
            return this;
        }

        @Override
        public InputBuilder heat(float inputHeat) {
            super.heat(inputHeat);
            return this;
        }

        @Override
        public InputBuilder items(Object... stacks) {
            super.items(stacks);
            return this;
        }

        @Override
        public InputBuilder power(float inputPower) {
            super.power(inputPower);
            return this;
        }

        @Override
        public OutputBuilder output() {
            return new OutputBuilder(this);
        }
    }

    public static class OutputBuilder extends RecipeBuilder.OutputBuilder {
        private InputBuilder input;

        private OutputBuilder() {}

        private OutputBuilder(InputBuilder input) {
            this.input = input;
        }

        @Override
        public OutputBuilder fluids(Object... stacks) {
            super.fluids(stacks);
            return this;
        }

        @Override
        public OutputBuilder heat(float outputHeat) {
            super.heat(outputHeat);
            return this;
        }

        @Override
        public OutputBuilder items(Object... stacks) {
            super.items(stacks);
            return this;
        }

        @Override
        public OutputBuilder power(float outputPower) {
            super.power(outputPower);
            return this;
        }

        @Override
        public Builder craftTime(float craftTime) {
            super.craftTime(craftTime);
            return new Builder(this);
        }
    }

    public static class Builder extends RecipeBuilder.Builder {
        private InputBuilder inputBuilder;
        private OutputBuilder outputBuilder;

        private Builder() {}

        private Builder(OutputBuilder output) {
            this.inputBuilder = output.input;
            this.outputBuilder = output;
        }

        @Override
        public HardRecipe build() {
            return new HardRecipe() {{
                input = new SmeltEntry() {{
                    if(Objects.nonNull(inputBuilder.inputItems)) items = inputBuilder.inputItems;
                    if(Objects.nonNull(inputBuilder.inputFluids)) fluids = inputBuilder.inputFluids;
                    if(inputBuilder.isConsumePower) power = inputBuilder.inputPower;
                    if(inputBuilder.isConsumeHeat) heat = inputBuilder.inputHeat;
                    temperature = inputBuilder.temperature;
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
