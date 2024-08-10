package jp.makizakao.world.bar;

import jp.makizakao.world.blocks.production.TierDrill;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;

public class DrillBarManager implements IBarManager<TierDrill> {
    @Override
    public void setBars(TierDrill block) {
        block.addBar("progress", this::createProgressBar);
    }

    protected Bar createProgressBar(Building building) {
        return new Bar("bar.loadprogress", Pal.accent, building::progress);
    }
}
