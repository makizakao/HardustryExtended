package jp.makizakao.type;

import arc.struct.ObjectSet;
import arc.struct.Seq;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import multicraft.IOEntry;
import multicraft.Recipe;

public class ResultEntry extends IOEntry {
    public Seq<ResultStack> items = new Seq<>(ResultStack.class);


    public ResultEntry() {}

    public ResultEntry(Seq<ResultStack> items, Seq<LiquidStack> fluids) {
        this(items, fluids, 0f, 0f);
    }

    public ResultEntry(Seq<ResultStack> items, Seq<LiquidStack> fluids, float power) {
        this(items, fluids, power, 0f);
    }

    public ResultEntry(Seq<ResultStack> items, Seq<LiquidStack> fluids, float power, float heat) {
        this.items = items;
        this.fluids = fluids;
        this.power = power;
        this.heat = heat;
    }
}
