package jp.makizakao.world.blocks.power;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.type.Category;
import mindustry.type.ItemStack;

import java.util.Objects;

public class WindGenerator extends RotateGenerator {
    private float minEfficiency = 1f;
    private float maxEfficiency = 1f;
    private float minPowerDuration = Time.toMinutes;
    private float maxPowerDuration = Time.toMinutes * 5;

    // コンストラクタは非推奨
    private WindGenerator(String name) {
        super(name);
    }

    // Builder用のコンストラクタ
    private WindGenerator(Builder builder) {
        super(builder.name);
        requirements(Category.power, builder.requirements);
        health = builder.health;
        size = builder.size;
        powerProduction = builder.powerProduction;
        minEfficiency = builder.minEfficiency;
        maxEfficiency = builder.maxEfficiency;
        minPowerDuration = builder.minPowerDuration;
        maxPowerDuration = builder.maxPowerDuration;
        rotateSpeed = builder.rotateSpeed;
    }

    // Builderを作成
    public static Builder create(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    @Override
    public void load() {
        super.load();
    }

    public class WindGeneratorBuild extends RotateGeneratorBuild {
        private float powerDuration = -1f;

        @Override
        public void updateTile() {
            if(powerDuration < 0) {
                powerDuration = Mathf.lerp(minPowerDuration, maxPowerDuration, Mathf.random());
                productionEfficiency = Mathf.lerp(minEfficiency, maxEfficiency, Mathf.random());
            }
            powerDuration -= delta();
            progress = (progress + rotateSpeed * productionEfficiency * delta()) % 360;
        }



        @Override
        public void draw() {
            super.draw();
            Draw.rect(rotatorRegion, x, y, progress);
        }
    }

    public static class Builder {
        private final String name;
        private final int health;
        private final int size;
        private float powerProduction = 1f;
        private float minEfficiency = 1f;
        private float maxEfficiency = 1f;
        private float minPowerDuration = Time.toMinutes;
        private float maxPowerDuration = Time.toMinutes * 5;
        private float rotateSpeed = 1f;
        private ItemStack[] requirements;

        private Builder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public Builder powerProduction(float powerProduction) {
            this.powerProduction = powerProduction;
            return this;
        }

        public Builder efficiency(float minEfficiency, float maxEfficiency) {
            this.minEfficiency = minEfficiency;
            this.maxEfficiency = maxEfficiency;
            return this;
        }

        public Builder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public Builder powerDuration(float minPowerDuration, float maxPowerDuration) {
            this.minPowerDuration = minPowerDuration;
            this.maxPowerDuration = maxPowerDuration;
            return this;
        }

        public Builder rotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
            return this;
        }

        public WindGenerator build() {
            if(Objects.isNull(name)) throw new IllegalStateException("Name must be set");
            if(Objects.isNull(requirements)) throw new IllegalStateException("Requirements must be set");
            return new WindGenerator(this);
        }
    }
}
