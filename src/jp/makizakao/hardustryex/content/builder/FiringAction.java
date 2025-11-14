package jp.makizakao.hardustryex.content.builder;

import java.util.Objects;

public class FiringAction {
    final float reloadTime;
    final float shootRange;
    final float inaccuracy;
    final FiringDisplayAction displayAction;

    /**
     * 発射動作を規定するためのクラス
     * @param displayAction 表示動作
     * @param reloadTime リロード時間
     * @param shootRange 射程
     * @param inaccuracy 発射精度の不正確さ
     */
    public FiringAction(final FiringDisplayAction displayAction, final float reloadTime, final float shootRange,
                        final float inaccuracy) {
        if (Objects.isNull(displayAction)) throw new IllegalArgumentException("表示動作が未定義です");
        if (reloadTime < 0) throw new IllegalArgumentException("リロード時間が負の値です");
        if (shootRange < 0) throw new IllegalArgumentException("射程が負の値です");
        if (inaccuracy < 0) throw new IllegalArgumentException("不正確さが負の値です");

        this.reloadTime = reloadTime;
        this.shootRange = shootRange;
        this.inaccuracy = inaccuracy;
        this.displayAction = displayAction;
    }
}
