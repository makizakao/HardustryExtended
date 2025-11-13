package jp.makizakao.hardustryex.world.temperature;

import jp.makizakao.hardustryex.type.entry.SmeltEntry;
import mindustry.world.modules.LiquidModule;
import multicraft.Recipe;

import java.util.Objects;

public class SteamBoilerTemperatureManager extends ExplodableTemperatureManager {
    private final LiquidModule liquids;

    public SteamBoilerTemperatureManager(float minEfficiency, float explodeTemperature, LiquidModule liquids) {
        super(minEfficiency, explodeTemperature);
        this.liquids = liquids;
    }

    @Override
    public float calcTemperature(Recipe recipe, float heat, float delta) {
        if(!(recipe.input instanceof SmeltEntry smeltEntry)) return super.calcTemperature(recipe, heat, delta);
        if(Objects.isNull(recipe.input.fluids.get(0))) return super.calcTemperature(recipe, heat, delta);

        float liquidAmount = liquids.get(recipe.input.fluids.get(0).liquid);

        if(liquidAmount < smeltEntry.fluids.get(0).amount) return super.calcTemperature(recipe, heat, delta);

        return Math.min(super.calcTemperature(recipe, heat, delta), smeltEntry.temperature);
    }

    @Override
    public float calcEfficiency(Recipe cur) {
        if(!(cur.input instanceof SmeltEntry smeltEntry)) return 1f;

        return smeltEntry.temperature <= temperature() ? 1f : 0f;
    }
}
