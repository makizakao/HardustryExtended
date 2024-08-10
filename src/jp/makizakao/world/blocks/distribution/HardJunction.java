package jp.makizakao.world.blocks.distribution;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.distribution.Junction;

public class HardJunction extends Junction {
    private HardJunction(String name) {
        super(name);
    }

    private HardJunction(Builder builder) {
        super(builder.name);
        requirements(Category.distribution, builder.requirements);
        health = builder.health;
        size = builder.size;
        buildCostMultiplier = builder.buildCostMultiplier;
        consumesPower = builder.powerConsume > 0f;
        if(consumesPower) {
            consumePower(builder.powerConsume);
            outputsPower = false;
        }
    }

    public class HardJunctionBuild extends JunctionBuild {
        @Override
        public void updateTile() {
            if(canConsume()) {
                super.updateTile();
            }
        }
    }

    public static class Builder {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder() {}

        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.powerConsume = builder.powerConsume;
        }

        public static IRequirementsBuilder<IPowerConsumeBuilder<Builder>> create(String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<IPowerConsumeBuilder<Builder>>,
                IPowerConsumeBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private float powerConsume = -1f;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public IPowerConsumeBuilder<Builder> requirements(Object... stacks) {
                this.requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public Builder powerConsume(float powerConsume) {
                this.powerConsume = powerConsume;
                return new Builder(this);
            }
        }

        public Builder buildCostMultiplier(float buildCostMultiplier) {
            this.buildCostMultiplier = buildCostMultiplier;
            return this;
        }


        public HardJunction build() {
            return new HardJunction(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IPowerConsumeBuilder<T> {
            T powerConsume(float powerConsume);
        }
    }
}
