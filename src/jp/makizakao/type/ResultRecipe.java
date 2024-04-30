package jp.makizakao.type;

import multicraft.IOEntry;
import multicraft.Recipe;

public class ResultRecipe extends Recipe {
    public ResultEntry output;

    public ResultRecipe(IOEntry input, ResultEntry output, float craftTime) {
        this.input = input;
        this.output = output;
        this.craftTime = craftTime;
    }

    public ResultRecipe() {}
}
