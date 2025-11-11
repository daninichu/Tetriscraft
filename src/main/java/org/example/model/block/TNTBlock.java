package org.example.model.block;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class TNTBlock extends Block{
    public List<Point> explode(Point origin) {
        return Arrays.asList(
            origin,

            new Point(origin.x, origin.y + 2),

            new Point(origin.x - 1, origin.y + 1),
            new Point(origin.x, origin.y + 1),
            new Point(origin.x + 1, origin.y + 1),

            new Point(origin.x - 2, origin.y),
            new Point(origin.x - 1, origin.y),
            new Point(origin.x, origin.y),
            new Point(origin.x + 1, origin.y),
            new Point(origin.x + 2, origin.y),

            new Point(origin.x - 1, origin.y - 1),
            new Point(origin.x, origin.y - 1),
            new Point(origin.x + 1, origin.y - 1),

            new Point(origin.x, origin.y - 2)
        );
    }
}