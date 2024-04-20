package jp.makizakao.world.blocks.power;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.PowerNode;

import java.util.Objects;

public class HardPowerNode extends PowerNode {
    private float powerConsumeMultiplier = 0f;

    private HardPowerNode(String name) {
        super(name);
    }

    private HardPowerNode(Builder builder) {
        super(builder.name);
        requirements(Category.power, builder.requirements);
        health = builder.health;
        size = builder.size;
        laserRange = builder.laserRange;
        maxNodes = builder.maxNodes;
        consumesPower = builder.isConsumePower;
        if(builder.isConsumePower) powerConsumeMultiplier = builder.powerConsumeMultiplier;
    }

    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public class HardPowerNodeBuild extends PowerNodeBuild {
        @Override
        public void updateTile() {
            super.updateTile();
            if(consumesPower) {
                this.block.consumePower(power.graph.getPowerBalance() * powerConsumeMultiplier);
                consume();
            }
        }
    }

    public static class Builder {
        public final String name;
        public final int health;
        public final int size;
        public ItemStack[] requirements;
        public float laserRange = 10f;
        public int maxNodes = 4;
        public float powerConsumeMultiplier = 0f;
        public boolean isConsumePower = false;

        private Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public Builder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Builder laserRange(float laserRange) {
            this.laserRange = laserRange;
            return this;
        }

        public Builder maxNodes(int maxNodes) {
            this.maxNodes = maxNodes;
            return this;
        }

        public Builder consumePower(float powerConsumeMultiplier) {
            this.isConsumePower = true;
            this.powerConsumeMultiplier = powerConsumeMultiplier;
            return this;
        }

        public HardPowerNode build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            if(Objects.isNull(requirements)) throw new IllegalArgumentException("Requirements must be set");
            return new HardPowerNode(this);
        }
    }
}
