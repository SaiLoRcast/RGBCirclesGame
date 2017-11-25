package com.example.rgbcircles.circles;

import android.graphics.Color;

import com.example.rgbcircles.GameManager;

public class MainCircle extends SimpleCircle {

    private static final int INIT_RADIUS = 50;
    private static final int MAIN_SPEED = 30;

    public MainCircle(int x, int y) {
        super(x, y, INIT_RADIUS);
        setColor(Color.BLUE);
    }

    public void moveMainCircleWhenTouchAt(int x1, int y1) {
        int dx = (x1 - x) * MAIN_SPEED / GameManager.getWidth();
        int dy = (y1 - y) * MAIN_SPEED / GameManager.getHeigth();
        x += dx;
        y += dy;
    }

    public void initRadius() {
        radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {
        radius = (int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }
}
