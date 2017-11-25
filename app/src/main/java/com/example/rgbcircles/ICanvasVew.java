package com.example.rgbcircles;

import com.example.rgbcircles.circles.SimpleCircle;

/**
 * Created by Константин on 13.08.2017.
 */

public interface ICanvasVew {

        void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}
