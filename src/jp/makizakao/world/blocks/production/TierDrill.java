package jp.makizakao.world.blocks.production;

import arc.math.Mathf;
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

    public static Builder create(String name, int health, int size, int tier) {
        return new Builder(name, health, size, tier);
    }

    @Override
    public float getDrillTime(Item item) {
        return (drillTime * Mathf.pow(hardnessDrillMultiplier, item.hardness)) / drillMultipliers.get(item, 1f);
    }

    public static class Builder {
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

        public Builder drillTime(int drillTime) {
            this.drillTime = drillTime;
            return this;
        }

        public Builder itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }

        public Builder consumePower(float powerConsumption) {
            this.hasPower = true;
            this.powerConsumption = powerConsumption;
            return this;
        }

        public Builder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Drill build() {
            if(name == null) throw new IllegalArgumentException("Name must be set.");
            if(requirements == null) throw new IllegalArgumentException("Requirements must be set.");
            return new TierDrill(this);
        }
    }
}
