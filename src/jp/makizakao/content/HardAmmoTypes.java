package jp.makizakao.content;

import jp.makizakao.world.builder.BaseBulletTypeBuilder.*;
import mindustry.entities.bullet.BasicBulletType;

public class HardAmmoTypes {
    public static final Object[] DUO_TIER_1 = new Object[] {
            HardItems.copperIngot, BasicBulletTypeBuilder.create(1.5f, 6f)
                    .dimensions(7f, 9f)
                    .lifetime(100f)
                    .ammoMultiplier(2f)
                    .build(),
            HardItems.tinIngot, BasicBulletTypeBuilder.create(2.0f, 9f)
                    .dimensions(8f, 10f)
                    .lifetime(100f)
                    .ammoMultiplier(2f)
                    .build()
    };

    private static class BasicBulletTypeBuilder implements IDimensionsBuilder<ILifetimeBuilder<
            IAmmoMultiplierBuilder<BasicBulletTypeBuilder>>>,
            ILifetimeBuilder<IAmmoMultiplierBuilder<BasicBulletTypeBuilder>>,
            IAmmoMultiplierBuilder<BasicBulletTypeBuilder>, IReloadMultiplierBuilder<BasicBulletTypeBuilder> {
        private final float speed;
        private final float damage;
        private float height;
        private float width;
        private float lifetime;
        private float ammoMultiplier = 1f;
        private float reloadMultiplier = 1f;

        private BasicBulletTypeBuilder(float speed, float damage) {
            this.speed = speed;
            this.damage = damage;
        }

        public static IDimensionsBuilder<ILifetimeBuilder<
                IAmmoMultiplierBuilder<BasicBulletTypeBuilder>>> create(float speed, float damage) {
            return new BasicBulletTypeBuilder(speed, damage);
        }

        @Override
        public ILifetimeBuilder<IAmmoMultiplierBuilder<BasicBulletTypeBuilder>> dimensions(float height, float width) {
            this.height = height;
            this.width = width;
            return this;
        }

        @Override
        public IAmmoMultiplierBuilder<BasicBulletTypeBuilder> lifetime(float lifetime) {
            this.lifetime = lifetime;
            return this;
        }

        @Override
        public BasicBulletTypeBuilder ammoMultiplier(float multiplier) {
            this.ammoMultiplier = multiplier;
            return this;
        }

        @Override
        public BasicBulletTypeBuilder reloadMultiplier(float multiplier) {
            this.reloadMultiplier = multiplier;
            return this;
        }

        public BasicBulletType build() {
            return new BasicBulletType(speed, damage) {{
                height = BasicBulletTypeBuilder.this.height;
                width = BasicBulletTypeBuilder.this.width;
                lifetime = BasicBulletTypeBuilder.this.lifetime;
                ammoMultiplier = BasicBulletTypeBuilder.this.ammoMultiplier;
                reloadMultiplier = BasicBulletTypeBuilder.this.reloadMultiplier;
            }};
        }
    }
}
