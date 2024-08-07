package jp.makizakao.world.block.production;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import jp.makizakao.world.builder.BaseBlockBuilder.*;

import static jp.makizakao.HardustryEx.regionGetter;

public class RotateAnimatedCrafter extends HardMultiCrafter {
    private float rotateSpeed = 1f;
    private float rotateAngle = 0f;
    private float startAngle = 0f;
    private TextureRegion rotatorRegion;

    protected RotateAnimatedCrafter(String name) {
        super(name);
    }

    protected RotateAnimatedCrafter(Builder builder) {
        super(builder);
        this.rotateSpeed = builder.rotateSpeed;
        this.rotateAngle = builder.rotateAngle;
        this.startAngle = builder.startAngle;
    }

    public static IRotateSpeedBuilder<IRotateAngleBuilder<IRequirementsBuilder<IResolveRecipesBuilder<
            HardMultiCrafter.Builder>>>> createRotate(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public static IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>> create(String name, int health, int size)
    {
        throw new RuntimeException("Use createRotate method instead of create.");
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

    public static class Builder extends HardMultiCrafter.Builder implements IRotateSpeedBuilder<
            IRotateAngleBuilder<IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>>>>,
            IRotateAngleBuilder<IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>>> {
        private float rotateSpeed = 1f;
        private float rotateAngle = 0f;
        private float startAngle = 0f;

        protected Builder(String name, int health, int size) {
            super(name, health, size);
        }

        public IRotateAngleBuilder<IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>>>
                rotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
            return this;
        }

        public IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>> rotateAngle(float startAngle, float rotateAngle) {
            this.startAngle = startAngle;
            this.rotateAngle = rotateAngle;
            return this;
        }

        @Override
        public RotateAnimatedCrafter build() {
            return new RotateAnimatedCrafter(this);
        }
    }
}
