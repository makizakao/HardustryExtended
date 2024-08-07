package jp.makizakao.world.block.production;

import arc.audio.Sound;
import arc.math.Mathf;
import arc.struct.Seq;
import jp.makizakao.type.ResultRecipe;
import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawBlock;
import multicraft.MultiCrafter;
import multicraft.Recipe;

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

    public static IRequirementsBuilder<IResolveRecipesBuilder<Builder>> create(
            String name, int health, int size) {
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
                var items = cur.output.items;
                if (cur.isOutputItem()) {
                    //Vars.ui.hudfrag.showToast(String.valueOf(cur.dropChances[0]));
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
    }

    public static class Builder implements IRequirementsBuilder<IResolveRecipesBuilder<Builder>>,
            IResolveRecipesBuilder<Builder>, IItemCapacityBuilder<Builder>,
            IDrawerBuilder<Builder>, IAmbientSoundBuilder<Builder> {
        private final String name;
        private final int size;
        private final int health;
        private ItemStack[] requirements;
        private Seq<Recipe> recipes;
        private DrawBlock drawer;
        private Sound ambientSound = Sounds.none;
        private float ambientSoundVolume = 0f;
        private int itemCapacity = 10;

        protected Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        @Override
        public IResolveRecipesBuilder<Builder> requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        @Override
        public Builder resolveRecipes(Seq<Recipe> recipes) {
            this.recipes = recipes;
            return this;
        }

        @Override
        public Builder itemCapacity(int itemCapacity) {
            this.itemCapacity = itemCapacity;
            return this;
        }

        @Override
        public Builder ambientSound(Sound sound, float volume) {
            this.ambientSound = sound;
            this.ambientSoundVolume = volume;
            return this;
        }

        @Override
        public Builder drawer(DrawBlock drawer) {
            this.drawer = drawer;
            return this;
        }

        public HardMultiCrafter build() {
            return new HardMultiCrafter(this);
        }
    }
}
