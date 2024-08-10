package jp.makizakao.world.bar;

import arc.Core;
import arc.graphics.Color;
import jp.makizakao.HardustryEx;
import jp.makizakao.type.entry.SmeltEntry;
import jp.makizakao.world.blocks.production.ExplodableCrafter.*;
import jp.makizakao.world.blocks.production.HardMultiCrafter;
import jp.makizakao.world.temperature.ExplodableTemperatureManager;
import mindustry.gen.Building;
import mindustry.ui.Bar;

public class ExplodableCrafterBarManager extends CrafterBarManager {
    @Override
    public void setBars(HardMultiCrafter block) {
        if (block.isConsumeHeat()) block.addBar("temperature", this::createTemperatureBar);
        if (block.isConsumeHeat() || block.isOutputHeat()) block.addBar("heat", this::createHeatBar);
    }

    @Override
    protected Bar createTemperatureBar(Building building) {
        var recipe = ((ExplodableCrafterBuild) building).getCurRecipe();
        var temp = ((ExplodableCrafterBuild) building).getTemperatureManager();
        String name;
        if (recipe.input instanceof SmeltEntry smeltEntry
                && temp instanceof ExplodableTemperatureManager explodableTemp) {
            name = Core.bundle.format(
                    String.format("bar.%s-explodable-temperature-stats", HardustryEx.MOD_NAME),
                    (int) explodableTemp.getTemperature(),
                    (int) smeltEntry.temperature,
                    (int) explodableTemp.getExplodeTemperature());
        } else name = "bar.temperature";
        return new Bar(name, Color.orange, () -> temp.calcTemperatureFrac(recipe));
    }
}