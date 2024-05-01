package jp.makizakao.world.blocks.distribution;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.distribution.Junction;

import java.util.Objects;

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

    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
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
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
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

        public HardJunction build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set.");
            if(Objects.isNull(requirements)) throw new IllegalArgumentException("Requirements must be set.");
            return new HardJunction(this);
        }
    }
}
