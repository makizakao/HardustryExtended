package jp.makizakao.content;

import arc.util.Log;
import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.entities.Effect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.turrets.ItemTurret;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.draw.DrawBlock;

import java.util.Objects;

public class DefaultBlockBuilders {
    public static class OreBlockBuilder {
        private final String name;
        private final Item dropItem;
        private boolean oreDefault = false;
        private float oreThreshold = 0f;
        private float oreScale = 0f;

        private OreBlockBuilder(String name, Item dropItem) {
            this.name = name;
            this.dropItem = dropItem;
        }

        public static OreBlockBuilder create(String name, Item dropItem) {
            return new OreBlockBuilder(name, dropItem);
        }

        public OreBlockBuilder oreDefault() {
            this.oreDefault = true;
            return this;
        }

        public OreBlockBuilder oreThreshold(float oreThreshold) {
            this.oreThreshold = oreThreshold;
            return this;
        }

        public OreBlockBuilder oreScale(float oreScale) {
            this.oreScale = oreScale;
            return this;
        }

        public OreBlock build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            return new OreBlock(name, dropItem) {{
                oreDefault = OreBlockBuilder.this.oreDefault;
                oreThreshold = OreBlockBuilder.this.oreThreshold;
                oreScale = OreBlockBuilder.this.oreScale;
            }};
        }
    }

    public static class ItemTurretBuilder implements IRequirementsBuilder<IAmmoBuilder<IShootBuilder<
            IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                    IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>>>>>,
            IAmmoBuilder<IShootBuilder<IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<
                    IShootConeBuilder<
                            IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>>>>,
            IShootBuilder<IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<
                    IShootConeBuilder<
                            IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>>>,
            IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                    IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>>,
            IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                    IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>,
            IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                    IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>,
            IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                    IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>,
            IRangeBuilder<IShootConeBuilder<
                    IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>,
            IShootConeBuilder<IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>,
            IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>,
            IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>,
            IConsumeCoolantBuilder<ItemTurretBuilder>, ILimitRangeBuilder<ItemTurretBuilder> {
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private int recoils;
        private float recoil;
        private float shootY;
        private float reload;
        private float range;
        private float shootCone;
        private Effect ammoUseEffect;
        private ShootPattern shoot;
        private Object[] ammo;
        private float inaccuracy;
        private float rotateSpeed;
        private float consumeCoolant;
        private boolean isLimitRange = false;
        private DrawBlock drawer;

        private ItemTurretBuilder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public static IRequirementsBuilder<IAmmoBuilder<IShootBuilder<IAmmoUseEffectBuilder<IRecoilBuilder<
                IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<IInaccuracyBuilder<
                        IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>>>>> create(String name, int health, int size) {
            return new ItemTurretBuilder(name, health, size);
        }

        @Override
        public IAmmoBuilder<IShootBuilder<IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<
                IRangeBuilder<IShootConeBuilder<IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<
                        ItemTurretBuilder>>>>>>>>>>> requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        @Override
        public IShootBuilder<IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<
                IShootConeBuilder<IInaccuracyBuilder<IRotateSpeedBuilder<
                        IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>>> ammo(Object... ammo) {
            this.ammo = ammo;
            return this;
        }

        @Override
        public IAmmoUseEffectBuilder<IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>>> shoot(ShootPattern shoot) {
            this.shoot = shoot;
            return this;
        }

        @Override
        public IRecoilBuilder<IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<IInaccuracyBuilder<
                IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>>> ammoUseEffect(Effect effect) {
            this.ammoUseEffect = effect;
            return this;
        }

        @Override
        public IShootYBuilder<IReloadBuilder<IRangeBuilder<IShootConeBuilder<IInaccuracyBuilder<
                IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>>> recoil(int recoils, float recoil) {
            this.recoils = recoils;
            this.recoil = recoil;
            return this;
        }

        @Override
        public IReloadBuilder<IRangeBuilder<IShootConeBuilder<
                IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>>> shootY(float shootY) {
            this.shootY = shootY;
            return this;
        }

        @Override
        public IRangeBuilder<IShootConeBuilder<
                IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>>> reload(float reload) {
            this.reload = reload;
            return this;
        }

        @Override
        public IShootConeBuilder<IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>>> range(float range) {
            this.range = range;
            return this;
        }

        @Override
        public IInaccuracyBuilder<IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>>> shootCone(float shootCone) {
            this.shootCone = shootCone;
            return this;
        }

        @Override
        public IRotateSpeedBuilder<IConsumeCoolantBuilder<ItemTurretBuilder>> inaccuracy(float inaccuracy) {
            this.inaccuracy = inaccuracy;
            return this;
        }

        @Override
        public IConsumeCoolantBuilder<ItemTurretBuilder> rotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
            return this;
        }

        @Override
        public ItemTurretBuilder consumeCoolant(float consumeCoolant) {
            this.consumeCoolant = consumeCoolant;
            return this;
        }

        @Override
        public ItemTurretBuilder limitRange() {
            this.isLimitRange = true;
            return this;
        }

        public ItemTurretBuilder drawer(DrawBlock drawer) {
            this.drawer = drawer;
            return this;
        }

        public ItemTurret build() {
            return new ItemTurret(name) {{
                requirements(Category.turret, requirements);
                health = ItemTurretBuilder.this.health;
                size = ItemTurretBuilder.this.size;
                ammo(ItemTurretBuilder.this.ammo);
                recoils = ItemTurretBuilder.this.recoils;
                recoil = ItemTurretBuilder.this.recoil;
                shootY = ItemTurretBuilder.this.shootY;
                reload = ItemTurretBuilder.this.reload;
                range = ItemTurretBuilder.this.range;
                shootCone = ItemTurretBuilder.this.shootCone;
                ammoUseEffect = ItemTurretBuilder.this.ammoUseEffect;
                shoot = ItemTurretBuilder.this.shoot;
                inaccuracy = ItemTurretBuilder.this.inaccuracy;
                rotateSpeed = ItemTurretBuilder.this.rotateSpeed;
                coolant = consumeCoolant(ItemTurretBuilder.this.consumeCoolant);
                if(ItemTurretBuilder.this.isLimitRange) limitRange();
                if(Objects.nonNull(ItemTurretBuilder.this.drawer)) drawer = ItemTurretBuilder.this.drawer;
            }};
        }
    }
}
