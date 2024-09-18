package jp.makizakao.hardustryex.content.builder;

import mindustry.entities.pattern.ShootPattern;
import mindustry.type.Item;

public final class Ammo {
    final Item[] ammoMaterials;
    final ShootPattern shoot;

    public Ammo(final Item[] ammoMaterials, final ShootPattern shootPattern) {
        this.ammoMaterials = ammoMaterials;
        this.shoot = shootPattern;
    }
}
