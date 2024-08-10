package jp.makizakao.world.temperature;

import multicraft.Recipe;

public interface ITemperatureManager {
    float calcTemperature(Recipe cur, float heat, float delta);
    float calcTemperatureEfficiency(Recipe cur);
    float calcTemperatureFrac(Recipe cur);
    float getTemperature();
    void setTemperature(float temperature);
}
