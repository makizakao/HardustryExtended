package jp.makizakao.world.block.production;

import arc.math.Mathf;
import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.blocks.production.Drill;

public class TierDrill extends Drill {
    protected float hardnessDrillMultiplier = 1.5f;

    private TierDrill(String name) {
        super(name);
    }

    private TierDrill(Builder builder) {
        super(builder.name);
        requirements(Category.production, builder.requirements);
        health = builder.health;
        size = builder.size;
        tier = builder.tier;
        drillTime = builder.drillTime;
        itemCapacity = builder.itemCapacity;
        hasPower = builder.hasPower;
        if(hasPower) consumePower(builder.powerConsumption);
    }

    public static IRequirementsBuilder<IItemCapacityBuilder<IDrillTimeBuilder<IPowerConsumeBuilder<Builder>>>> create(
            String name, int health, int size, int tier) {
        return new Builder(name, health, size, tier);
    }

    @Override
    public float getDrillTime(Item item) {
        return (drillTime * Mathf.pow(hardnessDrillMultiplier, item.hardness)) / drillMultipliers.get(item, 1f);
    }

    public static class Builder implements IRequirementsBuilder<
            IItemCapacityBuilder<IDrillTimeBuilder<IPowerConsumeBuilder<Builder>>>>,
            IItemCapacityBuilder<IDrillTimeBuilder<IPowerConsumeBuilder<Builder>>>,
            IDrillTimeBuilder<IPowerConsumeBuilder<Builder>>, IPowerConsumeBuilder<Builder> {
        private final String name;
        private final int health;
        private final int size;
        private final int tier;
        private ItemStack[] requirements;
        private int drillTime = 3600;
        private int itemCapacity = 10;
        private boolean hasPower = false;
        private float powerConsumption = 0;

        private Builder(String name, int health, int size, int tier) {
            this.name = name;
            this.health = health;
            this.size = size;
            this.tier = tier;
        }

        @Override
        public IItemCapacityBuilder<IDrillTimeBuilder<IPowerConsumeBuilder<Builder>>> requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        @Override
        public IDrillTimeBuilder<IPowerConsumeBuilder<Builder>> itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }

        @Override
        public IPowerConsumeBuilder<Builder> drillTime(int drillTime) {
            this.drillTime = drillTime;
            return this;
        }

        @Override
        public Builder powerConsume(float powerConsumption) {
            this.hasPower = true;
            this.powerConsumption = powerConsumption;
            return this;
        }

        public Drill build() {
            return new TierDrill(this);
        }
    }
}
