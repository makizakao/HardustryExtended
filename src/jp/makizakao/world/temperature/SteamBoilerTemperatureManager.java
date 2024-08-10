package jp.makizakao.world.temperature;

import jp.makizakao.type.entry.SmeltEntry;
import multicraft.Recipe;

public class SteamBoilerTemperatureManager extends ExplodableTemperatureManager {
    private float liquidAmount = 0;

    public SteamBoilerTemperatureManager(float minEfficiency, float explodeTemperature) {
        super(minEfficiency, explodeTemperature);
    }

    @Override
    public float calcTemperature(Recipe cur, float heat, float delta) {
        var smeltEntry = (SmeltEntry) cur.input;
        if(liquidAmount <= smeltEntry.fluids.get(0).amount) {
            return super.calcTemperature(cur, heat, delta);
        }
        return Math.min(super.calcTemperature(cur, heat, delta), smeltEntry.temperature);
    }

    @Override
    public float calcTemperatureEfficiency(Recipe cur) {
        return ((SmeltEntry) cur.input).temperature <= getTemperature() ? 1f : 0f;
    }

    public void setLiquidAmount(float liquidAmount) {
        this.liquidAmount = liquidAmount;
    }
}
