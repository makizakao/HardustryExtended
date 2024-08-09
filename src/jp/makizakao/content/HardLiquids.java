package jp.makizakao.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class HardLiquids {
    public static Liquid steam;
    public static void load()
    {
        steam = new Liquid("steam", Color.valueOf("596ab8")){{
            heatCapacity = 0.4f;
            temperature = 100f;
            explosiveness = 0.1f;
            effect = StatusEffects.wet;
        }};
    }
}
