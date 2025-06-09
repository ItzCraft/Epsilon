package epsilon.content.Kallistea;

import arc.graphics.Color;
import epsilon.planet.KallisteaPlanetGenerator;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.graphics.g3d.NoiseMesh;
import mindustry.graphics.g3d.SunMesh;
import mindustry.maps.planet.SerpuloPlanetGenerator;
import mindustry.type.Planet;

public class EpsilonPlanets{
    public static Planet epsilon, kallistea, eryphos, keraunos;

    public static void load(){
        epsilon = new Planet("epsilon", null, 5f, 0){{
            bloom = true;
            accessible = true;
            hasAtmosphere = true;
            solarSystem = this;

            meshLoader = () -> new SunMesh(
                    this, 5, 8, 0.4f, 0.7f, 1.4f, 1.6f, 1.2f,

                    Color.valueOf("253585"),
                    Color.valueOf("8d9ff7"),
                    Color.valueOf("405be3"),
                    Color.valueOf("7b8ad4"),
                    Color.valueOf("4d5478")
            );
        }};

        kallistea = new Planet("kallistea", epsilon, 0.82f, 1){{
            accessible = true;
            hasAtmosphere = true;
            atmosphereColor = Color.valueOf("7d1bb3");
            atmosphereRadIn = 0;
            atmosphereRadOut = 0.1f;
            orbitRadius = 40f;
            solarSystem = epsilon;
            alwaysUnlocked = allowLaunchLoadout = allowLaunchSchematics = clearSectorOnLose = true;
            minZoom = 0.75f;
            generator = new KallisteaPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 7)
            );
        }};

        eryphos = new Planet("eryphos", epsilon, 1.75f, 1){{
            accessible = true;
            hasAtmosphere = true;
            atmosphereColor = Color.valueOf("a9b0d1");
            atmosphereRadIn = 0;
            atmosphereRadOut = 0.1f;
            orbitRadius = 115f;
            solarSystem = epsilon;
            alwaysUnlocked = allowLaunchLoadout = allowLaunchSchematics = clearSectorOnLose = true;
            generator = new SerpuloPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 7)
            );
        }};
    }
}
