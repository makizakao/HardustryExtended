package jp.makizakao.world.blocks.production;

import arc.audio.Sound;
import arc.struct.Seq;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawMulti;
import multicraft.MultiCrafter;
import multicraft.Recipe;

public class HardMultiCrafter extends MultiCrafter {
    private HardMultiCrafter(String name) {
        super(name);
    }

    private HardMultiCrafter(Builder builder) {
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
            if (cur.isConsumeHeat()) {
                this.heat = this.calculateHeat(this.sideHeat);
                if(heat < cur.maxHeat() / 2) craftingTime = 0;
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
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private Seq<Recipe> recipes;
        private DrawMulti drawer;
        private Sound ambientSound = Sounds.none;
        private float ambientSoundVolume = 0f;
        private int itemCapacity = 10;

        private Builder(String name, int health, int size) {
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
            if(name == null) throw new IllegalArgumentException("Name must be set");
            if(requirements == null) throw new IllegalArgumentException("Requirements must be set");
            if(recipes == null) throw new IllegalArgumentException("Recipes must be set");
            return new HardMultiCrafter(this);
        }
    }
}
