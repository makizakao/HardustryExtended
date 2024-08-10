package jp.makizakao.world.bar;

import arc.Core;
import arc.graphics.Color;
import jp.makizakao.HardustryEx;
import jp.makizakao.type.entry.SmeltEntry;
import jp.makizakao.world.blocks.production.HardMultiCrafter;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import multicraft.Recipe;

public class CrafterBarManager implements IBarManager<HardMultiCrafter> {
    @Override
    public void setBars(HardMultiCrafter block) {
        if (block.isConsumeHeat()) block.addBar("temperature", this::createTemperatureBar);
        if (block.isConsumeHeat() || block.isOutputHeat()) block.addBar("heat", this::createHeatBar);
    }


    protected Bar createTemperatureBar(Building building) {
        Recipe recipe = ((HardMultiCrafter.HardMultiCrafterBuild) building).getCurRecipe();
        var temp = ((HardMultiCrafter.HardMultiCrafterBuild) building).getTemperatureManager();
        String name;
        if (recipe.input instanceof SmeltEntry smeltEntry) {
            name = Core.bundle.format(String.format("bar.%s-crafter-temperature-stats", HardustryEx.MOD_NAME),
                    (int) temp.getTemperature() + 0.01F,
                    (int) smeltEntry.temperature);
        } else name = String.format("bar.%s-temperature", HardustryEx.MOD_NAME);
        return new Bar(name, Color.orange, () -> temp.calcTemperatureFrac(recipe));
    }

    protected Bar createHeatBar(Building building) {
        Recipe recipe = ((HardMultiCrafter.HardMultiCrafterBuild) building).getCurRecipe();
        var build = (HardMultiCrafter.HardMultiCrafterBuild) building;
        String name;
        if (recipe.isConsumeHeat()) {
            name = Core.bundle.format("bar.heatpercent",
                    (int)(build.heat + 0.01F),
                    (int)(build.efficiencyScale() * 100.0F + 0.01F));
        } else if (recipe.isOutputHeat()) {
            name = Core.bundle.format(String.format("bar.%s-output-heatpercent", HardustryEx.MOD_NAME),
                    (int) (build.heat + 0.01F),
                    (int) (build.heat / recipe.output.heat * 100F + 0.01F));
        } else name = "bar.heat";
        return new Bar(name, Pal.lightOrange, build::heatFrac);
    }
}
