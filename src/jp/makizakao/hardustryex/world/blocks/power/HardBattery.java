package jp.makizakao.hardustryex.world.blocks.power;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.Battery;

public class HardBattery extends Battery {

    private HardBattery(String name) {
        super(name);
    }

    private HardBattery(Builder builder) {
        super(builder.name);
        requirements(Category.power, builder.requirements);
        health = builder.health;
        size = builder.size;
        consumePowerBuffered(builder.capacity);
    }

    public static class Builder {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;
        private float capacity;

        private Builder() {}

        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.capacity = builder.capacity;
        }

        public static IRequirementsBuilder<ICapacityBuilder<Builder>> create(String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<ICapacityBuilder<Builder>>,
                ICapacityBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private float capacity;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public ICapacityBuilder<Builder> requirements(Object... stacks) {
                this.requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public Builder capacity(float capacity) {
                this.capacity = capacity;
                return new Builder(this);
            }
        }

        public HardBattery build() {
            return new HardBattery(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface ICapacityBuilder<T> {
            T capacity(float capacity);
        }
    }
}
