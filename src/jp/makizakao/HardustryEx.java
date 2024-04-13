package jp.makizakao;

import arc.Events;
import arc.util.Log;
import jp.makizakao.content.HardItems;
import jp.makizakao.content.HardTechTree;
import mindustry.game.EventType;
import mindustry.mod.Mod;

public class HardustryEx extends Mod {
    public HardustryEx(){
        Events.on(EventType.ClientLoadEvent.class, e -> {
        });
    }

    @Override
    public void loadContent(){
        Log.info("Loading content.");
        HardItems.load();
        HardTechTree.load();
    }
}
