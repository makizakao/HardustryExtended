package jp.makizakao.world.blocks.storage;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.storage.Unloader;

public class HardUnloader extends Unloader {
    private HardUnloader(String name) {
        super(name);
    }

    private HardUnloader(Builder builder) {
        super(builder.name);
        requirements(Category.effect, builder.requirements);
        health = builder.health;
        size = builder.size;
        speed = builder.speed;
        buildCostMultiplier = builder.buildCostMultiplier;
        consumesPower = builder.powerConsume > 0f;
        if(consumesPower) {
            consumePower(builder.powerConsume);
            outputsPower = false;
        }
    }

    public class HardUnloaderBuild extends UnloaderBuild {
        @Override
        public void updateTile() {
            if(canConsume()) super.updateTile();
        }
    }

    public static class Builder {
        private String name;
        private int health;
        private int size;
        private float speed;
        private ItemStack[] requirements;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder() {}

        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.speed = builder.speed;
            this.requirements = builder.requirements;
            this.powerConsume = builder.powerConsume;
        }

        public static IRequirementsBuilder<IPowerConsumeBuilder<Builder>> create(
                String name, int health, int size, float speed) {
            return new RequiredBuilder(name, health, size, speed);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<IPowerConsumeBuilder<Builder>>,
                IPowerConsumeBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private float speed;
            private ItemStack[] requirements;
            private float powerConsume = -1f;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size, float speed) {
                this.name = name;
                this.health = health;
                this.size = size;
                this.speed = speed;
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

        public HardUnloader build() {
            return new HardUnloader(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IPowerConsumeBuilder<T> {
            T powerConsume(float powerConsume);
        }
    }

}
