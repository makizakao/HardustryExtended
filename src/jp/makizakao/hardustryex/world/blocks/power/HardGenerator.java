package jp.makizakao.hardustryex.world.blocks.power;

import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import arc.util.Eachable;
import jp.makizakao.hardustryex.content.drawer.HardDrawMultis;
import mindustry.entities.units.BuildPlan;
import mindustry.world.blocks.power.PowerGenerator;
import mindustry.world.draw.DrawBlock;

public abstract class HardGenerator extends PowerGenerator {
    protected final DrawBlock drawer;

    protected HardGenerator(String name) {
        super(name);
        drawer = HardDrawMultis.SPIN_ROTATOR.get();
    }

    @Override
    public void load() {
        super.load();
        drawer.load(this);
    }

    @Override
    public TextureRegion[] icons() {
        return drawer.icons(this);
    }

    @Override
    public void drawPlanRegion(BuildPlan plan, Eachable<BuildPlan> list) {
        this.drawer.drawPlan(this, plan, list);
    }

    @Override
    public void getRegionsToOutline(Seq<TextureRegion> out) {
        this.drawer.getRegionsToOutline(this, out);
    }

    public class RotateGeneratorBuild extends GeneratorBuild {
        protected float progress;
        @Override
        public void draw() {
            HardGenerator.this.drawer.draw(this);
        }

        @Override
        public void drawLight() {
            super.drawLight();
            HardGenerator.this.drawer.drawLight(this);
        }
    }
}
