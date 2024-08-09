package jp.makizakao.world.type.entry;

import multicraft.IOEntry;

public class SmeltEntry extends IOEntry {
    public float temperature = 20f;
    public SmeltEntry() {}

    @Override
    public String toString() {
        return "SmeltEntry{items=" + this.items + "fluids=" + this.fluids + "power=" + this.power + "heat=" + this.heat
                + "temperature=" + this.temperature + "}";
    }
}
