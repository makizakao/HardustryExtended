package jp.makizakao.world.temperature;

import arc.math.Mathf;
import multicraft.Recipe;

public class ExplodableTemperatureManager extends BasicTemperatureManager {
    private final float explodeTemperature;

    public ExplodableTemperatureManager(float minEfficiency, float explodeTemperature) {
        super(minEfficiency);
        this.explodeTemperature = explodeTemperature;
    }

    @Override
    public float calcTemperatureFrac(Recipe cur) {
        return Mathf.lerp(0, 1, getTemperature() / explodeTemperature);
    }

    public boolean shouldExplode() {
        return explodeTemperature < getTemperature();
    }

    public float getExplodeTemperature() {
        return explodeTemperature;
    }
}
