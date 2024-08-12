package jp.makizakao.hardustryex.world.stat;

import arc.scene.ui.layout.Table;

public interface IMultiStatsManager<T> extends IStatsManager<T> {
    void buildStats(T block, Table table);
}
