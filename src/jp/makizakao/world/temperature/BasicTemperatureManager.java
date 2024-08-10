package jp.makizakao.world.temperature;

import arc.math.Mathf;
import arc.util.Time;
import jp.makizakao.type.entry.SmeltEntry;
import multicraft.Recipe;

import java.util.Optional;

public class BasicTemperatureManager implements ITemperatureManager {
    private static final float OUTSIDE_TEMPERATURE = 20f;
    private static final float TEMPERATURE_PER_HEAT = 50f;
    private static final float TEMPERATURE_INCREMENTS_MULTIPLIER = 0.5f;
    private final float minEfficiency;
    private float temperature = OUTSIDE_TEMPERATURE;

    private BasicTemperatureManager() {
        this.minEfficiency = 1f;
    }

    public BasicTemperatureManager(float minEfficiency) {
        this.minEfficiency = minEfficiency;
    }

    @Override
    public float calcTemperature(Recipe cur, float heat, float delta) {
        return Math.min(temperature + heat * TEMPERATURE_INCREMENTS_MULTIPLIER * delta / Time.toSeconds,
                Math.max(temperature - delta / Time.toSeconds, heat * TEMPERATURE_PER_HEAT + OUTSIDE_TEMPERATURE));
    }

    @Override
    public float calcTemperatureEfficiency(Recipe cur) {
        if (cur.input instanceof SmeltEntry smeltEntry) {
            return temperature < smeltEntry.temperature ? 0
                    : Math.min((temperature - smeltEntry.temperature) / smeltEntry.temperature + minEfficiency, 1f);
        }
        return 1f;
    }

    @Override
    public float calcTemperatureFrac(Recipe cur) {
        return Optional.of(cur).filter(r -> r.input instanceof SmeltEntry)
                .map(r -> (SmeltEntry)r.input)
                .map(e -> e.temperature)
                .map(t -> Mathf.clamp(getTemperature() / t, 0, 1f))
                .orElse(0f);
    }


    @Override
    public float getTemperature() {
        return temperature;
    }

    @Override
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
