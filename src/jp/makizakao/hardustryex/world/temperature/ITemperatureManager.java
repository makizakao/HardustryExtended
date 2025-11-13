package jp.makizakao.hardustryex.world.temperature;

import multicraft.Recipe;

public interface ITemperatureManager {
    void updateTemperature(Recipe cur, float heat, float delta);
    float calcEfficiency(Recipe cur);
    float calcTemperatureFrac(Recipe cur);
    float temperature();
    void setTemperature(float temperature);
}
