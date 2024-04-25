package jp.makizakao.world.blocks.distribution;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.distribution.Conveyor;

import java.util.Objects;

public class HardConveyor extends Conveyor {
    private HardConveyor(String name) {
        super(name);
    }

    private HardConveyor(Builder builder) {
        super(builder.name);
        requirements(Category.distribution, builder.requirements);
        health = builder.health;
        speed = builder.speed;
        displayedSpeed = builder.displayedSpeed;
        buildCostMultiplier = builder.buildCostMultiplier;
        consumesPower = builder.powerConsume > 0f;
        if(consumesPower) consumePower(builder.powerConsume);
    }

    public static Builder create(String name, int health, float speed, float displayedSpeed) {
        return new Builder(name, health, speed, displayedSpeed);
    }

    public static class Builder {
        private final String name;
        private final int health;
        private final float speed;
        private final float displayedSpeed;
        private ItemStack[] requirements;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder(String name, int health, float speed, float displayedSpeed) {
            this.name = name;
            this.health = health;
            this.speed = speed;
            this.displayedSpeed = displayedSpeed;
        }

        public Builder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Builder buildCostMultiplier(float buildCostMultiplier) {
            this.buildCostMultiplier = buildCostMultiplier;
            return this;
        }

        public Builder powerConsume(float powerConsume) {
            this.powerConsume = powerConsume;
            return this;
        }

        public HardConveyor build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set.");
            if(Objects.isNull(requirements)) throw new IllegalArgumentException("Requirements must be set.");
            return new HardConveyor(this);
        }
    }
}
