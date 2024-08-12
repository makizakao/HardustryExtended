package jp.makizakao.hardustryex.world.blocks.production;

import arc.audio.Sound;
import arc.struct.Seq;
import jp.makizakao.hardustryex.world.bar.ExplodableCrafterBarManager;
import jp.makizakao.hardustryex.world.stat.ExplodableCrafterStatsManager;
import jp.makizakao.hardustryex.world.stat.IStatsManager;
import jp.makizakao.hardustryex.world.temperature.ExplodableTemperatureManager;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawBlock;
import multicraft.Recipe;

import java.util.Objects;

public class ExplodableCrafter extends HardMultiCrafter {
    protected int explosionDamage;
    protected int explosionRadius;
    protected Effect explodeEffect = Fx.explosion;
    protected Sound explodeSound = Sounds.none;
    protected float explodeTemperature;

    protected ExplodableCrafter(String name) {
        super(name);
    }

    protected ExplodableCrafter(Builder builder) {
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
        if(Objects.nonNull(builder.drawer)) this.drawer = builder.drawer;
        this.explodeTemperature = builder.explodeTemperature;
        this.explosionDamage = builder.damage;
        this.explosionRadius = builder.radius;
        if(Objects.nonNull(builder.effect)) this.explodeEffect = builder.effect;
    }

    public float getExplodeTemperature() {
        return explodeTemperature;
    }

    public class ExplodableCrafterBuild extends HardMultiCrafterBuild {

        @Override
        public void updateTile() {
            super.updateTile();
            if(temperatureManager instanceof ExplodableTemperatureManager explodableTemp
                    && explodableTemp.shouldExplode()) {
                createExplosion();
                onDestroyed();
            }
        }

        public void createExplosion() {
            if (ExplodableCrafter.this.explosionDamage > 0) {
                Damage.damage(this.x, this.y, (float)(ExplodableCrafter.this.explosionRadius * 8), (float) ExplodableCrafter.this.explosionDamage);
            }
            ExplodableCrafter.this.explodeEffect.at(this);
            ExplodableCrafter.this.explodeSound.at(this);
        }

        {
            temperatureManager = new ExplodableTemperatureManager(minEfficiency, explodeTemperature);
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

        private Builder(RequiredBuilder builder) {
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

        public static final class RequiredBuilder implements
                IRequirementsBuilder<IExplodeTemperatureBuilder<Builder>>,
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


        public ExplodableCrafter build()
        {
            return new ExplodableCrafter(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IExplodeTemperatureBuilder<T> {
            T explodeTemperature(float explodeTemperature, int damage, int radius);
        }
    }

    {
        barManager = new ExplodableCrafterBarManager();
        statsManager = new ExplodableCrafterStatsManager();
    }
}
