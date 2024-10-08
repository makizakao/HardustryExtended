package jp.makizakao.world.block.power;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.PowerNode;

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

    public static class Builder  {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;
        private float laserRange = 10f;
        private int maxNodes = 4;
        private float powerConsumeMultiplier = -1f;

        private Builder() {}

        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.laserRange = builder.laserRange;
            this.powerConsumeMultiplier = builder.powerConsumeMultiplier;
        }

        public static IRequirementsBuilder<ILaserRangeBuilder<IPowerConsumeBuilder<Builder>>> create(
                String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<ILaserRangeBuilder<IPowerConsumeBuilder<Builder>>>,
                ILaserRangeBuilder<IPowerConsumeBuilder<Builder>>,
                IPowerConsumeBuilder<Builder> {
            private final String name;
            private final int health;
            private final int size;
            private ItemStack[] requirements;
            private float laserRange = 10f;
            private float powerConsumeMultiplier = -1f;

            private RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public ILaserRangeBuilder<IPowerConsumeBuilder<Builder>> requirements(Object... stacks) {
                this.requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public IPowerConsumeBuilder<Builder> laserRange(float laserRange) {
                this.laserRange = laserRange;
                return this;
            }

            @Override
            public Builder powerConsume(float powerConsumeMultiplier) {
                this.powerConsumeMultiplier = powerConsumeMultiplier;
                return new Builder(this);
            }
        }


        public Builder maxNodes(int maxNodes) {
            this.maxNodes = maxNodes;
            return this;
        }

        public HardPowerNode build() {
            return new HardPowerNode(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface ILaserRangeBuilder<T> {
            T laserRange(float laserRange);
        }

        public interface IPowerConsumeBuilder<T> {
            T powerConsume(float powerConsumeMultiplier);
        }
    }
}
