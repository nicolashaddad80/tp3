package fr.cnam.tp3.circle;

import fr.cnam.tp1and2.point.Point;

import javax.annotation.Nullable;

/**
 * ___________________________________________________________<br>
 * NFP121:TP" <br>
 * Nicolas HADDAD        <br>
 * nicolas.haddad80@gmail.com  <br>
 * _____________________________________________________________<br>
 * La classe Circlet Modelise un Cercle <br>
 * La modelisation d'un PCercle  est represente par son Point Centre, son Rayon et sa couleur dans trois attribus <br>
 * ____________________________________________________________________________________<br>
 */


public class Circle {
    /**
     * La Constante Pi  (la meme pour tous les objects circle)
     */
    public static final double PI = Math.PI;

    /**
     * Methode Static pour construire un cercle a partir de son Point Centre et d'un Point de sa circonference.
     *
     * @param a_pCenter : le centre de notre Cercle
     * @param a_pc      : le Point de sa circonference
     * @return : Le cercle qui vient d'etre cree
     */
    public static Circle createCircle(@Nullable Point a_pCenter, @Nullable Point a_pc) {
        assert a_pCenter != null : "Center Point  is null";
        assert a_pc != null : "Circonference Point  is null";
        assert a_pCenter.distance(a_pc) > 0 : "Points should be strictly different Points";

        return new Circle(new Point(a_pCenter.getX(), a_pCenter.getY()), a_pCenter.distance(a_pc) / 2);
    }

    /**
     * Le Centre de notre Cercler
     */
    private Point center;

    /**
     * Le Rayon de Notre Cercle
     */
    private double radius;

    /**
     * La Couleur de notre cercle
     */
    private Color col;

    /**
     * Constructeur d'un cercle a partir de son point centre et son rayon, sa couleur sera par defaut mise a blue
     *
     * @param a_center : Le Centre de notre cercle
     * @param a_radius : Le Rayon de notre Cercle
     */
    public Circle(@Nullable Point a_center, @Nullable double a_radius) {

        assert a_center != null : "Center is null";
        assert a_radius > 0 : "Radius should be strictly greater than 0";

        this.center = new Point(a_center.getX(), a_center.getY());
        this.radius = a_radius;
        this.col = Color.BLUE;
    }

    /**
     * Costructeur d'un cercle a partir de deux Points diametraux et de sa couleur.
     *
     * @param a_pd1   : le Point diametral 1
     * @param a_pd2   : le Point diametral 2
     * @param a_color : la couleur de notre Cercle
     */
    public Circle(@Nullable Point a_pd1, @Nullable Point a_pd2, @Nullable Color a_color) {

        assert a_pd1 != null : "Point 1 is null";
        assert a_pd2 != null : "Point 2 is null";
        assert a_color != null : "color is null";
        assert a_pd1.distance(a_pd2) > 0 : "Points should be strictly different Points";
        this.center = new Point((a_pd1.getX() + a_pd2.getX()) / 2, (a_pd1.getY() + a_pd2.getY()) / 2);
        this.radius = a_pd1.distance(a_pd2) / 2;
        this.col = a_color;
    }

    /**
     * Costructeur d'un cercle a partir de deux Points Diametraux.
     *
     * @param a_pd1 : le Point diametral 1
     * @param a_pd2 : le Point diametral 2
     */
    public Circle(@Nullable Point a_pd1, @Nullable Point a_pd2) {
        this(a_pd1, a_pd2, Color.BLUE);
    }

    /**
     * Methode pour transtaler notre cercle d'un deplacement selon l'axe des abscisses et d'un deplacement selon l'axe des Ordonnees.
     *
     * @param a_x : deplacement selon l'axe des Abscisses
     * @param a_y : deplacement selon l'axe des Ordonnees
     */
    public void translate(double a_x, double a_y) {
        this.center.translate(a_x, a_y);
    }

    /**
     * Methode pour obtenir le Centre de notre Cercle
     *
     * @return le Centre de notre Cercle
     */
    public Point getCenter() {
        return new Point(this.center.getX(), this.center.getY());
    }

    /**
     * Methode pour obtenir le Rayon de notre Cercle
     *
     * @return le Rayon de notre Cercle
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Methode pour obtenir le Diametre de notre Cercle
     *
     * @return le Diametre de notre Cercle
     */
    public double getDiameter() {
        return this.radius * 2;
    }

    /**
     * Methode pour obtenir le Perimetre de notre Cercle
     *
     * @return le Perimetre de notre Cercle
     */
    public double getPerimeter() {
        return 2 * Circle.PI * this.radius;
    }

    /**
     * Methode pour obtenir la surface de notre Cercle
     *
     * @return la surface de notre Cercle
     */
    public double getArea() {
        return Circle.PI * this.radius * this.radius;
    }

    /**
     * Methode pour obtenir la Couleur de notre Cercle
     *
     * @return la Couleur de notre Cercle
     */
    public Color getColor() {
        return this.col;
    }

    /**
     * Methode pour changer la couleur de notre Cercle
     *
     * @param a_color: La nouvelle coumeur de notre Cercle
     */
    public void setColor(@Nullable Color a_color) {
        assert a_color != null : "Given Color is null";
        this.col = a_color;
    }

    /**
     * Methode pour l'affichage de notre cercle
     *
     * @return la chaine de caracteres representant notre cercle
     */
    public String toString() {
        return "C" + this.radius + "@" + this.center.toString();
    }

    /**
     * Methode pour changer le Rayon de notre Cercle
     *
     * @param a_radius: Le nouveau Rayon de notre Cercle
     */
    public void setRadius(double a_radius) {
        assert a_radius > 0 : "Radius should be strictly greater than 0";
        this.radius = a_radius;
    }

    /**
     * Methode pour changer le Diametren de notre Cercle
     *
     * @param a_diameter: Le nouveau Diametre de notre Cercle
     */
    public void setDiameter(double a_diameter) {
        assert a_diameter > 0 : "Diameter should be strictly greater than 0";
        this.radius = a_diameter / 2;
    }

    /**
     * Methode pour tester si un Point est dans notre Cercle
     *
     * @param a_p: Le Point a verifier
     * @return True si le Point a_p est a l'interieur du disque.
     * False si le Point a_p est a l'exterieur du disque.
     */
    public Boolean contains(@Nullable Point a_p) {
        assert a_p != null : "The Point  is null";
        return this.center.distance(a_p) <= this.radius;
    }
}