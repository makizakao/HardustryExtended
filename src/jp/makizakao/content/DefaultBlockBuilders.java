package jp.makizakao.content;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.PowerNode;

import java.util.Objects;

public class DefaultBlockBuilders {
    public static class PowerNodeBuilder {
        public final String name;
        public final int health;
        public final int size;
        public ItemStack[] requirements;
        public float laserRange = 10f;
        public int maxNodes = 4;
        public float powerConsumption = 0f;
        public boolean isConsumePower = false;

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
