package jp.makizakao.world.meta;

import jp.makizakao.HardustryEx;
import mindustry.world.meta.Stat;

public class HardStat {
    public static final Stat temperature = new Stat(String.format("%s-temperature", HardustryEx.MOD_NAME));
    public static final Stat maxTemperature = new Stat(String.format("%s-max-temperature", HardustryEx.MOD_NAME));
}
