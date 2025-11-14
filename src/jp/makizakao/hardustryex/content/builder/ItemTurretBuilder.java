package jp.makizakao.hardustryex.content.builder;

import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.draw.DrawBlock;

import java.util.Objects;

public class ItemTurretBuilder {

    public static IRequirementsBuilder<IAmmoBuilder<IShootBuilder<OptionalBuilder>>> create(
            String name, int health, int size) {
        return new RequiredBuilder(name, health, size);
    }

    public static class RequiredBuilder implements
            IRequirementsBuilder<IAmmoBuilder<IShootBuilder<OptionalBuilder>>>,
            IAmmoBuilder<IShootBuilder<OptionalBuilder>>,
            IShootBuilder<OptionalBuilder> {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;
        private Object[] ammo;
        private ShootPattern shoot;
        private float shootStartPosY;

        private RequiredBuilder() {}

        private RequiredBuilder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        @Override
        public IAmmoBuilder<IShootBuilder<OptionalBuilder>> requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        @Override
        public IShootBuilder<OptionalBuilder> ammo(Object... ammo) {
            this.ammo = ammo;
            return this;
        }

        @Override
        public OptionalBuilder shoot(ShootPattern shoot, float shootY) {
            this.shoot = shoot;
            this.shootStartPosY = shootY;
            return new OptionalBuilder(this);
        }
    }

    public static class OptionalBuilder {
        private RequiredBuilder requiredBuilder;
        private int recoils = -1;
        private float recoil = 1.0f;
        private float reload = 10f;
        private float range = 80f;
        private float shootCone = 8f;
        private Effect ammoUseEffect = Fx.none;
        private float inaccuracy = 0f;
        private float rotateSpeed = 1f;
        private float consumeCoolant;
        private boolean isLimitRange = false;
        private DrawBlock drawer;
        private ItemTurret turret;
        private float powerConsume;

        private OptionalBuilder() {}

        private OptionalBuilder(RequiredBuilder requiredBuilder) {
            this.requiredBuilder = requiredBuilder;
        }

        public OptionalBuilder ammoUseEffect(Effect effect) {
            this.ammoUseEffect = effect;
            return this;
        }

        public OptionalBuilder recoil(int recoils, float recoil) {
            this.recoils = recoils;
            this.recoil = recoil;
            return this;
        }

        public OptionalBuilder reload(float reload) {
            this.reload = reload;
            return this;
        }

        public OptionalBuilder range(float range) {
            this.range = range;
            return this;
        }

        public OptionalBuilder shootCone(float shootCone) {
            this.shootCone = shootCone;
            return this;
        }

        public OptionalBuilder inaccuracy(float inaccuracy) {
            this.inaccuracy = inaccuracy;
            return this;
        }

        public OptionalBuilder rotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
            return this;
        }

        public OptionalBuilder consumeCoolant(float consumeCoolant) {
            this.consumeCoolant = consumeCoolant;
            return this;
        }

        public OptionalBuilder limitRange() {
            this.isLimitRange = true;
            return this;
        }

        public OptionalBuilder powerConsume(float powerConsume) {
            this.powerConsume = powerConsume;
            return this;
        }

        public OptionalBuilder drawer(DrawBlock drawer) {
            this.drawer = drawer;
            return this;
        }

        public ItemTurret build() {
            return new ItemTurret(requiredBuilder.name) {{
                    requirements(Category.turret, requiredBuilder.requirements);
                    health = requiredBuilder.health;
                    size = requiredBuilder.size;
                    /*
                    ammo(requiredBuilder.ammo);
                    shoot = requiredBuilder.shoot;
                    shootY = requiredBuilder.shootStartPosY;
                    recoils = OptionalBuilder.this.recoils;
                    recoil = OptionalBuilder.this.recoil;
                    reload = OptionalBuilder.this.reload;
                    range = OptionalBuilder.this.range;
                    shootCone = OptionalBuilder.this.shootCone;
                    ammoUseEffect = OptionalBuilder.this.ammoUseEffect;
                    inaccuracy = OptionalBuilder.this.inaccuracy;
                     */
                    rotateSpeed = OptionalBuilder.this.rotateSpeed;
                    coolant = consumeCoolant(OptionalBuilder.this.consumeCoolant);
                    if(powerConsume > 0) consumePower(powerConsume);
                    if(OptionalBuilder.this.isLimitRange) limitRange();
                    if(Objects.nonNull(OptionalBuilder.this.drawer)) drawer = OptionalBuilder.this.drawer;
            }};
        }
    }

    public interface IRequirementsBuilder<T> {
        T requirements(Object... stacks);
    }

    public interface IAmmoBuilder<T> {
        T ammo(Object... ammo);
    }

    public interface IShootBuilder<T> {
        T shoot(ShootPattern shoot, float shootY);
    }
}
