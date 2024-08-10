package jp.makizakao.content.drawer;

import arc.graphics.Color;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;
import mindustry.world.draw.DrawHeatOutput;
import mindustry.world.draw.DrawMulti;

import static jp.makizakao.content.drawer.HardDrawBlocks.*;

public class HardDrawMultis {
    public static final DrawMulti SMELT_FLAME = new DrawMulti(new DrawDefault(),
            new DrawFlame(Color.valueOf("ffc099")));
    public static final DrawMulti BOILER_FLAME = new DrawMulti(new DrawDefault(),
            getMovedDrawFlame(0f, -2f, Color.valueOf("ff9b4f")));
    public static final DrawMulti HEAT_OUTPUT = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
}
