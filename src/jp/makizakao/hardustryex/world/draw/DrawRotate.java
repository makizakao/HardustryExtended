package jp.makizakao.hardustryex.world.draw;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.util.Eachable;
import arc.util.Log;
import mindustry.entities.units.BuildPlan;
import mindustry.gen.Building;
import mindustry.world.Block;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawRegion;

public class DrawRotate extends DrawRegion {
    private static final String SUFFIX = "-rotator";
    private final float rotateSpeed;
    private final float rotateAngle;
    private final float startAngle;
    private TextureRegion region;

    public DrawRotate() {
        this(0, 360, 1f);
    }

    public DrawRotate(final float startAngle, final float rotateAngle) {
        this(startAngle, rotateAngle, 1f);
    }

    public DrawRotate(final float startAngle, final float rotateAngle, final float rotateSpeed) {
        if(rotateAngle < startAngle) {
            throw new IllegalArgumentException("rotateAngle must be greater than startAngle");
        }

        this.rotateAngle = rotateAngle;
        this.startAngle = startAngle;
        this.rotateSpeed = rotateSpeed;
    }

    @Override
    public void draw(Building build) {
        var angle = build.progress() * rotateSpeed * 360f;
        Draw.rect(region, build.x, build.y, build.drawrot() + angle % rotateAngle + startAngle);
    }

    @Override
    public void drawPlan(Block block, BuildPlan plan, Eachable<BuildPlan> list) {
        Draw.rect(region, plan.drawx(), plan.drawx(), plan.rotation);
    }

    @Override
    public TextureRegion[] icons(Block block) {
        return new TextureRegion[]{this.region};
    }

    @Override
    public void load(Block block) {
        Log.info(block.name);
        this.region = Core.atlas.find(block.name + SUFFIX);
    }
};