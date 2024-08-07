package jp.makizakao.world.drawer;

import arc.graphics.Color;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawHeatOutput;
import mindustry.world.draw.DrawMulti;

public class HardDrawMultis {
    public static final DrawMulti SMELT_FLAME = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffc099")));
    public static final DrawMulti HEAT_OUTPUT = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
}
