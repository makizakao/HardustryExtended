package jp.makizakao.hardustryex.world.bar;

import arc.Core;
import arc.func.Func;
import arc.func.Prov;
import arc.graphics.Color;
import arc.util.Log;
import arc.util.Strings;
import jp.makizakao.hardustryex.HardustryEx;
import jp.makizakao.hardustryex.type.entry.SmeltEntry;
import jp.makizakao.hardustryex.world.blocks.production.HardMultiCrafter;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.LiquidStack;
import mindustry.ui.Bar;
import multicraft.Recipe;

import java.util.Objects;

public class CrafterBarManager implements ICrafterBarManager<HardMultiCrafter> {

    @Override
    public void setBars(HardMultiCrafter block) {
        removeBars(block);
        addBars(block);
    }

    protected void removeBars(HardMultiCrafter block) {
    }

    protected void addBars(HardMultiCrafter block) {
        block.addBar("health", this::createHealthBar);
        if (block.hasPower) block.addBar("power", this::createPowerBar);
        if (block.hasItems && block.configurable) block.addBar("items", this::createItemsBar);
        if (block.isConsumeHeat() || block.isOutputHeat()) block.addBar("heat", this::createHeatBar);
        if (block.isConsumeHeat()) block.addBar("temperature", this::createTemperatureBar);
        block.addBar("progress", this::createProgressBar);
    }

    protected Bar createHealthBar(Building building) {
        Objects.requireNonNull(building);
        return new Bar("stat.health", Pal.health, building::healthf).blink(Color.white);
    }

    protected Bar createPowerBar(Building building) {
        var crafter = (HardMultiCrafter.HardMultiCrafterBuild) building;
        var recipe = crafter.getCurRecipe();
        Prov<CharSequence> name = () -> {
            if(!recipe.isOutputPower()) return Core.bundle.format("bar.power");
            return Core.bundle.format("bar.poweroutput",
                    Strings.fixed(crafter.getPowerProduction() * 60.0F * crafter.timeScale(), 1));
        };
        return new Bar(name, () -> Pal.powerBar, () -> crafter.efficiency);
    }

    protected Bar createItemsBar(Building building) {
        return new Bar(
                () -> Core.bundle.format("bar.items", building.items.total()),
                () -> Pal.items,
                () -> (float)building.items.total() / (float)building.block().itemCapacity);
    }

    protected Bar createProgressBar(Building building) {
        return new Bar("bar.loadprogress", Pal.accent, building::progress);
    }

    protected Bar createTemperatureBar(Building building) {
        Recipe recipe = ((HardMultiCrafter.HardMultiCrafterBuild) building).getCurRecipe();
        var temp = ((HardMultiCrafter.HardMultiCrafterBuild) building).getTemperatureManager();
        Prov<CharSequence> name = () -> {
            if (!(recipe.input instanceof SmeltEntry smeltEntry)) {
                return String.format("bar.%s-temperature", HardustryEx.MOD_NAME);
            }
            return Core.bundle.format(String.format("bar.%s-crafter-temperature-stats", HardustryEx.MOD_NAME),
                    (int) (temp.temperature() + 0.01F),
                    (int) smeltEntry.temperature);
        };
        return new Bar(name, () -> Color.orange, () -> temp.calcTemperatureFrac(recipe));
    }

    protected Bar createHeatBar(Building building) {
        Recipe recipe = ((HardMultiCrafter.HardMultiCrafterBuild) building).getCurRecipe();
        var build = (HardMultiCrafter.HardMultiCrafterBuild) building;
        Prov<CharSequence>  name = () -> {
            if (recipe.isConsumeHeat()) {
                return Core.bundle.format("bar.heatpercent",
                        (int)(build.heat + 0.01F),
                        (int)(build.efficiencyScale() * 100.0F + 0.01F));
            } else if (recipe.isOutputHeat()) {
                return Core.bundle.format(String.format("bar.%s-output-heatpercent", HardustryEx.MOD_NAME),
                        (int) (build.heat + 0.01F),
                        (int) (build.heat / recipe.output.heat * 100F + 0.01F));
            }

            return "bar.heat";
        };
        return new Bar(name, () -> Pal.lightOrange, build::heatFrac);
    }

    @Override
    public void setLiquidBars(Building building, Recipe recipe) {
        var block = building.block();
        for(LiquidStack liq : recipe.input.fluids.items){
            block.addBar(String.format("liquid-%s", liq.liquid.name), createLiquidBar(liq));
        }

        for(LiquidStack liq : recipe.output.fluids.items) {
            block.addBar(String.format("liquid-%s", liq.liquid.name), createLiquidBar(liq));
        }
    }

    protected Func<Building, Bar> createLiquidBar(LiquidStack liquid) {
        return building -> new Bar(
                () -> liquid.liquid.localizedName,
                () -> liquid.liquid.color,
                () -> building.liquids().get(liquid.liquid) / building.block().liquidCapacity);
    }
}
