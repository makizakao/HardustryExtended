package jp.makizakao.hardustryex.content.drawer;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.draw.DrawBlock;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawFlame;

public class HardDrawBlocks {

    public static DrawBlock getMovedDrawDefault(float moveX, float moveY) {
        return new DrawDefault() {
            @Override
            public void draw(Building build) {
                Draw.rect(build.block.region, build.x + moveX, build.y + moveY, build.drawrot());
            }
        };
    }

    public static DrawFlame getMovedDrawFlame(float moveX, float moveY, Color color) {
        return new DrawFlame(color) {
            @Override
            public void draw(Building build) {
                if (build.warmup() > 0.0F && this.flameColor.a > 0.001F) {
                    float g = 0.3F;
                    float r = 0.06F;
                    float cr = Mathf.random(0.1F);
                    Draw.z(30.01F);
                    Draw.alpha(build.warmup());
                    Draw.alpha((1.0F - g + Mathf.absin(Time.time, 8.0F, g) + Mathf.random(r) - r) * build.warmup());
                    Draw.tint(this.flameColor);
                    Fill.circle(build.x + moveX, build.y + moveY, this.flameRadius + Mathf.absin(Time.time,
                            this.flameRadiusScl, this.flameRadiusMag) + cr);
                    Draw.color(1.0F, 1.0F, 1.0F, build.warmup());
                    Fill.circle(build.x + moveX, build.y + moveY, this.flameRadiusIn
                            + Mathf.absin(Time.time, this.flameRadiusScl, this.flameRadiusInMag) + cr);
                    Draw.color();
                }
            }
            @Override
            public void drawLight(Building build) {
                Drawf.light(build.x + moveX, build.y + moveY,
                        (this.lightRadius + Mathf.absin(this.lightSinScl, this.lightSinMag)) * build.warmup()
                                * (float)build.block.size, this.flameColor, this.lightAlpha);
            }
        };
    }
}
