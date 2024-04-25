package jp.makizakao.world.blocks.power;

import arc.func.Floatf;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.modules.PowerModule;

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
        consumesPower = builder.powerConsumeMultiplier > 0f;
        if(consumesPower) {
            powerConsumeMultiplier = builder.powerConsumeMultiplier;
            consumePowerDynamic(build -> build.power().graph.getPowerProduced() * powerConsumeMultiplier);
        }
    }

    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public static class Builder {
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private float laserRange = 10f;
        private int maxNodes = 4;
        private float powerConsumeMultiplier = -1f;

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
