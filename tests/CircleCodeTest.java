package fr.cnam.tp3.tests;

import fr.cnam.tp1and2.point.Point;
import fr.cnam.tp3.circle.Circle;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static org.junit.Assert.*;

public class CircleCodeTest {
    private final static Class<Circle> circle = Circle.class;
    private final static Set<String> meaninglessNames;

    static {
        meaninglessNames = new TreeSet<>();
        Collections.addAll(meaninglessNames, "d1", "d2", "a", "b", "dot1", "dot2", "c", "r", "c_aux");
    }

    private static Method getMethod(Class<?> c, String name, Class<?>... types) throws NoSuchMethodException {
        Method result = c.getMethod(name, types);
        assertNotNull("méthode " + name + "(" + Arrays.toString(types) + ") non déclarée !", result);
        return result;
    }

    private static Field getAttribute(Class c, String name) {
        try {
            return c.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            fail("attribut non déclaré : " + name);
            return null; // never reached
        }
    }

    private static List<Field> getAttrInstance(Class c) {
        ArrayList<Field> result = new ArrayList<>(5);
        for (Field f : c.getDeclaredFields()) {
            if (!Modifier.isStatic(f.getModifiers())) {
                result.add(f);
            }
        }
        return result;
    }

    private static void verifyConstantClass(Class c, String name) {
        Field attr = getAttribute(c, name);
        assertTrue(name + " : devrait être une constante !",
                Modifier.isFinal(attr.getModifiers()));
        assertTrue(name + " : doit être une constante de *classe* !",
                Modifier.isStatic(attr.getModifiers()));
        assertTrue(name + " : Pourquoi pas public ?",
                Modifier.isPublic(attr.getModifiers()));
    }

    @Test
    public void testC12() {
        verifyConstantClass(circle, "PI");
    }

    @Test
    public void testC12ValuePI() throws IllegalAccessException {
        Field pi = getAttribute(circle, "PI");
        pi.setAccessible(true);
        assertEquals("Le type de PI doit être double", double.class, pi.getType());
        if (Modifier.isStatic(pi.getModifiers())) {
            assertEquals("Pas d'utilisation de Math.PI pour initialiser PI ?",
                    Math.PI, pi.getDouble(null), 0.0);
        } else {
            Circle c = new Circle(new Point(1, 2), 10);
            assertEquals("Pas d'utilisation de Math.PI pour initialiser PI ?",
                    Math.PI, pi.getDouble(c), 0.0);
        }
    }

    @Test
    public void testNumberAttrs() {
        int expected = 3;
        int actual = getAttrInstance(circle).size();
        assertFalse("Trop d'attributs d'instance : " + actual
                + " au lieu de " + expected + " !", actual > expected);
        assertFalse("Pas assez d'attributs d'instance : " + actual
                + " au lieu de " + expected + " !", actual < expected);
    }

    @Test
    public void testPrivateAttrs() {
        for (Field f : circle.getDeclaredFields()) {
            if (!Modifier.isFinal(f.getModifiers())) {
                if (!Modifier.isFinal(f.getModifiers())) {
                    assertFalse("L'attribut " + f.getName() + " ne devrait pas être public !",
                            Modifier.isPublic(f.getModifiers()));
                    assertFalse("Attribut " + f + " : Pourquoi protected ?",
                            Modifier.isPublic(f.getModifiers()));
                    assertTrue("Attribut " + f + " : Droit d'accès oublié ?",
                            Modifier.isPrivate(f.getModifiers()));
                }
            }
        }
    }

    @Test
    public void testAttrNames() {
        for (Field f : circle.getDeclaredFields()) {
            String nom = f.getName();
            assertTrue("C3: Nom trop court pour l'attribut " + nom, nom.length() > 1);
        }
    }

    @Test
    public void testAttrNamesMeaningful() {
        for (Field f : circle.getDeclaredFields()) {
            String nom = f.getName();
            assertFalse("C3: Nom pas assez significatif pour l'attribut " + nom,
                    meaninglessNames.contains(nom.toLowerCase()));
        }
    }

    @Test
    public void testNbConstructors() {
        int expected = 3;
        int actual = circle.getConstructors().length;
        assertFalse("Trop de constructeurs : " + actual, actual > expected);
        assertFalse("Pas assez de constructeurs : " + actual, actual < expected);
    }

    @Test
    public void testE14() {
        Method createCircle = null;
        try {
            createCircle = getMethod(circle, "createCircle", Point.class, Point.class);
        } catch (NoSuchMethodException e) {
            fail("La méthode createCircle(Point, Point) n'existe pas !");
        }
        int modifiers = createCircle.getModifiers();
        assertTrue("createCircle devrait être publique !",
                Modifier.isPublic(modifiers));
        assertTrue("createCircle doit être une méthode de classe !",
                Modifier.isStatic(modifiers));
    }

    @Test
    public void testDefaultConstructor() {
        try {
            Constructor<Circle> defaut = circle.getConstructor();
            fail("Pourquoi définir un constructeur par défaut sur Cercle ?");
        } catch (NoSuchMethodException e) {
            // OK
        }
    }

}