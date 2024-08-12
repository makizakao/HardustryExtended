package jp.makizakao.hardustryex.world.meta;

import jp.makizakao.hardustryex.HardustryEx;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

public class HardStat {
    public static final Stat temperature = new Stat(String.format("%s-temperature", HardustryEx.MOD_NAME));
    public static final Stat maxTemperature = new Stat(String.format("%s-max-temperature", HardustryEx.MOD_NAME));
    public static final Stat powerLoss = new Stat(String.format("%s-power-loss", HardustryEx.MOD_NAME), StatCat.power);
    public static final Stat maxPowerGeneration = new Stat(
            String.format("%s-max-power-generation", HardustryEx.MOD_NAME), StatCat.power);
    public static final Stat minPowerGeneration = new Stat(
            String.format("%s-min-power-generation", HardustryEx.MOD_NAME), StatCat.power);
}
