package jp.makizakao.world;

import arc.audio.Sound;
import arc.struct.Seq;
import jp.makizakao.type.SmeltStack;
import mindustry.type.UnitType;
import mindustry.world.draw.DrawMulti;
import multicraft.Recipe;

public final class BaseBuilder {
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
        T drawer(DrawMulti drawer);
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
}
