package jp.makizakao.hardustryex.world.blocks.production;

import arc.audio.Sound;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.struct.Seq;
import arc.util.io.Reads;
import arc.util.io.Writes;
import jp.makizakao.hardustryex.type.recipe.IRecipeCraft;
import jp.makizakao.hardustryex.world.bar.CrafterBarManager;
import jp.makizakao.hardustryex.world.bar.IBarManager;
import jp.makizakao.hardustryex.world.stat.CrafterStatsManager;
import jp.makizakao.hardustryex.world.stat.IMultiStatsManager;
import jp.makizakao.hardustryex.world.temperature.BasicTemperatureManager;
import jp.makizakao.hardustryex.world.temperature.ITemperatureManager;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawBlock;
import multicraft.MultiCrafter;
import multicraft.Recipe;

public class HardMultiCrafter extends MultiCrafter {
    protected final float minEfficiency = 0.1f;
    protected IBarManager<HardMultiCrafter> barManager = new CrafterBarManager();
    protected IMultiStatsManager<HardMultiCrafter> statsManager = new CrafterStatsManager();

    protected HardMultiCrafter(String name) {
        super(name);
    }

    protected HardMultiCrafter(Builder builder) {
        super(builder.name);
        requirements(Category.crafting, builder.requirements);
        this.health = builder.health;
        this.size = builder.size;
        this.itemCapacity = builder.itemCapacity;
        this.resolvedRecipes = builder.recipes;
        this.liquidCapacity = builder.liquidCapacity;
        if(ambientSound != Sounds.none) {
            this.ambientSound = builder.ambientSound;
            this.ambientSoundVolume = builder.ambientSoundVolume;
        }
        if(builder.drawer != null) this.drawer = builder.drawer;
    }

    @Override
    public void setBars() {
        super.setBars();
        barManager.setBars(this);
    }

    @Override
    public void setStats() {
        super.setStats();
        statsManager.setStats(this);
    }

    @Override
    public void buildStats(Table stat) {
        statsManager.buildStats(this, stat);
    }

    @Override
    public void buildIOEntry(Table table, Recipe recipe, boolean isInput) {
        super.buildIOEntry(table, recipe, isInput);
    }

    public boolean isConsumeHeat() {
        return isConsumeHeat;
    }

    public boolean isOutputHeat() {
        return isOutputHeat;
    }

    public boolean getShowNameTooltip() {
        return showNameTooltip;
    }



    public class HardMultiCrafterBuild extends MultiCrafterBuild {
        protected ITemperatureManager temperatureManager = new BasicTemperatureManager(minEfficiency);
        protected float temperatureEfficiency = 0f;

        @Override
        public void updateTile() {
            Recipe cur = this.getCurRecipe();
            if(cur.isConsumeHeat()) {
                this.heat = this.calculateHeat(this.sideHeat);
                temperatureManager.setTemperature(temperatureManager.calcTemperature(cur, heat, delta()));
                if(heat < cur.maxHeat() / 2) craftingTime = 0;
                temperatureEfficiency = temperatureManager.calcTemperatureEfficiency(cur);
            }
            if(cur.isOutputHeat()) {
                heat = Mathf.approachDelta(heat, cur.output.heat * efficiency
                        + (isConsumeHeat ? this.calculateHeat(this.sideHeat) : 0), warmupRate * delta());
            }

            super.updateTile();
        }

        @Override
        public void craft() {
            IRecipeCraft cur = (IRecipeCraft) this.getCurRecipe();
            cur.craft(this);
        }

        @Override
        public float getProgressIncrease(float baseTime) {
            Recipe cur = this.getCurRecipe();
            if (cur.isConsumeHeat()) {
                return super.getProgressIncrease(baseTime) * Math.min(1, this.heat / cur.maxHeat());
            }
            return super.getProgressIncrease(baseTime);
        }

        @Override
        public float edelta() {
            Recipe cur = this.getCurRecipe();
            return super.edelta() * (cur.isConsumeHeat() ? temperatureEfficiency : 1f);
        }

        @Override
        public void updateBars() {
            HardMultiCrafter.this.barMap.clear();
            HardMultiCrafter.this.setBars();
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(temperatureManager.getTemperature());
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            temperatureManager.setTemperature(read.f());
        }

        public ITemperatureManager getTemperatureManager() {
            return temperatureManager;
        }
    }

    public static final class Builder {
        private String name;
        private int size;
        private int health;
        private ItemStack[] requirements;
        private Seq<Recipe> recipes;
        private DrawBlock drawer;
        private Sound ambientSound = Sounds.none;
        private float ambientSoundVolume = 0f;
        private int itemCapacity = 10;
        private float liquidCapacity = 0f;

        private Builder() {}

        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.recipes = builder.recipes;
        }

        public static IRequirementsBuilder<IResolveRecipesBuilder<Builder>> create(String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static final class RequiredBuilder implements
                IRequirementsBuilder<IResolveRecipesBuilder<Builder>>,
                IResolveRecipesBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private Seq<Recipe> recipes;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public IResolveRecipesBuilder<Builder> requirements(Object... stacks) {
                requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public Builder resolveRecipes(Seq<Recipe> recipes) {
                this.recipes = recipes;
                return new Builder(this);
            }
        }

        public Builder itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }

        public Builder ambientSound(Sound sound, float volume) {
            this.ambientSound = sound;
            this.ambientSoundVolume = volume;
            return this;
        }

        public Builder drawer(DrawBlock drawer) {
            this.drawer = drawer;
            return this;
        }

        public Builder liquidCapacity(float liquidCapacity) {
            this.liquidCapacity = liquidCapacity;
            return this;
        }

        public HardMultiCrafter build() {
            return new HardMultiCrafter(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IResolveRecipesBuilder<T> {
            T resolveRecipes(Seq<Recipe> recipes);
        }
    }
}
