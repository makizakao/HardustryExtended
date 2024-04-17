package jp.makizakao.world.blocks.power;

import arc.graphics.g2d.TextureRegion;
import mindustry.world.blocks.power.PowerGenerator;

import static jp.makizakao.HardustryEx.regionBuilder;

public abstract class RotateGenerator extends PowerGenerator {
    protected float rotateSpeed = 1f;
    protected TextureRegion rotatorRegion;

    protected RotateGenerator(String name) {
        super(name);
    }

    @Override
    public void load() {
        super.load();
        rotatorRegion = regionBuilder.apply(this.name, "rotator");
    }

    @Override
    public TextureRegion[] icons() {
        return new TextureRegion[]{region, rotatorRegion};
    }

    public class RotateGeneratorBuild extends GeneratorBuild {
        protected float progress = 0f;
    }
}
