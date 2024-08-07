package jp.makizakao.world.drawer;

import arc.graphics.g2d.Draw;
import arc.util.Eachable;
import jp.makizakao.world.block.production.HardMultiCrafter;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;
import multicraft.MultiCrafter;

import java.util.Optional;

public class HardDrawBlocks {
    public static final DrawBlock ITEM_DISPLAY_DRAWER = new DrawBlock() {
        @Override
        public void draw(Building build) {
            Draw.rect(build.block.region, build.x, build.y, build.drawrot());
            Optional.of(build)
                    .map(b -> (HardMultiCrafter.HardMultiCrafterBuild)b)
                    .map(MultiCrafter.MultiCrafterBuild::getCurRecipe)
                    .map(r -> r.output.items.get(0))
                    .map(s -> s.item.fullIcon)
                    .ifPresent(i -> Draw.rect(i, build.x, build.y, build.drawrot()));
        }

        @Override
        public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
            block.drawDefaultPlanRegion(plan, list);
        }
    };
}
