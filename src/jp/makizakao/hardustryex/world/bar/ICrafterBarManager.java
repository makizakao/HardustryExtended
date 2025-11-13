package jp.makizakao.hardustryex.world.bar;

import mindustry.gen.Building;
import multicraft.Recipe;

public interface ICrafterBarManager<T> extends IBarManager<T> {
    void setLiquidBars(Building building, Recipe recipe);
}
