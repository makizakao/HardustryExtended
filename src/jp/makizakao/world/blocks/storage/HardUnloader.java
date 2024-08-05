package jp.makizakao.world.blocks.storage;

import jp.makizakao.world.builder.BaseBlockBuilder.*;
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

    public static IRequirementsBuilder<IPowerConsumeBuilder<Builder>> create(
            String name, int health, int size, float speed) {
        return new Builder(name, health, size, speed);
    }

    public class HardUnloaderBuild extends UnloaderBuild {
        @Override
        public void updateTile() {
            if(canConsume()) super.updateTile();
        }
    }

    public static class Builder implements IRequirementsBuilder<IPowerConsumeBuilder<Builder>>,
            IPowerConsumeBuilder<Builder>, IBuildCostMultiplierBuilder<Builder> {
        private final String name;
        private final int health;
        private final int size;
        private final float speed;
        private ItemStack[] requirements;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder(String name, int health, int size, float speed) {
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
            return this;
        }

        @Override
        public Builder buildCostMultiplier(float buildCostMultiplier) {
            this.buildCostMultiplier = buildCostMultiplier;
            return this;
        }

        public HardUnloader build() {
            return new HardUnloader(this);
        }
    }

}
