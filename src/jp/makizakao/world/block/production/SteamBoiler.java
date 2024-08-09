package jp.makizakao.world.block.production;

import arc.audio.Sound;
import arc.struct.Seq;
import arc.util.Time;
import jp.makizakao.world.type.entry.SmeltEntry;
import mindustry.entities.Effect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawBlock;
import multicraft.Recipe;

import java.util.Objects;

public class SteamBoiler extends ExplodableCrafter {
    protected SteamBoiler(String name) {
        super(name);
    }

    protected SteamBoiler(Builder builder) {
        this(builder.name);
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
        if(Objects.nonNull(builder.drawer)) this.drawer = builder.drawer;
        this.explodeTemperature= builder.explodeTemperature;
        this.explosionDamage = builder.damage;
        this.explosionRadius = builder.radius;
        this.hasLiquids = true;
        if(Objects.nonNull(builder.effect)) this.explodeEffect = builder.effect;
    }

    public class SteamBoilerBuild extends ExplodableCrafterBuild {
        protected float count = 0;
        @Override
        public void updateTile() {
            super.updateTile();
            var cur = getCurRecipe();
            count += Time.delta;
            if(count < Time.toSeconds * 3) return;
            count = 0;
            if(((SmeltEntry) cur.input).temperature <= temperature
                    && liquids.get(cur.input.fluids.get(0).liquid) < cur.input.fluids.get(0).amount * 60) {
                liquids.clear();
            }

        }

        @Override
        protected float calcTemperature(Recipe cur) {
            if(this.liquids.get(cur.input.fluids.get(0).liquid) <= cur.input.fluids.get(0).amount) return super.calcTemperature(cur);
            return Math.min(super.calcTemperature(cur), ((SmeltEntry) cur.input).temperature);
        }

        @Override
        protected float calcTemperatureEfficiency(Recipe cur) {
            return ((SmeltEntry) cur.input).temperature <= temperature ? 1f : 0f;
        }
    }

    public static class Builder {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;
        private int damage;
        private int radius;
        private float explodeTemperature = 0;
        private Effect effect;
        private Sound ambientSound = Sounds.none;
        private float ambientSoundVolume = 1.0F;
        private DrawBlock drawer;
        private int itemCapacity = 0;
        private float liquidCapacity = 0;
        private Seq<Recipe> recipes;

        protected Builder() {}

        private Builder(Builder.RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.explodeTemperature = builder.explodeTemperature;
            this.damage = builder.damage;
            this.radius = builder.radius;
        }

        public static IRequirementsBuilder<IExplodeTemperatureBuilder<Builder>> create(
                String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static final class RequiredBuilder implements IRequirementsBuilder<IExplodeTemperatureBuilder<Builder>>,
                IExplodeTemperatureBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private float explodeTemperature;
            private int damage;
            private int radius;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public IExplodeTemperatureBuilder<Builder> requirements(Object... stacks) {
                requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public Builder explodeTemperature(float explodeTemperature, int damage, int radius) {
                this.explodeTemperature = explodeTemperature;
                this.damage = damage;
                this.radius = radius;
                return new Builder(this);
            }
        }


        public Builder effect(Effect effect) {
            this.effect = effect;
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

        public Builder itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }


        public Builder liquidCapacity(float liquidCapacity) {
            this.liquidCapacity = liquidCapacity;
            return this;
        }


        public Builder resolveRecipes(Seq<Recipe> recipes) {
            this.recipes = recipes;
            return this;
        }


        public SteamBoiler build()
        {
            return new SteamBoiler(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IExplodeTemperatureBuilder<T> {
            T explodeTemperature(float explodeTemperature, int damage, int radius);
        }
    }
}
