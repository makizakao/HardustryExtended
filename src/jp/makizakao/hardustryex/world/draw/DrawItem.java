package jp.makizakao.hardustryex.world.draw;

import arc.graphics.g2d.Draw;
import jp.makizakao.hardustryex.world.blocks.production.HardMultiCrafter;
import mindustry.gen.Building;
import mindustry.world.draw.DrawBlock;

import java.util.Objects;

public class DrawItem extends DrawBlock {
    @Override
    public void draw(Building building) {
        if(!(building instanceof HardMultiCrafter.HardMultiCrafterBuild crafter)) return;
        if(Objects.isNull(crafter.getCurRecipe()) || !crafter.getCurRecipe().isOutputItem()) return;

        var icon = crafter.getCurRecipe().output.items.get(0).item.fullIcon;
        Draw.rect(icon, building.x, building.y, building.drawrot());
    }
}
