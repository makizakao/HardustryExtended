package jp.makizakao.hardustryex.type.entry;

import lombok.NoArgsConstructor;
import multicraft.IOEntry;

@NoArgsConstructor(force = true)
public class SmeltEntry extends IOEntry {
    public float temperature = 20f;

    @Override
    public String toString() {
        return "SmeltEntry{items=" + this.items + "fluids=" + this.fluids + "power=" + this.power + "heat=" + this.heat
                + "temperature=" + this.temperature + "}";
    }
}
