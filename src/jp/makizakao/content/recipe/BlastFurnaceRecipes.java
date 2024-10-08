package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.content.HardItems;
import jp.makizakao.world.builder.RecipeBuilder;
import multicraft.Recipe;

public class BlastFurnaceRecipes {
    public static final Seq<Recipe> BLAST_FURNACE_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.ironIngot, 2, HardItems.coke, 1).heat(15f)
                    .output().items(HardItems.steelIngot, 1)
                    .craftTime(3600f)
                    .build()
    );
}
