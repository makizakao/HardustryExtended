package jp.makizakao.hardustryex.world.stat;

import arc.math.Interp;
import arc.scene.ui.layout.Cell;
import arc.scene.ui.layout.Table;
import arc.util.Time;
import jp.makizakao.hardustryex.type.entry.SmeltEntry;
import jp.makizakao.hardustryex.type.recipe.ResultRecipe;
import jp.makizakao.hardustryex.ui.ResultItemImage;
import jp.makizakao.hardustryex.world.blocks.production.HardMultiCrafter;
import jp.makizakao.hardustryex.world.meta.HardStat;
import mindustry.Vars;
import mindustry.gen.Tex;
import mindustry.graphics.Pal;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.ui.Bar;
import mindustry.ui.ItemImage;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatUnit;
import multicraft.IOEntry;
import multicraft.MultiCrafter;
import multicraft.Recipe;
import multicraft.ui.FluidImage;
import multicraft.ui.HeatImage;
import multicraft.ui.PowerImage;

import java.util.Iterator;


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
            buildIOEntry(block, t, recipe, true);
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

            buildIOEntry(block, t, recipe, false);
            table.add(t).pad(10.0F).grow();
            table.row();
        }

        table.row();
        table.defaults().grow();
    }

    protected void buildIOEntry(HardMultiCrafter block, Table table, Recipe recipe, boolean isInput) {
        Table t = new Table();
        if (isInput) {
            t.left();
        } else {
            t.right();
        }

        Table mat = new Table();
        IOEntry entry = isInput ? recipe.input : recipe.output;
        int i = 0;
        int itemIdx = 0;

        Iterator var8;
        Cell iconCell;
        for(var8 = entry.items.iterator(); var8.hasNext(); ++i) {
            ItemStack stack = (ItemStack)var8.next();
            if (!isInput && recipe instanceof ResultRecipe result) {
                float rate = result.getDropChances()[itemIdx];
                iconCell = mat.add(new ResultItemImage(stack.item.uiIcon, stack.amount, rate)).pad(2.0F);
            } else iconCell = mat.add(new ItemImage(stack.item.uiIcon, stack.amount)).pad(2.0F);

            if (block.getShowNameTooltip()) {
                iconCell.tooltip(stack.item.localizedName);
            }

            if (isInput) {
                iconCell.left();
            } else {
                iconCell.right();
            }

            if (i != 0 && i % 2 == 0) {
                mat.row();
            }
            itemIdx++;
        }

        for(var8 = entry.fluids.iterator(); var8.hasNext(); ++i) {
            LiquidStack stack = (LiquidStack)var8.next();
            iconCell = mat.add(new FluidImage(stack.liquid.uiIcon, stack.amount * 60.0F)).pad(2.0F);
            if (block.getShowNameTooltip()) {
                iconCell.tooltip(stack.liquid.localizedName);
            }

            if (isInput) {
                iconCell.left();
            } else {
                iconCell.right();
            }

            if (i != 0 && i % 2 == 0) {
                mat.row();
            }
        }

        if (entry.power > 0.0F) {
            iconCell = mat.add(new PowerImage(entry.power * 60.0F)).pad(2.0F);
            if (isInput) {
                iconCell.left();
            } else {
                iconCell.right();
            }

            if (block.getShowNameTooltip()) {
                iconCell.tooltip(entry.power + " " + StatUnit.powerSecond.localized());
            }

            ++i;
            if (i != 0 && i % 2 == 0) {
                mat.row();
            }
        }

        if (entry.heat > 0.0F) {
            iconCell = mat.add(new HeatImage(entry.heat)).pad(2.0F);
            if (isInput) {
                iconCell.left();
            } else {
                iconCell.right();
            }

            if (block.getShowNameTooltip()) {
                iconCell.tooltip(entry.heat + " " + StatUnit.heatUnits.localized());
            }

            ++i;
            if (i != 0 && i % 2 == 0) {
                mat.row();
            }
        }

        iconCell = t.add(mat);
        if (isInput) {
            iconCell.left();
        } else {
            iconCell.right();
        }

        Cell<Table> tCell = table.add(t).pad(12.0F).fill();
        tCell.width(Vars.mobile ? 90.0F : 120.0F);
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
