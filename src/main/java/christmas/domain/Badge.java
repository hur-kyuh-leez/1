package christmas.domain;

import java.util.function.Function;

public enum Badge {

    NONE("없음"),
    STAR("별"),
    TREE("트리"),
    SANTA("산타");

    private static final int STAR_STANDARD = 5000;
    private static final int TREE_STANDARD = 10000;
    private static final int SANTA_STANDARD = 20000;

    private String badgeName;

    Badge(String badgeName) {
        this.badgeName = badgeName;
    }

    public static String calculateBadge(int discountAmount) {
        if (discountAmount >= STAR_STANDARD && discountAmount < TREE_STANDARD) {
            return Badge.STAR.badgeName;
        }
        if (discountAmount >= TREE_STANDARD && discountAmount < SANTA_STANDARD) {
            return Badge.TREE.badgeName;
        }
        if (discountAmount >= SANTA_STANDARD) {
            return Badge.SANTA.badgeName;
        }
        return Badge.NONE.badgeName;
    }

}
