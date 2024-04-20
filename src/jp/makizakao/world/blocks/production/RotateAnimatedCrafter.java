package jp.makizakao.world.blocks.production;

import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import mindustry.gen.Sounds;
import mindustry.type.Category;

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

    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
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

    public static class Builder extends HardMultiCrafter.Builder {
        private float rotateSpeed = 1f;
        private float rotateAngle = 0f;
        private float startAngle = 0f;

        protected Builder(String name, int health, int size) {
            super(name, health, size);
        }

        public Builder rotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
            return this;
        }

        public Builder rotateAngle(float startAngle, float rotateAngle) {
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
