package jp.makizakao.hardustryex.type.recipe;

import arc.math.Mathf;
import jp.makizakao.hardustryex.world.blocks.production.HardMultiCrafter.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@SuperBuilder(builderMethodName = "of")
public class ResultRecipe extends HardRecipe {
    @Getter
    private float[] dropChances;

    @Override
    public void craft(HardMultiCrafterBuild building) {
        building.consume();
        var items = this.output.items;
        if (this.isOutputItem()) {
            for (int i = 0; i < items.size; i++) for (int j = 0; j < items.get(i).amount; j++) {
                if(Mathf.random() <= this.getDropChances()[i]) {
                    building.offload(items.get(i).item);
                }
            }
        }
        if (building.wasVisible) building.createCraftEffect();
        if (this.craftTime > 0f) building.craftingTime %= this.craftTime;
        else building.craftingTime = 0f;
    }

    public abstract static class ResultRecipeBuilder<C extends ResultRecipe, B extends ResultRecipeBuilder<C, B>> extends HardRecipeBuilder<C, B> {
        public B dropChances(float... chances) {
            this.dropChances = chances;
            return self();
        }
    }
}
