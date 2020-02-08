package fr.cnam.tp3.tests;

import fr.cnam.tp1and2.point.Point;
import fr.cnam.tp3.circle.Circle;
import fr.cnam.tp3.circle.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CircleTest {
    public final static double e = 0.001;

    private Point c, d, e1;
    private Circle c2, c3, c4;


    @Before
    public void setUp() {
        this.c = new Point(4, 1);
        this.d = new Point(8, 1);
        this.e1 = new Point(8, 4);

        this.c2 = new Circle(this.c, this.d);
        this.c3 = new Circle(this.c, this.d, Color.RED);
        this.c4 = Circle.createCircle(this.d, this.e1);
    }

    @Test
    public void testE12() {

        CircleTestBase.sameCoordinates("E12 sur c2", new Point(6, 1), this.c2.getCenter());
        assertEquals("E12", Color.BLUE, this.c2.getColor());
    }

    @Test
    public void testE13() {

        CircleTestBase.sameCoordinates("E13 sur c3", new Point(6, 1), this.c3.getCenter());
        assertEquals("E13", Color.RED, this.c3.getColor());
    }

    @Test
    public void testE14() {

        CircleTestBase.sameCoordinates("E14 sur c4", this.d, this.c4.getCenter());
        assertEquals("E14", Color.BLUE, this.c4.getColor());
    }


}
