package jp.makizakao.type;

import multicraft.IOEntry;
import multicraft.Recipe;

public class ResultRecipe extends Recipe {
    public float[] dropChances;

    public ResultRecipe(IOEntry input, IOEntry output, float craftTime) {
        this.input = input;
        this.output = output;
        this.craftTime = craftTime;
    }

    public ResultRecipe() {}
}
