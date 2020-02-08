package fr.cnam.tp3.tests;

import fr.cnam.tp1and2.point.Point;
import fr.cnam.tp3.circle.Circle;
import fr.cnam.tp3.circle.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircleTestBase {
    public final static double e = 0.001;

    private Point A, B, C, D, E;

    private Circle C1, C2;

    static void sameCoordinates(String message, Point point1, Point point2) {
        assertEquals(message + " (x)", point1.getX(), point2.getX(), e);
        assertEquals(message + " (y)", point1.getY(), point2.getY(), e);
    }

    @Before
    public void setUp() {
        this.A = new Point(1, 2);
        this.B = new Point(2, 1);
        this.C = new Point(4, 1);
        this.D = new Point(8, 1);
        this.E = new Point(8, 4);

        this.C1 = new Circle(this.A, 2.5);
        this.C2 = new Circle(this.C, this.D, Color.YELLOW);
    }

    @Test
    public void testE1() {
        this.C1.translate(10, 20);
        sameCoordinates("E1 sur C1", new Point(11, 22), this.C1.getCenter());
        this.C2.translate(3, -1);
        sameCoordinates("E1 sur C2", new Point(9, 0), this.C2.getCenter());
    }

    @Test
    public void testE2() {
        sameCoordinates("E2 sur C1", this.A, this.C1.getCenter());
        sameCoordinates("E2 sur C2", new Point(6, 1), this.C2.getCenter());
    }

    @Test
    public void testE3() {
        assertEquals("E3 sur C1", 2.5, this.C1.getRadius(), e);
        assertEquals("E3 sur C2", 2.0, this.C2.getRadius(), e);
    }

    @Test
    public void testE4() {
        assertEquals("E4 sur C1", 5.0, this.C1.getDiameter(), e);
        assertEquals("E4 sur C2", 4.0, this.C2.getDiameter(), e);
    }

    @Test
    public void testE5() {
        assertTrue("E5", this.C1.contains(this.A));
        assertTrue("E5", this.C1.contains(this.B));
        assertFalse("E5", this.C1.contains(this.C));
        assertFalse("E5", this.C1.contains(this.D));
        assertFalse("E5", this.C1.contains(this.E));
        assertFalse("E5", this.C1.contains(this.C));
        assertFalse("E5", this.C1.contains(new Point(3, 4)));
        assertTrue("E5", new Circle(this.D, 3).contains(E));
    }

    @Test
    public void testE6() {
        assertEquals("E6", 5 * Math.PI, this.C1.getPerimeter(), e);
        assertEquals("E6", 4 * Math.PI, this.C2.getPerimeter(), e);
        assertEquals("E6", 6.25 * Math.PI, this.C1.getArea(), e);
        assertEquals("E6", 4 * Math.PI, this.C2.getArea(), e);
    }

    @Test
    public void testE9() {
        assertEquals("E9", Color.BLUE, this.C1.getColor());
        assertEquals("E9", Color.YELLOW, this.C2.getColor());
    }

    @Test
    public void testE10() {
        this.C1.setColor(Color.RED);
        assertEquals("E10", Color.RED, this.C1.getColor());
    }

    @Test
    public void testE11() {
        sameCoordinates("Centre de C1 incorrect", this.A, this.C1.getCenter());
        assertEquals("Rayon de C1 incorrect", 2.5, this.C1.getRadius(), e);
        assertEquals(Color.BLUE, this.C1.getColor());
    }

    @Test
    public void testE15() {
        assertEquals("E15: toString() redéfinie ? Correctement ?", "C2.5@(1.0, 2.0)", this.C1.toString());
    }

    @Test
    public void testE16() {
        this.C1.setRadius(10);
        assertEquals(10, this.C1.getRadius(), e);
        this.C1.setRadius(20);
        assertEquals(20, this.C1.getRadius(), e);
    }

    @Test
    public void testE17() {
        this.C1.setDiameter(10);
        assertEquals(5, this.C1.getRadius(), e);
        this.C1.setDiameter(20);
        assertEquals(10, this.C1.getRadius(), e);
    }

    @Test
    public void testE18() {
        this.C1.getCenter().translate(10, 20);
        sameCoordinates("E18 : erreur si translation du centre", new Point(1, 2), this.C1.getCenter());
        this.A.translate(10, 20);
        sameCoordinates("E18 : erreur si translation de A", new Point(1, 2), this.C1.getCenter());
    }
}