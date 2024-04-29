package jp.makizakao.world.blocks.production;

import arc.audio.Sound;
import arc.math.Mathf;
import arc.struct.Seq;
import jp.makizakao.type.ResultRecipe;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawMulti;
import multicraft.MultiCrafter;
import multicraft.Recipe;

import java.util.Objects;

public class HardMultiCrafter<T extends Recipe> extends MultiCrafter {
    public Seq<T> resolvedRecipes;
    protected HardMultiCrafter(String name) {
        super(name);
    }

    protected HardMultiCrafter(Builder<T> builder) {
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
        public void craft() {
            if(getCurRecipe() instanceof ResultRecipe cur) {
                consume();
                if (cur.isOutputItem()) {
                    for (var output : cur.output.items) for (int i = 0; i < output.amount; i++) {
                        if(output.dropChance <= Mathf.random(1f)) offload(output.item);
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
    }

    public static class Builder<T extends Recipe> {
        private final String name;
        private final int size;
        private final int health;
        private ItemStack[] requirements;
        private Seq<T> recipes;
        private DrawMulti drawer;
        private Sound ambientSound = Sounds.none;
        private float ambientSoundVolume = 0f;
        private int itemCapacity = 10;

        protected Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public Builder<T> requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Builder<T> resolveRecipes(Seq<T> recipes) {
            this.recipes = recipes;
            return this;
        }

        public Builder<T> itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }

        public Builder<T> ambientSound(Sound sound, float volume) {
            this.ambientSound = sound;
            this.ambientSoundVolume = volume;
            return this;
        }

        public Builder<T> drawer(DrawMulti drawer) {
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
