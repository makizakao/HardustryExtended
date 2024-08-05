package jp.makizakao.world.blocks.distribution;

import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.gen.Building;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.blocks.distribution.Sorter;

public class HardSorter extends Sorter {
    private HardSorter(String name) {
        super(name);
    }

    private HardSorter(Builder builder) {
        super(builder.name);
        requirements(Category.distribution, builder.requirements);
        this.invert = builder.invert;
        health = builder.health;
        size = builder.size;
        buildCostMultiplier = builder.buildCostMultiplier;
        consumesPower = builder.powerConsume > 0f;
        update = true;
        if(consumesPower) {
            consumePower(builder.powerConsume);
            outputsPower = false;
        }
    }

    public static IRequirementsBuilder<IPowerConsumeBuilder<Builder>> create(
            String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public class HardSorterBuild extends SorterBuild {
        @Override
        public boolean acceptItem(Building source, Item item) {
            return canConsume() && super.acceptItem(source, item);
        }

        @Override
        public void handleItem(Building source, Item item) {
            if(canConsume()) consume();
            super.handleItem(source, item);
        }
    }

    public static class Builder implements IRequirementsBuilder<IPowerConsumeBuilder<Builder>>,
            IPowerConsumeBuilder<Builder>, IBuildCostMultiplierBuilder<Builder> {
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private boolean invert = false;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
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

        public Builder invert() {
            this.invert = true;
            return this;
        }


        public HardSorter build() {
            return new HardSorter(this);
        }
    }
}
