package epsilon.content.Kallistea;

import arc.graphics.Color;
import arc.math.Interp;
import arc.math.Mathf;
import arc.struct.Seq;
import epsilon.graphics.EpsPal;
import epsilon.planet.ColorCalc;
import epsilon.planet.HeightCalc;
import epsilon.planet.KallisteaPlanetGenerator;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.MultiMesh;
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
                    this, 4, 5, 0.4f, 0.7f, 1f, 1, 1.2f,

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
            atmosphereRadOut = 0.055f;
            orbitRadius = 40f;
            solarSystem = epsilon;
            alwaysUnlocked = allowLaunchLoadout = allowLaunchSchematics = clearSectorOnLose = true;
            generator = new KallisteaPlanetGenerator(){{
                baseHeight = 0f;
                baseColor = Color.valueOf("68588c");
                heights.add(new HeightCalc.NoiseHeight(){{
                    offset.set(100, 0,0 );
                    octaves = 6;
                    magnitude = 1;
                    heightOffset = -0.65f;
                }});
                Mathf.rand.setSeed(2);
                Seq<HeightCalc> mountains = new Seq<>();
                for(int i = 0; i < 12; i++){
                    mountains.add(new HeightCalc.DotHeight(){{
                        dir.setToRandomDirection().y = Mathf.random(2f, 5f);
                        min = 0.99f;
                        magnitude = Math.max(0.7f, dir.nor().y) * 0.3f;
                        interp = Interp.exp10In;
                    }});
                }
                heights.add(new HeightCalc.MultiHeight(mountains, HeightCalc.MultiHeight.MixType.max, HeightCalc.MultiHeight.Operation.add));
                colors.addAll(
                        new ColorCalc.NoiseColorCalc(){{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 3;
                            magnitude = 1.2f;
                            min = 0.3f;
                            max = 0.6f;
                            out = Color.valueOf("5c5c5c");
                            offset.set(1500f, 300f, -500f);
                        }});
            }};
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, 6)
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
