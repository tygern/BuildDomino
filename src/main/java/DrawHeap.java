/**
 * Copyright (c) 2012 by Tyson Gern
 * Licensed under the MIT License 
 */

import java.awt.*;
import javax.swing.*;

/**
* This class draws a Heap of dominoes.
* @author Tyson Gern (tygern@gmail.com)
*/
public class DrawHeap extends Canvas
{
    Heap currentHeap;
    int scale = 50;
    int offset = 10;
    int titleHeight = 30;
    int xnOffset = scale / 15;
    int x1Offset = 3 * scale / 15;
    int x2Offset = 0;
    int xLabelOffset;
    int yLabelOffset = scale / 15;
    public int width;
    public int height;

    public DrawHeap(Heap input) {
        currentHeap = input;
        width = scale * input.maxWidth() + 2 * offset;
        height = scale * input.maxHeight() + 2 * offset + titleHeight;
    }

    public void paint(Graphics graphics) {
        int longSide = 2 * scale;
        int shortSide = scale;
        for (int i = 0; i < currentHeap.getSize(); i++) {
            Domino current = currentHeap.getBlock(i);
            int xVal = current.getFirstBlock().getXVal();
            int yVal = current.getFirstBlock().getYVal();
            int labelInt = current.getLabel();
            String labelSt = Integer.toString(labelInt);
            int xCoord = scale * (xVal) + offset;
            int yCoord = height - scale * (yVal + 1) - offset - titleHeight;
            
            if (labelInt == 1) xLabelOffset = x1Offset;
            else if (labelInt == 2) xLabelOffset = x2Offset;
            else xLabelOffset = xnOffset;

            graphics.drawRect(xCoord, yCoord, longSide, shortSide);
            graphics.drawString(labelSt, xCoord + scale - xLabelOffset, yCoord + scale / 2 + yLabelOffset);
        }
    }
}
