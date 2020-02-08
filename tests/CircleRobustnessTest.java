package fr.cnam.tp3.tests;

import fr.cnam.tp1and2.point.Point;
import fr.cnam.tp3.circle.Circle;
import fr.cnam.tp3.circle.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class CircleRobustnessTest {

    private Circle c1;

    @Before
    public void setUp() {
        this.c1 = new Circle(new Point(1, 2), 10);
    }

    @Test
    public void testOptionEnableAssertion() {
        try {
            assert false;
        } catch (AssertionError e) {
            // C'est normal !
            return;
        } catch (Throwable e) {
            // C'est pas normal !
        }
        fail("Il faut exécuter avec l'option -ea de java !");
    }

    @Test(expected = AssertionError.class)
    public void testE5a() {
        this.c1.contains(null);
    }

    @Test(expected = AssertionError.class)
    public void testE10a() {
        this.c1.setColor(null);
    }

    @Test(expected = AssertionError.class)
    public void testE11a() {
        this.c1 = new Circle(null, 10);
    }

    @Test(expected = AssertionError.class)
    public void testE11b() {
        this.c1 = new Circle(new Point(1, 2), -10);
    }

    @Test(expected = AssertionError.class)
    public void testE11c() {
        this.c1 = new Circle(new Point(1, 2), 0);
    }

    @Test(expected = AssertionError.class)
    public void testE12a() {
        this.c1 = new Circle(new Point(1, 2), null);
    }

    @Test(expected = AssertionError.class)
    public void testE12b() {
        this.c1 = new Circle(null, new Point(1, 2));
    }

    @Test(expected = AssertionError.class)
    public void testE12c() {
        this.c1 = new Circle(new Point(1, 2), new Point(1, 2));
    }

    @Test(expected = AssertionError.class)
    public void testE13a() {
        this.c1 = new Circle(new Point(1, 2), null, Color.RED);
    }

    @Test(expected = AssertionError.class)
    public void testE13b() {
        this.c1 = new Circle(null, new Point(1, 2), Color.RED);
    }

    @Test(expected = AssertionError.class)
    public void testE13c() {
        this.c1 = new Circle(new Point(1, 2), new Point(1, 2), Color.RED);
    }

    @Test(expected = AssertionError.class)
    public void testE13d() {
        this.c1 = new Circle(new Point(1, 2), new Point(1, 2), null);
    }

    @Test(expected = AssertionError.class)
    public void testE14a() {
        this.c1 = Circle.createCircle(new Point(1, 2), null);
    }

    @Test(expected = AssertionError.class)
    public void testE14b() {
        this.c1 = Circle.createCircle(null, new Point(1, 2));
    }

    @Test(expected = AssertionError.class)
    public void testE14c() {
        this.c1 = Circle.createCircle(new Point(1, 2), new Point(1, 2));
    }

    @Test(expected = AssertionError.class)
    public void testE16a() {
        this.c1.setRadius(-10);
    }

    @Test(expected = AssertionError.class)
    public void testE16b() {
        this.c1.setRadius(0);
    }

    @Test(expected = AssertionError.class)
    public void testE17a() {
        this.c1.setDiameter(-10);
    }

    @Test(expected = AssertionError.class)
    public void testE17b() {
        this.c1.setDiameter(0);
    }
}