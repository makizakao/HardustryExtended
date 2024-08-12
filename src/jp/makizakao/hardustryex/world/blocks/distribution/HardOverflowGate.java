package jp.makizakao.hardustryex.world.blocks.distribution;

import arc.struct.Seq;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.Tile;
import mindustry.world.blocks.distribution.OverflowGate;

import java.util.Iterator;

public class HardOverflowGate extends OverflowGate implements IConnectable {
    private HardOverflowGate(String name) { super(name); }

    private HardOverflowGate(Builder builder) {
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

    public class HardOverflowBuild extends OverflowGateBuild {

        @Override
        public boolean acceptItem(Building source, Item item) {
            return canConsume() && super.acceptItem(source, item);
        }

        @Override
        public void handleItem(Building source, Item item) {
            if(canConsume()) consume();
            super.handleItem(source, item);
        }

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
                    } while(!(canConnect(other.block)));
                    if (this.conductsTo(other) && other.conductsTo(this)
                            && !this.power.links.contains(other.pos())) {
                        out.add(other);
                    }
                }
            }
        }
    }

    public static class Builder  {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;
        private boolean invert = false;
        private float buildCostMultiplier = 1f;
        private float powerConsume = -1f;

        private Builder() {}

        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.powerConsume = builder.powerConsume;
        }

        public static IRequirementsBuilder<IPowerConsumeBuilder<Builder>> create(String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<IPowerConsumeBuilder<Builder>>,
                IPowerConsumeBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private float powerConsume = -1f;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size) {
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
                return new Builder(this);
            }
        }

        public Builder buildCostMultiplier(float buildCostMultiplier) {
            this.buildCostMultiplier = buildCostMultiplier;
            return this;
        }

        public Builder invert() {
            this.invert = true;
            return this;
        }

        public HardOverflowGate build() {
            return new HardOverflowGate(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IPowerConsumeBuilder<T> {
            T powerConsume(float powerConsume);
        }
    }
}
