package jp.makizakao.world.builder;

import arc.audio.Sound;
import arc.struct.Seq;
import jp.makizakao.type.SmeltStack;
import mindustry.entities.Effect;
import mindustry.entities.pattern.ShootPattern;
import mindustry.type.UnitType;
import mindustry.world.draw.DrawBlock;
import multicraft.Recipe;

public final class BaseBlockBuilder {
    public interface IRequirementsBuilder<T> {
        T requirements(Object... stacks);
    }

    public interface IUnitTypeBuilder<T> {
        T unitType(UnitType unitType);
    }

    public interface IUnitCapModifierBuilder<T> {
        T unitCapModifier(int unitCapModifier);
    }

    public interface ISmeltListBuilder<T> {
        T smeltList(Seq<SmeltStack> smeltList);
    }

    public interface IPowerConsumeBuilder<T> {
        T powerConsume(float powerConsume);
    }

    public interface IBuildCostMultiplierBuilder<T> {
        T buildCostMultiplier(float buildCostMultiplier);
    }

    public interface ICapacityBuilder<T> {
        T capacity(float capacity);
    }

    public interface IPowerProductionBuilder<T> {
        T powerProduction(float powerProduction);
    }

    public interface IProductionEfficiencyLimitsBuilder<T> {
        T efficiencyLimits(float minEfficiency, float maxEfficiency);
    }

    public interface IPowerDurationBuilder<T> {
        T powerDuration(float minPowerDuration, float maxPowerDuration);
    }

    public interface IRotateSpeedBuilder<T> {
        T rotateSpeed(float rotateSpeed);
    }

    public interface ILaserRangeBuilder<T> {
        IMaxNodesBuilder<T> laserRange(float laserRange);
    }

    public interface IMaxNodesBuilder<T> {
        T maxNodes(int maxNodes);
    }

    public interface IResolveRecipesBuilder<T> {
        T resolveRecipes(Seq<Recipe> recipes);
    }

    public interface IItemCapacityBuilder<T> {
        T itemCapacity(int itemCapacity);
    }

    public interface IDrawerBuilder<T> {
        T drawer(DrawBlock drawer);
    }

    public interface IAmbientSoundBuilder<T> {
        T ambientSound(Sound sound, float volume);
    }

    public interface IRotateAngleBuilder<T> {
        T rotateAngle(float startAngle, float rotateAngle);
    }

    public interface IDrillTimeBuilder<T> {
        T drillTime(int drillTime);
    }

    public interface IAmmoBuilder<T> {
        T ammo(Object... ammo);
    }

    public interface IShootBuilder<T> {
        T shoot(ShootPattern shoot);
    }

    public interface IAmmoUseEffectBuilder<T> {
        T ammoUseEffect(Effect effect);
    }

    public interface IRecoilBuilder<T> {
        T recoil(int recoils, float recoil);
    }

    public interface IShootYBuilder<T> {
        T shootY(float shootY);
    }

    public interface IReloadBuilder<T> {
        T reload(float reload);
    }

    public interface IRangeBuilder<T> {
        T range(float range);
    }

    public interface IShootConeBuilder<T> {
        T shootCone(float shootCone);
    }

    public interface IInaccuracyBuilder<T> {
        T inaccuracy(float inaccuracy);
    }

    public interface IConsumeCoolantBuilder<T> {
        T consumeCoolant(float consumeCoolant);
    }

    public interface ILimitRangeBuilder<T> {
        T limitRange();
    }

    public interface IExplodeHeatBuilder<T> {
        T explodeHeat(float explodeHeat, int damage, int radius);
    }
}
