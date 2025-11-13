package jp.makizakao.hardustryex.content.drawer;

import arc.func.Prov;
import arc.graphics.Color;
import jp.makizakao.hardustryex.world.draw.DrawItem;
import jp.makizakao.hardustryex.world.draw.DrawRotate;
import mindustry.world.draw.*;

import static jp.makizakao.hardustryex.content.drawer.HardDrawBlocks.*;

public class HardDrawMultis {
    // static
    public static final DrawMulti SMELT_FLAME = new DrawMulti(new DrawDefault(),
            new DrawFlame(Color.valueOf("ffc099")));
    public static final DrawMulti BOILER_FLAME = new DrawMulti(new DrawDefault(),
            getMovedDrawFlame(0f, -2f, Color.valueOf("ff9b4f")));
    public static final DrawMulti HEAT_OUTPUT = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
    public static final DrawMulti DISPLAY_ITEM = new DrawMulti(new DrawDefault(), new DrawItem());

    // instance
    public static final Prov<DrawMulti> PRESS_HUMMER =
            () -> new DrawMulti(new DrawDefault(), new DrawRotate(0f, 60f, -1f));
    public static final Prov<DrawMulti> SPIN_ROTATOR = () -> new DrawMulti(new DrawDefault(), new DrawRotate());
    public static final Prov<DrawMulti> DRILL =
            () -> new DrawMulti(new DrawDefault(), new DrawRotate(), new DrawRegion("-top"));
}
