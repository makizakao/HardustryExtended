package jp.makizakao.world.block.production;

import arc.Core;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.math.Mathf;
import arc.struct.Seq;
import arc.util.Time;
import arc.util.io.Reads;
import arc.util.io.Writes;
import jp.makizakao.world.type.ResultRecipe;
import jp.makizakao.world.type.entry.SmeltEntry;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.ui.Bar;
import mindustry.world.draw.DrawBlock;
import multicraft.MultiCrafter;
import multicraft.Recipe;

import java.util.Optional;

public class HardMultiCrafter extends MultiCrafter {
    protected final float outsideTemperature = 20f;
    protected final float temperaturePerHeat = 50f;
    protected final float temperatureIncrementsMultiplier = 0.5f;
    protected final float minEfficiency = 0.1f;

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
        setTemperatureBar();
    }

    protected void setTemperatureBar() {
        if(isConsumeHeat) {
            this.addBar("temperature", b ->
                    new Bar(((HardMultiCrafterBuild) b).getCurRecipe().input instanceof SmeltEntry s
                            ? Core.bundle.format("bar.crafter-temperature-stats",
                            (int) ((HardMultiCrafterBuild) b).temperature,
                            (int) s.temperature)
                            : "bar.temperature", Color.orange, ((HardMultiCrafterBuild) b)::temperatureFrac));
        }
    }



    public class HardMultiCrafterBuild extends MultiCrafterBuild {
        protected float temperature = outsideTemperature;
        protected float temperatureEfficiency = 0f;

        @Override
        public void updateTile() {
            Recipe cur = this.getCurRecipe();
            if(cur.isConsumeHeat()) {
                this.heat = this.calculateHeat(this.sideHeat);
                temperature = this.calcTemperature(cur);
                if(heat < cur.maxHeat() / 2) craftingTime = 0;
                Optional.of(cur).filter(r -> r.input instanceof SmeltEntry).map(r -> (SmeltEntry)r.input).ifPresent(
                        e -> temperatureEfficiency = this.calcTemperatureEfficiency(cur));
            }
            if(cur.isOutputHeat()) {
                heat = Mathf.approachDelta(heat, cur.output.heat * efficiency
                        + (isConsumeHeat ? this.calculateHeat(this.sideHeat) : 0), warmupRate * delta());
            }

            super.updateTile();
        }

        protected float calcTemperature(Recipe cur) {
            return Math.min(temperature + this.heat * temperatureIncrementsMultiplier * delta() / Time.toSeconds,
                    Math.max(temperature - delta() / Time.toSeconds,
                            this.heat * temperaturePerHeat + outsideTemperature));
        }

        protected float calcTemperatureEfficiency(Recipe cur) {
            return temperature < ((SmeltEntry)cur.input).temperature ? 0
                    : Math.min((temperature - ((SmeltEntry)cur.input).temperature) / (
                            (SmeltEntry)cur.input).temperature + minEfficiency, 1f);
        }

        public float temperatureFrac() {
            return Optional.of(this.getCurRecipe()).map(r -> (SmeltEntry)r.input)
                    .map(e -> e.temperature)
                    .map(t -> Mathf.clamp(temperature / t, 0, 1f))
                    .orElse(0f);
        }

        @Override
        public void craft() {
            if(getCurRecipe() instanceof ResultRecipe cur) {
                consume();
                var items = cur.output.items;
                if (cur.isOutputItem()) {
                    for (int i = 0; i < items.size; i++) for (int j = 0; j < items.get(i).amount; j++) {
                        if(Mathf.random() <= cur.dropChances[i]) {
                            offload(items.get(i).item);
                        }
                    }
                }

                if (wasVisible) createCraftEffect();
                if (cur.craftTime > 0f) craftingTime %= cur.craftTime;
                else craftingTime = 0f;
            }
            else super.craft();
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
            write.f(temperature);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            temperature = read.f();
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
