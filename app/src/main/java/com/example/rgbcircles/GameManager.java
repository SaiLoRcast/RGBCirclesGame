package com.example.rgbcircles;

import com.example.rgbcircles.circles.EnemyCircle;
import com.example.rgbcircles.circles.MainCircle;
import com.example.rgbcircles.circles.SimpleCircle;

import java.util.ArrayList;

public class GameManager {

    public static final int MAX_CIRCLES = 10;

    private MainCircle mainCircle;
    private ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        width = w;
        height = h;
        this.canvasView = canvasView;
        initMainCircle();
        initEnemyCircles();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeigth() {
        return height;
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea();
        circles = new ArrayList<EnemyCircle>();

        for (int i = 0; i < MAX_CIRCLES; i++) {
            EnemyCircle circle;
            do {
                circle = EnemyCircle.getRandomCircle();

            } while (circle.isIntersect(mainCircleArea));
            circles.add(circle);
        }
        calculateAndSetCircleColor();
    }

    private void calculateAndSetCircleColor() {
        for (EnemyCircle circle : circles) {
            circle.setEnemyOrFoodColorDependsOn(mainCircle);
        }
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
        for (EnemyCircle circle : circles) {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveMainCircleWhenTouchAt(x, y);
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {
        SimpleCircle circleDel = null;
        for (EnemyCircle circle : circles) {
            if (mainCircle.isIntersect(circle)) {
                if (circle.isSmallerThan(mainCircle)) {
                    mainCircle.growRadius(circle);
                    circleDel = circle;
                    calculateAndSetCircleColor();
                    break;
                } else {
                    gameEnd("ВЫ ПРОИГРАЛИ!");
                    return;
                }
            }
        }
        if (circleDel != null) {
            circles.remove(circleDel);
        }
        if (circles.isEmpty()) {
            gameEnd("ВЫ ВЫИГРАЛИ!");
        }
    }

    private void gameEnd(String text) {
        canvasView.showMessage(text);
        mainCircle.initRadius();
        initEnemyCircles();
        canvasView.redraw();
    }


    private void moveCircles() {
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
    }
}
