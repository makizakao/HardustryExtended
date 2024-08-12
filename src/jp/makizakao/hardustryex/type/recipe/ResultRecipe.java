package jp.makizakao.hardustryex.type.recipe;

import arc.math.Mathf;
import jp.makizakao.hardustryex.world.blocks.production.HardMultiCrafter.*;
import multicraft.IOEntry;

public class ResultRecipe extends HardRecipe {
    private float[] dropChances;

    public ResultRecipe(IOEntry input, IOEntry output, float craftTime) {
        super(input, output, craftTime);
    }

    public ResultRecipe() {}

    public float[] getDropChances() {
        return dropChances;
    }

    public void setDropChances(float[] dropChances) {
        this.dropChances = dropChances;
    }

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
}
