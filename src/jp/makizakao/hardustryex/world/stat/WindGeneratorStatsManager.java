package jp.makizakao.hardustryex.world.stat;

import jp.makizakao.hardustryex.world.blocks.power.WindGenerator;
import jp.makizakao.hardustryex.world.meta.HardStat;
import mindustry.world.meta.StatUnit;

public class WindGeneratorStatsManager implements IStatsManager<WindGenerator> {
    @Override
    public void setStats(WindGenerator block) {
        block.stats.remove(block.generationType);
        block.stats.add(HardStat.maxPowerGeneration, block.getMaxGeneration(), StatUnit.powerSecond);
        block.stats.add(HardStat.minPowerGeneration, block.getMinGeneration(), StatUnit.powerSecond);
    }
}
