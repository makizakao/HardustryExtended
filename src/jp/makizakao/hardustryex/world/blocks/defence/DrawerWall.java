package jp.makizakao.hardustryex.world.blocks.defence;

import arc.graphics.g2d.TextureRegion;
import arc.util.Eachable;
import lombok.Builder;
import mindustry.entities.units.BuildPlan;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.draw.DrawBlock;


@Builder(builderMethodName = "of")
public class DrawerWall extends Wall {
    private final DrawBlock drawer;

    @Override
    public void load() {
        super.load();
        this.drawer.load(this);
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        this.drawer.drawPlan(this, plan, list);
    }

    @Override
    public TextureRegion[] icons() {
        return this.drawer.finalIcons(this);
    }

    public class DrawerWallBuild extends WallBuild {
        @Override
        public void draw() {
            DrawerWall.this.drawer.draw(this);
        }
    }

    public static class DrawerWallBuilder {

    }
}
