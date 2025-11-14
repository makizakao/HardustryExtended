package jp.makizakao.hardustryex.type.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.type.entry.SmeltEntry;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@SuperBuilder(builderMethodName = "of")
public class SmeltRecipe extends HardRecipe {
    private SmeltEntry input;
    private SmeltEntry output;

    public abstract static class SmeltRecipeBuilder<C extends SmeltRecipe, B extends SmeltRecipeBuilder<C, B>> {
        SmeltEntry input;
        IOEntry output;
        private float craftTime;

        public InputStep input() {
            return new InputStep(self());
        }

        public OutputStep output() {
            return new OutputStep(self());
        }

        public B craftTime(float t) {
            this.craftTime = t;
            return self();
        }

        public class InputStep extends Step<InputStep> {
            protected float temperature;

            InputStep(B parent) {
                super(parent);
            }

            public InputStep temperature(float temperature) {
                this.temperature = temperature;
                return InputStep.this;
            }

            public B complete() {
                parent.input = new SmeltEntry() {{
                    if (InputStep.this.items != null) items = InputStep.this.items;
                    if (InputStep.this.fluids != null) fluids = InputStep.this.fluids;
                    if (0 < InputStep.this.power) power = InputStep.this.power;
                    if (0 < InputStep.this.heat)  heat = InputStep.this.heat;
                    temperature = InputStep.this.temperature;
                }};
                return parent;
            }
        }

        public class OutputStep extends Step<OutputStep> {
            OutputStep(B parent) {
                super(parent);
            }

            public B complete() {
                parent.output = new IOEntry() {{
                    if (this.items != null) items = OutputStep.this.items;
                    if (OutputStep.this.fluids != null) fluids = OutputStep.this.fluids;
                    if (0 < OutputStep.this.power) power = OutputStep.this.power;
                    if (0 < OutputStep.this.heat)  heat = OutputStep.this.heat;
                }};
                return parent;
            }
        }

        public abstract class Step<T extends Step<T>> {
            protected final B parent;
            protected Seq<ItemStack> items;
            protected Seq<LiquidStack> fluids;
            protected float power;
            protected float heat;

            public Step(B parent) {
                this.parent = parent;
            }

            public T items(Object... stacks) {
                this.items = Seq.with(ItemStack.with(stacks));
                return (T) this;
            }

            public T fluids(Object... stacks) {
                fluids = Seq.with(LiquidStack.with(stacks));
                return (T) this;
            }

            public T power(float power) {
                this.power = power;
                return (T) this;
            }

            public T heat(float heat) {
                this.heat = heat;
                return (T) this;
            }

            public abstract B complete();
        }
    }
}