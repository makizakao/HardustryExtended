package jp.makizakao.world.blocks.power;

import jp.makizakao.world.BaseBuilder.*;
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

    public static IRequirementsBuilder<ICapacityBuilder<Builder>> create(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public static class Builder implements IRequirementsBuilder<ICapacityBuilder<Builder>>, ICapacityBuilder<Builder> {
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private float capacity;


        private Builder(String name, int health, int size) {
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
            return this;
        }

        public HardBattery build() {
            return new HardBattery(this);
        }
    }
}
