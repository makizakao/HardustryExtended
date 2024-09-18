package jp.makizakao.hardustryex.content.builder;

import mindustry.entities.Effect;

public class FiringDisplayAction {
    final float shootOriginPosY;
    final float recoils;
    final float recoil;
    final float shootCone;
    final Effect ammoUseEffect;

    public FiringDisplayAction(final float shootOriginPosY, final float recoil, final float recoils,
                               final float shootCone, final Effect ammoUseEffect) {
        this.shootOriginPosY = shootOriginPosY;
        this.recoils = recoils;
        this.recoil = recoil;
        this.shootCone = shootCone;
        this.ammoUseEffect = ammoUseEffect;
    }
}
