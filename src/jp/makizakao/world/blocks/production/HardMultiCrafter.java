package jp.makizakao.world.blocks.production;

import arc.audio.Sound;
import arc.math.Mathf;
import arc.struct.Seq;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawMulti;
import multicraft.MultiCrafter;
import multicraft.Recipe;

import java.util.Objects;

public class HardMultiCrafter extends MultiCrafter {
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
        if(ambientSound != Sounds.none) {
            this.ambientSound = builder.ambientSound;
            this.ambientSoundVolume = builder.ambientSoundVolume;
        }
        if(builder.drawer != null) this.drawer = builder.drawer;
    }

    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public class HardMultiCrafterBuild extends MultiCrafterBuild {
        @Override
        public void updateTile() {
            Recipe cur = this.getCurRecipe();
            if(cur.isConsumeHeat()) {
                this.heat = this.calculateHeat(this.sideHeat);
                if(heat < cur.maxHeat() / 2) craftingTime = 0;
            }
            if(cur.isOutputHeat()) {
                heat = Mathf.approachDelta(heat, cur.output.heat * efficiency
                        + (isConsumeHeat ? this.calculateHeat(this.sideHeat) : 0), warmupRate * delta());
            }
            super.updateTile();
        }

        @Override
        public float getProgressIncrease(float baseTime) {
            Recipe cur = this.getCurRecipe();
            if (cur.isConsumeHeat()) {
                return super.getProgressIncrease(baseTime) * Math.min(1, this.heat / cur.maxHeat());
            }
            return super.getProgressIncrease(baseTime);
        }
    }

    public static class Builder {
        private final String name;
        private final int size;
        private final int health;
        private ItemStack[] requirements;
        private Seq<Recipe> recipes;
        private DrawMulti drawer;
        private Sound ambientSound = Sounds.none;
        private float ambientSoundVolume = 0f;
        private int itemCapacity = 10;

        protected Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public Builder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Builder resolveRecipes(Seq<Recipe> recipes) {
            this.recipes = recipes;
            return this;
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

        public Builder drawer(DrawMulti drawer) {
            this.drawer = drawer;
            return this;
        }

        public HardMultiCrafter build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            if(Objects.isNull(requirements)) throw new IllegalArgumentException("Requirements must be set");
            if(Objects.isNull(recipes)) throw new IllegalArgumentException("Recipes must be set");
            return new HardMultiCrafter(this);
        }
    }
}
