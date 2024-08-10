package jp.makizakao.builder;

public class BaseBulletTypeBuilder {
    public interface IDimensionsBuilder<T> {
        T dimensions(float height, float width);
    }

    public interface ILifetimeBuilder<T> {
        T lifetime(float lifetime);
    }

    public interface IAmmoMultiplierBuilder<T> {
        T ammoMultiplier(float multiplier);
    }

    public interface IReloadMultiplierBuilder<T> {
        T reloadMultiplier(float multiplier);
    }
}
