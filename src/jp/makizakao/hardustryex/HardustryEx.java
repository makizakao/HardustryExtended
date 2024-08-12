package jp.makizakao.hardustryex;

import arc.Core;
import arc.Events;
import arc.graphics.g2d.TextureRegion;
import arc.util.Log;
import jp.makizakao.hardustryex.content.*;
import mindustry.game.EventType;
import mindustry.mod.Mod;
import java.util.function.BiFunction;

public class HardustryEx extends Mod {
    public static final String MOD_NAME = "hardustry-ex";
    public static final BiFunction<String, String, TextureRegion> regionGetter =
            (name, symbol) -> Core.atlas.find(name + "-" + symbol);

    public HardustryEx(){
        Events.on(EventType.ClientLoadEvent.class, e -> {
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading content.");
        HardItems.load();
        HardLiquids.load();
        HardTechTree.load();
        HardBlocks.load();
        TestBlocks.load();
    }
}
