package jp.makizakao.world.blocks.power;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.Battery;

import java.util.Objects;

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

    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public static class Builder {
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private float capacity = 1000f;


        private Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public Builder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Builder capacity(float capacity) {
            this.capacity = capacity;
            return this;
        }

        public HardBattery build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            if(Objects.isNull(requirements)) throw new IllegalArgumentException("Requirements must be set");
            return new HardBattery(this);
        }
    }
}
