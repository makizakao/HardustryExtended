package jp.makizakao.content.drawer;

import arc.graphics.g2d.TextureRegion;
import mindustry.content.Blocks;
import mindustry.entities.part.RegionPart;
import mindustry.world.Block;
import mindustry.world.draw.DrawTurret;

public class HardDrawTurrets {
    public static final DrawTurret DUO = new DrawTurret(){
        {
            for(int i = 0; i < 2; i ++) {
                int f = i;
                parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")){{
                    progress = PartProgress.recoil;
                    recoilIndex = f;
                    under = true;
                    moveY = -1.5f;
                }});
            }
        }


        @Override
        public TextureRegion[] icons(Block block) {
            return super.icons(Blocks.duo);
        }
        @Override
        public void load(Block block) {
            super.load(Blocks.duo);
        }
    };
}
