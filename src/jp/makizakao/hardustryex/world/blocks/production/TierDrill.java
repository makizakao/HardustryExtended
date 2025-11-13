package jp.makizakao.hardustryex.world.blocks.production;

import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Eachable;
import jp.makizakao.hardustryex.content.drawer.HardDrawMultis;
import jp.makizakao.hardustryex.world.bar.DrillBarManager;
import jp.makizakao.hardustryex.world.bar.IBarManager;
import mindustry.entities.units.BuildPlan;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.blocks.production.Drill;
import mindustry.world.draw.DrawBlock;
import multicraft.MultiCrafter;

public class TierDrill extends Drill {
    protected float hardnessDrillMultiplier = 1.5f;
    private final IBarManager<TierDrill> barManager = new DrillBarManager();
    private final DrawBlock drawer = HardDrawMultis.DRILL.get();

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

    @Override
    public float getDrillTime(Item item) {
        return (drillTime * Mathf.pow(hardnessDrillMultiplier, item.hardness)) / drillMultipliers.get(item, 1f);
    }

    @Override
    public void setBars() {
        super.setBars();
        barManager.setBars(this);
    }

    @Override
    public void load() {
        super.load();
        this.drawer.load(this);
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        this.drawer.drawPlan(this, plan, list);
    }

    @Override
    public TextureRegion[] icons() {
        return this.drawer.finalIcons(this);
    }

    @Override
    public void getRegionsToOutline(Seq<TextureRegion> out) {
        this.drawer.getRegionsToOutline(this, out);
    }

    public class TierDrillBuild extends DrillBuild {
        @Override
        public void draw() {
            TierDrill.this.drawer.draw(this);
        }

        @Override
        public void drawLight() {
            super.drawLight();
            TierDrill.this.drawer.drawLight(this);
        }
    }

    public static class Builder {
        private final String name;
        private final int health;
        private final int size;
        private final int tier;
        private final ItemStack[] requirements;
        private final float drillTime;
        private int itemCapacity = 10;
        private boolean hasPower = false;
        private float powerConsumption = 0;


        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.tier = builder.tier;
            this.requirements = builder.requirements;
            this.drillTime = builder.drillTime;
        }

        public static IRequirementsBuilder<IDrillTimeBuilder<Builder>> create(
                String name, int health, int size, int tier) {
            return new RequiredBuilder(name, health, size, tier);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<IDrillTimeBuilder<Builder>>,
                IDrillTimeBuilder<Builder> {
            private final String name;
            private final int health;
            private final int size;
            private final int tier;
            private ItemStack[] requirements;
            private float drillTime = 0;


            private RequiredBuilder(String name, int health, int size, int tier) {
                this.name = name;
                this.health = health;
                this.size = size;
                this.tier = tier;
            }

            @Override
            public IDrillTimeBuilder<Builder> requirements(Object... stacks) {
                this.requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public Builder drillTime(float drillTime) {
                this.drillTime = drillTime;
                return new Builder(this);
            }
        }

        public Builder itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }

        public Builder powerConsume(float powerConsumption) {
            this.hasPower = true;
            this.powerConsumption = powerConsumption;
            return this;
        }

        public TierDrill build() {
            return new TierDrill(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IDrillTimeBuilder<T> {
            T drillTime(float drillTime);
        }
    }
}
