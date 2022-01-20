public class Initializer {

    private static PlayerFactory playerFactory;

    private static EnemyFactory enemyFactory;

    public static void init() {
        playerFactory = new PlayerFactory();
        enemyFactory = new EnemyFactory();
    }

    public static PlayerFactory getPlayerFactory() {
        return playerFactory;
    }

    public static EnemyFactory getEnemyFactory() {
        return enemyFactory;
    }
}
