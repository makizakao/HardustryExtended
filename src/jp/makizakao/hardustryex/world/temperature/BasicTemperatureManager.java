package jp.makizakao.hardustryex.world.temperature;

import arc.math.Mathf;
import arc.util.Log;
import arc.util.Time;
import jp.makizakao.hardustryex.type.entry.SmeltEntry;
import multicraft.Recipe;

public class BasicTemperatureManager implements ITemperatureManager {
    private static final float OUTSIDE_TEMPERATURE = 20f;
    private static final float TEMPERATURE_PER_HEAT = 50f;
    private static final float TEMPERATURE_INCREMENTS_MULTIPLIER = 0.5f;
    private static final float TEMPERATURE_DECREASE_MULTIPLIER = 4f;
    private final float minEfficiency;
    private float temperature;

    public BasicTemperatureManager(final float minEfficiency) {
        this.minEfficiency = minEfficiency;
        this.temperature = OUTSIDE_TEMPERATURE;
    }

    @Override
    public void updateTemperature(final Recipe cur, final float heat, final float delta) {
        this.temperature =  calcTemperature(cur, heat, delta);
    }

    protected float calcTemperature(final Recipe cur, final float heat, final float delta) {
        return Math.min(calcIncreaseTemperature(heat, delta),
                Math.max(calcDecreaseTemperature(delta), calcMaxTemperature(heat)));
    }

    protected float calcDecreaseTemperature(final float delta) {
        return temperature - delta * TEMPERATURE_DECREASE_MULTIPLIER / Time.toSeconds;
    }

    protected float calcIncreaseTemperature(final float heat, final float delta) {
        return temperature + heat * TEMPERATURE_INCREMENTS_MULTIPLIER * delta / Time.toSeconds;
    }

    protected float calcMaxTemperature(final float heat) {
        return heat * TEMPERATURE_PER_HEAT + OUTSIDE_TEMPERATURE;
    }

    @Override
    public float calcEfficiency(final Recipe recipe) {
        if (!(recipe.input instanceof SmeltEntry smeltEntry)) return 1f;
        if (temperature() < smeltEntry.temperature) return 0f;

        return Math.min((temperature() - smeltEntry.temperature) / smeltEntry.temperature + minEfficiency, 1f);
    }

    @Override
    public float calcTemperatureFrac(final Recipe recipe) {
        if (!(recipe.input instanceof SmeltEntry smeltEntry)) return 0f;

        return Mathf.clamp(temperature() / smeltEntry.temperature, 0, 1f);
    }


    @Override
    public final float temperature() {
        return temperature;
    }

    @Override
    public final void setTemperature(final float temperature) {
        this.temperature = temperature;
    }
}
