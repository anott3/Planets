import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.stage.Stage;
import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.EnumSet;
/**
  * Class representation of Planetarium that shows planets in orbit
  *
  * @author Adithya Nott
  * @version    9.0.0.1
  */
public class Planetarium extends Application {

    private static final int SCENE_WIDTH = 790;
    private static final int SCENE_HEIGHT = SCENE_WIDTH;
    private ArrayDeque<Planet> planets = new ArrayDeque<Planet>();

    /**
      * Planetarium's start method to run the application
      *
      * @param  stage   Stage paramter that Planetarium takes in
      */
    public void start(Stage stage) {
        addAllPlanets(EnumSet.allOf(Planet.class));

        Group root = new Group();

        root.getChildren().addAll(planets.stream().map(
            (c) -> c.getOrbitalPath()).collect(Collectors.toList()));
        root.getChildren().addAll(planets.stream().map(
            (c) -> c.getCircle()).collect(Collectors.toList()));

        planets.stream().map((c) -> (
            c.animateOrbitalPath())).collect(Collectors.toList());

        root.getChildren(e).add(getSun());

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
      * Circle representation of the Sun
      *
      * @return Circle representation of the Sun
      */
    public static final Circle getSun() {
        return new Circle(SCENE_WIDTH / 2.0, SCENE_HEIGHT / 2.0, 65.0,
            Color.YELLOW);
    }

    /**
      * Adds all the planet from the Planet enum to the Planetarium class to be
      * stored in an ArrayDeque
      *
      * @param <P>  generic Collection that extends a Collection of Planet
      * @param p  planets to be added
      * @return true if the planets were added to the ArrayDeque
      */
    public <P extends Collection<Planet>> boolean addAllPlanets(P p) {
        return planets.addAll(p);
    }

}
