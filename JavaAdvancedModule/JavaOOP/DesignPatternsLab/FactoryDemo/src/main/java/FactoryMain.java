import strategy.DrawCircleStrategy;

import java.util.ArrayList;
import java.util.List;

public class FactoryMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello now");

        List<GameObject> gameObjects = new ArrayList<>();

        Initializer.init();
        PlayerFactory playerFactory = Initializer.getPlayerFactory();
        EnemyFactory enemyFactory = Initializer.getEnemyFactory();

        gameObjects.add(playerFactory.produce());
        gameObjects.add(enemyFactory.produce());

        boolean gameOver= false;

        while(!gameOver) {


            for (GameObject gameObject : gameObjects) {
                gameObject.update();
                gameObject.draw(new DrawCircleStrategy());
            }

            Thread.sleep(2000);
            gameOver = 100 > 0;
        }
    }
}
