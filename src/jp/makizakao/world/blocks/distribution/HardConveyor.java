package jp.makizakao.world.blocks.distribution;

import arc.struct.Seq;
import jp.makizakao.content.HardBlocks;
import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.Conveyor;

import java.util.Iterator;

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
        if(consumesPower) {
            consumePower(builder.powerConsume);
            outputsPower = false;
        }
    }

    public static IRequirementsBuilder<IPowerConsumeBuilder<Builder>> create(
            String name, int health, float speed, float displayedSpeed) {
        return new Builder(name, health, speed, displayedSpeed);
    }

    @Override
    public void init() {
        super.init();
        junctionReplacement = HardBlocks.copperJunction;
        bridgeReplacement = null;
    }

    public class HardConveyorBuild extends ConveyorBuild {
        @Override
        public Seq<Building> getPowerConnections(Seq<Building> out) {
            out.clear();
            if (this.power == null) {
                return out;
            } else {
                Iterator<Building> var2 = this.proximity.iterator();

                while(true) {
                    Building other;
                    do {
                        do {
                            do {
                                do {
                                    if (!var2.hasNext()) {
                                        for(int i = 0; i < this.power.links.size; ++i) {
                                            Tile link = Vars.world.tile(this.power.links.get(i));
                                            if (link != null && link.build != null && link.build.power != null
                                                    && link.build.team == this.team) {
                                                out.add(link.build);
                                            }
                                        }

                                        return out;
                                    }

                                    other = var2.next();
                                } while(other == null);
                            } while(other.power == null);
                        } while(other.team != this.team);
                    } while(!(other.block instanceof HardConveyor));
                    if (this.conductsTo(other) && other.conductsTo(this)
                            && !this.power.links.contains(other.pos())) {
                        out.add(other);
                    }
                }
            }
        }
    }

    public static class Builder implements IRequirementsBuilder<IPowerConsumeBuilder<Builder>>,
            IPowerConsumeBuilder<Builder>, IBuildCostMultiplierBuilder<Builder> {
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

        public HardConveyor build() {
            return new HardConveyor(this);
        }
    }
}
