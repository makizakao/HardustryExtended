package jp.makizakao.hardustryex.world.bar;

import arc.Core;
import jp.makizakao.hardustryex.HardustryEx;
import jp.makizakao.hardustryex.world.blocks.power.HardPowerNode;
import mindustry.core.UI;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.meta.StatUnit;

public class PowerNodeBarManager implements IBarManager<HardPowerNode> {
    @Override
    public void setBars(HardPowerNode block) {
        block.addBar("powerloss", this::createPowerLossBar);
    }

    protected Bar createPowerLossBar(Building building) {
        var powerNode = (HardPowerNode) building.block;
        var powerLoss = powerNode.getPowerLoss(building);
        String displayLoss;
        if (powerLoss < 100f) {
            displayLoss = String.format("%.2f", powerLoss);
        } else if (powerLoss < 1000f) {
            displayLoss = String.format("%.0f", powerLoss);
        } else {
            displayLoss = UI.formatAmount((long) (powerLoss / 1000f + 0.01F));
        }
        return new Bar(Core.bundle.format(String.format("bar.%s-power-loss", HardustryEx.MOD_NAME),
                String.format("%s %s", displayLoss, StatUnit.powerSecond.localized())),
                Pal.powerBar, () -> 1f);
    }
}
