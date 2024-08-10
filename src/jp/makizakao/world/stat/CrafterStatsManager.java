package jp.makizakao.world.stat;

import arc.math.Interp;
import arc.scene.ui.layout.Cell;
import arc.scene.ui.layout.Table;
import arc.util.Time;
import jp.makizakao.type.entry.SmeltEntry;
import jp.makizakao.world.blocks.production.HardMultiCrafter;
import jp.makizakao.world.meta.HardStat;
import mindustry.Vars;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import multicraft.MultiCrafter;
import multicraft.Recipe;


public class CrafterStatsManager implements IMultiStatsManager<HardMultiCrafter> {
    @Override
    public void setStats(HardMultiCrafter crafter) {
    }

    @Override
    public void buildStats(HardMultiCrafter block, Table table) {
        table.row();

        for (Recipe recipe : block.resolvedRecipes) {
            Table t = new Table();
            t.background(Tex.whiteui);
            t.setColor(Pal.darkestGray);
            block.buildIOEntry(t, recipe, true);
            Table time = new Table();
            float[] duration = new float[]{0.0F};
            float visualCraftTime = recipe.craftTime;
            time.update(() -> {
                duration[0] += Time.delta;
                if (duration[0] > visualCraftTime) {
                    duration[0] = 0.0F;
                }

            });
            String barText = getBarText(block, table, recipe);
            Cell<Bar> barCell = time.add(new Bar(
                    () -> barText,
                    () -> Pal.accent,
                    () -> Interp.smooth.apply(duration[0] / visualCraftTime))).height(45.0F);
            barCell.width(Vars.mobile ? 220.0F : 250.0F);
            Cell<Table> timeCell = t.add(time).pad(12.0F);
            if (block.getShowNameTooltip()) {
                timeCell.tooltip(getTooltipText(block, table, recipe));
            }

            block.buildIOEntry(t, recipe, false);
            table.add(t).pad(10.0F).grow();
            table.row();
        }

        table.row();
        table.defaults().grow();
    }

    protected String getBarText(MultiCrafter crafter, Table table, Recipe recipe) {
        String craftTime = recipe.craftTime == 0.0F ? "0" : String.format("%.2f", recipe.craftTime / 60.0F);
        if (recipe.input instanceof SmeltEntry) {
            return String.format("%s (%s)", craftTime, getTemperatureText(recipe));
        } else {
            return craftTime;
        }
    }

    protected String getTooltipText(MultiCrafter crafter, Table table, Recipe recipe) {
        String craftTime = getCraftTimeText(recipe);
        String temperature = getTemperatureText(recipe);
        if (temperature.isEmpty())
        {
            return getTooltipCraftTimeText(craftTime);
        }
        return String.format("%s\n%s", getTooltipCraftTimeText(craftTime), getTooltipTemperatureText(temperature));
    }

    protected String getCraftTimeText(Recipe recipe) {
        return recipe.craftTime == 0.0F ? "0" : String.format("%.2f", recipe.craftTime / 60.0F);
    }

    protected String getTemperatureText(Recipe recipe) {
        if (recipe.input instanceof SmeltEntry smeltEntry) {
            return String.format("%d°C", (int) (smeltEntry.temperature + 0.01F));
        }
        return "";
    }

    protected String getTooltipTemperatureText(String temperature) {
        return String.format("%s : %s", HardStat.temperature.localized(), temperature);
    }

    protected String getTooltipCraftTimeText(String craftTime) {
        return String.format("%s : %s %s", Stat.productionTime.localized(), craftTime, StatUnit.seconds.localized());
    }
}
