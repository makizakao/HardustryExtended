package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.content.HardItems;
import jp.makizakao.builder.RecipeBuilder;
import mindustry.content.Items;
import multicraft.Recipe;

public class PressRecipes {
    public static final Seq<Recipe> BRONZE_PRESS_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 3).power(0.4f)
                    .output().items(HardItems.bronzePlate, 2)
                    .craftTime(600f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.brassIngot, 3).power(0.4f)
                    .output().items(HardItems.brassPlate, 2)
                    .craftTime(600f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.tinIngot, 3).power(0.4f)
                    .output().items(HardItems.tinPlate, 2)
                    .craftTime(600f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.copperIngot, 1).power(0.4f)
                    .output().items(HardItems.copperWire, 2)
                    .craftTime(300f)
                    .build()
    );
    public static final Seq<Recipe> BRONZE_PRESS_TIER_2 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 3).power(0.6f)
                    .output().items(HardItems.bronzePlate, 2)
                    .craftTime(400f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.brassIngot, 3).power(0.6f)
                    .output().items(HardItems.brassPlate, 2)
                    .craftTime(400f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.tinIngot, 3).power(0.6f)
                    .output().items(HardItems.tinPlate, 2)
                    .craftTime(400f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzePlate, 3).power(0.6f)
                    .output().items(HardItems.compressedBronzePlate, 1)
                    .craftTime(600f)
                    .build(),
            RecipeBuilder
                    .input().items(Items.coal, 2).power(0.6f)
                    .output().items(Items.graphite, 1)
                    .craftTime(600f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.copperIngot, 1).power(0.6f)
                    .output().items(HardItems.copperWire, 2)
                    .craftTime(150f)
                    .build()
    );
}
