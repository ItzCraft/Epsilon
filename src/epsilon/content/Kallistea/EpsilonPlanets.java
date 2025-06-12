package epsilon.content.Kallistea;

import arc.graphics.Color;
import epsilon.content.Kallistea.blocks.KallisteaStorage;
import epsilon.planet.*;
import mindustry.graphics.g3d.*;
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
            landCloudColor = Color.valueOf("6a7d9e");
            atmosphereColor = Color.valueOf("7d1bb3");
            atmosphereRadIn = 0;
            atmosphereRadOut = 0.1f;
            orbitRadius = 40f;
            iconColor = Color.valueOf("891dcc");
            solarSystem = epsilon;
            alwaysUnlocked = clearSectorOnLose = true;
            allowLaunchLoadout = allowLaunchSchematics = false;
            defaultCore = KallisteaStorage.coreObscurity;
            ruleSetter = r -> {
              r.defaultTeam = EpsTeams.ganieris;
              r.waveTeam = EpsTeams.incers;
              r.lighting = false;
              r.fog = true;
              r.staticFog = false;
              r.ambientLight = Color.valueOf("0f001fa7");
              r.onlyDepositCore = true;
            };
            allowLaunchToNumbered = false;
            updateLighting = true;
            campaignRuleDefaults.fog = true;
            startSector = 13;
            minZoom = 0.75f;
            generator = new KallisteaPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 7)
            );
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 59, 2.7f, 0.1f, 5, Color.valueOf("6a7d9e").a(0.95f), 3, 0.42f, 1f, 0.43f),
                    new HexSkyMesh(this, 1, 2f, 0.16f, 5, Color.valueOf("253785").a(0.55f), 3, 0.42f, 1.2f, 0.45f)
            );
        }};

        eryphos = new Planet("eryphos", epsilon, 1.75f, 1){{
            accessible = true;
            hasAtmosphere = true;
            atmosphereColor = Color.valueOf("929dd1");
            atmosphereRadIn = 0f;
            atmosphereRadOut = 2f;
            orbitRadius = 85f;
            solarSystem = epsilon;
            alwaysUnlocked = allowLaunchLoadout = allowLaunchSchematics = clearSectorOnLose = true;
            generator = new EryphosPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 7)
            );
        }};
    }
}
