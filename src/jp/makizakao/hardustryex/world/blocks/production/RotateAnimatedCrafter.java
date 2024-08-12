package jp.makizakao.hardustryex.world.blocks.production;

import arc.audio.Sound;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.draw.DrawBlock;
import multicraft.Recipe;

import java.util.Objects;

import static jp.makizakao.hardustryex.HardustryEx.regionGetter;

public class RotateAnimatedCrafter extends HardMultiCrafter {
    private float rotateSpeed = 1f;
    private float rotateAngle = 0f;
    private float startAngle = 0f;
    private TextureRegion rotatorRegion;

    protected RotateAnimatedCrafter(String name) {
        super(name);
    }

    protected RotateAnimatedCrafter(Builder builder) {
        this(builder.name);
        this.health = builder.health;
        this.size = builder.size;
        this.resolvedRecipes = builder.recipes;
        requirements(Category.crafting, builder.requirements);
        if(ambientSound != Sounds.none) {
            this.ambientSound = builder.ambientSound;
            this.ambientSoundVolume = builder.ambientVolume;
        }
        if(Objects.nonNull(builder.drawer)) this.drawer = builder.drawer;
        this.itemCapacity = builder.itemCapacity;
        this.liquidCapacity = builder.liquidCapacity;
        this.rotateSpeed = builder.rotateSpeed;
        this.rotateAngle = builder.rotateAngle;
        this.startAngle = builder.startAngle;
    }

    @Override
    public void load() {
        super.load();
        rotatorRegion = regionGetter.apply(this.name, "rotator");
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{region, rotatorRegion};
    }

    public class RotateAnimatedCrafterBuild extends HardMultiCrafterBuild {
        protected float angle = 0f;

        @Override
        public void updateTile() {
            super.updateTile();
            angle = progress() * rotateSpeed * 360f;
        }

        @Override
        public void draw() {
            super.draw();
            Draw.rect(rotatorRegion, x, y, angle % rotateAngle + startAngle);
        }
    }

    public static class Builder {
        private String name;
        private int health;
        private int size;
        private float rotateSpeed = 1f;
        private float rotateAngle = 0f;
        private float startAngle = 0f;
        private Seq<Recipe> recipes;
        private ItemStack[] requirements;
        private Sound ambientSound = Sounds.none;
        private float ambientVolume;
        private DrawBlock drawer;
        private int itemCapacity;
        private float liquidCapacity;

        private Builder() {}

        protected Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.rotateSpeed = builder.rotateSpeed;
            this.rotateAngle = builder.rotateAngle;
            this.startAngle = builder.startAngle;
            this.recipes = builder.recipes;
            this.requirements = builder.requirements;
        }

        public static IRequirementsBuilder<IRotateBuilder<IResolveRecipesBuilder<Builder>>> create(
                String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        public static class RequiredBuilder implements
                IRequirementsBuilder<IRotateBuilder<IResolveRecipesBuilder<Builder>>>,
                IRotateBuilder<IResolveRecipesBuilder<Builder>>,
                IResolveRecipesBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private float rotateSpeed = 1f;
            private float rotateAngle = 0f;
            private float startAngle = 0f;
            private Seq<Recipe> recipes;

            private RequiredBuilder () {}

            public RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public IRotateBuilder<IResolveRecipesBuilder<Builder>> requirements(Object... stacks) {
                this.requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public IResolveRecipesBuilder<Builder> rotate(float startAngle, float rotateAngle, float rotateSpeed) {
                this.startAngle = startAngle;
                this.rotateAngle = rotateAngle;
                this.rotateSpeed = rotateSpeed;
                return this;
            }

            @Override
            public Builder resolveRecipes(Seq<Recipe> recipes) {
                this.recipes = recipes;
                return new Builder(this);
            }
        }


        public Builder ambientSound(Sound sound, float volume) {
            this.ambientSound = sound;
            this.ambientVolume = volume;
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


        public RotateAnimatedCrafter build() {
            return new RotateAnimatedCrafter(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IRotateBuilder<T> {
            T rotate(float startAngle, float rotateAngle, float rotateSpeed);
        }

        public interface IResolveRecipesBuilder<T> {
            T resolveRecipes(Seq<Recipe> recipes);
        }
    }
}
