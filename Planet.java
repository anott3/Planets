import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Circle;
import javafx.animation.PathTransition;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;


/**
  * Enum representation of Planet
  *
  * @author Adithya Nott
  * @version    9.0.0.1
  */
public enum Planet {

    EARTH(Color.SPRINGGREEN, 1, 1, 1),
    MERCURY(Color.SILVER, 0.24, 0.1915, 0.387),
    VENUS(Color.GREEN, 0.62, 0.4745, 0.723),
    MARS(Color.RED, 1.88, 0.266, 1.52);

    private final Color color;
    private final double period;
    private final double radius;
    private final double distance;
    private Circle circle;
    private Path orbitalPath;

    /** DO NOT MODIFY IT'S FOR YOUR OWN GOOD**/
    private final int earthRadius = 35;
    private final int earthDistance = 265;
    private final int earthPeriod = 5;
    /** OK YOU'RE GOOD GO AHEAD AND DO WORK NOW **/

    /**
      * Constructor for Planet enum
      *
      * @param   color  color of planet
      * @param   propPeriod    magnitude of the period relative to Earth's
      * @param   propRadius    magnitude of the radius relative to Earth's
      * @param   propDistance    magnitude of the distance relative to Earth's
      */
    Planet(Color color, double propPeriod, double propRadius,
        double propDistance) {
        this.color = color;
        this.period = propPeriod * earthPeriod;
        this.radius = propRadius * earthRadius;
        this.distance = propDistance * earthDistance;
    }

    /**
      * Returns the circle representation of the planet. Designed so that the
      * Circle representation is effectively final.
      *
      * @return circle for the given planet
      */
    Circle getCircle() {
        if (circle == null) {
            circle = new Circle(Planetarium.getSun().getCenterX(),
                Planetarium.getSun().getCenterY() - distance, radius, color);
        }
        return circle;
    }


    /**
      * Returns the Path that the planet uses to orbit the Sun. Designed so that
      * the orbit Path is effectively final.
      *
      * @return path for the given planet
      */
    Path getOrbitalPath() {
        if (orbitalPath != null) {
            return orbitalPath;
        }
        Path orbitingPath = new Path();
        orbitingPath.setStroke(Color.WHITE);
        ArcTo planetArcToFirst = new ArcTo();
        planetArcToFirst.setX(getCircle().getCenterX());
        planetArcToFirst.setY(getCircle().getCenterY() + (2 * distance));
        planetArcToFirst.setRadiusX(distance);
        planetArcToFirst.setRadiusY(distance);

        ArcTo planetArcToSecond = new ArcTo();
        planetArcToSecond.setX(getCircle().getCenterX());
        planetArcToSecond.setY(getCircle().getCenterY());
        planetArcToSecond.setRadiusX(distance);
        planetArcToSecond.setRadiusY(distance);

        orbitingPath.getElements().addAll(new MoveTo(getCircle().getCenterX(),
            getCircle().getCenterY()), planetArcToFirst, planetArcToSecond);
        orbitalPath = orbitingPath;
        return orbitalPath;
    }

    /**
      * Animates the Planet's orbit around the sun
      *
      * @return PathTransition animation for the circle passed in
      */
    PathTransition animateOrbitalPath() {
        PathTransition planetPathT = new PathTransition();
        planetPathT.setNode(getCircle());
        planetPathT.setDuration(Duration.seconds(period));
        planetPathT.setPath(getOrbitalPath());
        planetPathT.setCycleCount(Timeline.INDEFINITE);
        planetPathT.setInterpolator(Interpolator.LINEAR);
        planetPathT.play();
        return planetPathT;
    }
}
