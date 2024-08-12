package jp.makizakao.hardustryex.world.stat;


import jp.makizakao.hardustryex.world.blocks.production.ExplodableCrafter;
import jp.makizakao.hardustryex.world.blocks.production.HardMultiCrafter;
import jp.makizakao.hardustryex.world.meta.HardStat;


public class ExplodableCrafterStatsManager extends CrafterStatsManager {
    @Override
    public void setStats(HardMultiCrafter crafter) {
        var explodableCrafter = (ExplodableCrafter) crafter;
        crafter.stats.add(HardStat.maxTemperature, "@°C",
                (int) (explodableCrafter.getExplodeTemperature() + 0.01F));
    }
}
