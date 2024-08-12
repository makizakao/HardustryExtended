package jp.makizakao.hardustryex.world.stat;

import jp.makizakao.hardustryex.world.blocks.power.HardPowerNode;
import jp.makizakao.hardustryex.world.meta.HardStat;

public class PowerNodeStatsManager implements IStatsManager<HardPowerNode> {
    @Override
    public void setStats(HardPowerNode powerNode) {
        powerNode.stats.add(HardStat.powerLoss, "@%",
                String.format("%.1f", powerNode.getPowerConsumeMultiplier() * 100 + 0.01F));
    }
}
