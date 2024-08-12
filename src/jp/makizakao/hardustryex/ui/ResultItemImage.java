package jp.makizakao.hardustryex.ui;

import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.Image;
import arc.scene.ui.layout.Table;
import arc.util.Scaling;
import mindustry.core.UI;
import mindustry.ui.ItemImage;
import mindustry.ui.Styles;

public class ResultItemImage extends ItemImage {
    public ResultItemImage(TextureRegion region, int amount, float rate) {
        super(region, 0);
        this.remove();
        this.add(new Table((o) -> {
            o.left();
            o.add(new Image(region)).size(32.0F).scaling(Scaling.fit);
        }));
        if (amount != 0) {
            this.add(new Table((t) -> {
                t.left().bottom();
                t.add(amount >= 1000 ? UI.formatAmount((long)amount) : amount + "").style(Styles.outlineLabel);
                t.add(String.format("   (%.0f%%)", rate * 100.0F)).style(Styles.outlineLabel);
                t.pack();
            }));
        }
    }
}
