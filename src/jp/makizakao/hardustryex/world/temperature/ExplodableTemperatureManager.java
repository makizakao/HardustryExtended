package jp.makizakao.hardustryex.world.temperature;

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
        return Mathf.lerp(0, 1, temperature() / explodeTemperature);
    }

    public boolean shouldExplode() {
        return explodeTemperature < temperature();
    }

    public float explodeTemperature() {
        return explodeTemperature;
    }
}
