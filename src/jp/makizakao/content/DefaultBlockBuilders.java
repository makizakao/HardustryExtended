package jp.makizakao.content;

import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.type.ItemStack;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.power.PowerNode;

import java.util.Objects;

public class DefaultBlockBuilders {
    public static class OreBlockBuilder {
        private final String name;
        private final Item dropItem;
        private boolean oreDefault = false;
        private float oreThreshold = 0f;
        private float oreScale = 0f;

        private OreBlockBuilder(String name, Item dropItem) {
            this.name = name;
            this.dropItem = dropItem;
        }

        public static OreBlockBuilder create(String name, Item dropItem) {
            return new OreBlockBuilder(name, dropItem);
        }

        public OreBlockBuilder oreDefault() {
            this.oreDefault = true;
            return this;
        }

        public OreBlockBuilder oreThreshold(float oreThreshold) {
            this.oreThreshold = oreThreshold;
            return this;
        }

        public OreBlockBuilder oreScale(float oreScale) {
            this.oreScale = oreScale;
            return this;
        }

        public OreBlock build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            return new OreBlock(name, dropItem) {{
                oreDefault = OreBlockBuilder.this.oreDefault;
                oreThreshold = OreBlockBuilder.this.oreThreshold;
                oreScale = OreBlockBuilder.this.oreScale;
            }};
        }
    }

    public static class PowerNodeBuilder {
        private final String name;
        private final int health;
        private final int size;
        private ItemStack[] requirements;
        private float laserRange = 10f;
        private int maxNodes = 4;
        private float powerConsumption = 0f;
        private boolean isConsumePower = false;

        private PowerNodeBuilder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        public static PowerNodeBuilder create(String name, int health, int size) {
            return new PowerNodeBuilder(name, health, size);
        }

        public PowerNodeBuilder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        public PowerNodeBuilder laserRange(float laserRange) {
            this.laserRange = laserRange;
            return this;
        }

        public PowerNodeBuilder maxNodes(int maxNodes) {
            this.maxNodes = maxNodes;
            return this;
        }

        public PowerNodeBuilder consumePower(float powerConsumption) {
            this.isConsumePower = true;
            this.powerConsumption = powerConsumption;
            return this;
        }

        public PowerNode build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            if(Objects.isNull(requirements)) throw new IllegalArgumentException("Requirements must be set");
            return new PowerNode(name) {{
                requirements(Category.power, requirements);
                health = PowerNodeBuilder.this.health;
                size = PowerNodeBuilder.this.size;
                laserRange = PowerNodeBuilder.this.laserRange;
                maxNodes = PowerNodeBuilder.this.maxNodes;
                consumesPower = isConsumePower;
                if(isConsumePower) consumePower(powerConsumption);
            }};
        }
    }
}
