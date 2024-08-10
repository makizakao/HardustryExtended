package jp.makizakao.type.recipe;


import jp.makizakao.world.blocks.production.HardMultiCrafter.*;
import mindustry.content.Fx;
import mindustry.type.ItemStack;
import multicraft.IOEntry;
import multicraft.Recipe;

public class HardRecipe extends Recipe implements IRecipeCraft {

    public HardRecipe(IOEntry input, IOEntry output, float craftTime) {
        super(input, output, craftTime);
    }
    public HardRecipe() {
        this.craftEffect = Fx.none;
    }

    @Override
    public void craft(HardMultiCrafterBuild building) {
        building.consume();
        if (this.isOutputItem()) {

            for (ItemStack output : this.output.items) {
                for (int i = 0; i < output.amount; ++i) {
                    building.offload(output.item);
                }
            }
        }

        if (building.wasVisible) {
            building.createCraftEffect();
        }

        if (this.craftTime > 0.0F) {
            building.craftingTime %= this.craftTime;
        } else {
            building.craftingTime = 0.0F;
        }
    }
}
