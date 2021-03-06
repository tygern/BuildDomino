/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.awt.*;
import javax.swing.*;

/**
* This class draws a tableau of dominoes.
* @author Tyson Gern (tygern@gmail.com)
*/
public class DrawDomino extends Canvas
{
    Tableau dominoTableau;
    int scale = 50;
    int offset = 10;
    int titleHeight = 30;
    int xLabelOffset = scale / 15;
    int yLabelOffset = scale / 15;
    public int width;
    public int height;

    public DrawDomino(Tableau input) {
        dominoTableau = input;
        width = scale * input.maxWidth() + 2 * offset;
        height = scale * input.maxHeight() + 2 * offset + titleHeight;
    }

    public void paint(Graphics graphics) {
        int longSide = 2 * scale;
        int shortSide = scale;
        for (int i = 1; i <= dominoTableau.getRank(); i++) {
            if (dominoTableau.inTableau(i)) {
                Domino current = dominoTableau.getDomino(i);
                int xVal = current.getFirstBlock().getXVal();
                int yVal = current.getFirstBlock().getYVal();
                int labelInt = i;
                String labelSt = Integer.toString(labelInt);
                int xCoord = scale * (xVal - 1) + offset;
                int yCoord = scale * (yVal - 1) + offset;
                
                if (current.getIsVertical()) {
                    graphics.drawRect(xCoord, yCoord, shortSide, longSide);
                    graphics.drawString(labelSt, xCoord + scale / 2 - xLabelOffset, yCoord + scale + yLabelOffset);
                }
                else {
                    graphics.drawRect(xCoord, yCoord, longSide, shortSide);
                    graphics.drawString(labelSt, xCoord + scale - xLabelOffset, yCoord + scale / 2 + yLabelOffset);
                }
            }
        }
    }
}