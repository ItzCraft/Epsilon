package epsilon.content.Kallistea;

import arc.graphics.Color;
import arc.math.*;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import epsilon.planet.ColorCalc;
import epsilon.planet.HeightCalc;
import epsilon.planet.KallisteaPlanetGenerator;
import mindustry.graphics.g3d.*;
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
                    offset.set(190, 300,150 );
                    octaves = 6;
                    magnitude = 1.15f;
                    heightOffset = -0.65f;
                }});
                Mathf.rand.setSeed(5);
                Seq<HeightCalc> mountains = new Seq<>();
                for(int i = 0; i < 20; i++){
                    mountains.add(new HeightCalc.DotHeight(){{
                        dir.setToRandomDirection().y = Mathf.random(1f, 4f);
                        min = 0.99f;
                        magnitude = Math.max(0.4f, dir.nor().y) * 0.3f;
                        interp = Interp.exp10In;
                    }});
                }
                mountains = new Seq<>();
                for(int i = 0; i < 15; i++){
                    mountains.add(new HeightCalc.DotHeight(){{
                        dir.setToRandomDirection().y = Mathf.random(-2f, -4f);
                        min = 0.99f;
                        magnitude = Math.max(0.6f, dir.nor().y) * 0.3f;
                        interp = Interp.exp10In;
                    }});
                }
                heights.add(new HeightCalc.MultiHeight(mountains, HeightCalc.MultiHeight.MixType.max, HeightCalc.MultiHeight.Operation.add));
                Seq<HeightCalc> craters = new Seq<>();
                Mathf.rand.setSeed(3);
                for(int i = 0; i < 5; i++){
                    craters.add(new HeightCalc.SphereHeight(){{
                        pos.set(Vec3.Y).rotate(Vec3.X, 115f);
                        radius = 0.35f + Mathf.random(0.5f);
                        offset = 0.5f;
                        set = true;
                    }});
                }
                heights.addAll(new HeightCalc.MultiHeight(craters, HeightCalc.MultiHeight.MixType.max, HeightCalc.MultiHeight.Operation.set));
                Mathf.rand.setSeed(3);
                for(int i = 0; i < 5; i++){
                    heights.add(new HeightCalc.SphereHeight(){{
                        pos.set(Vec3.Y).rotate(Vec3.X, -115f);
                        radius = 0.19f + Mathf.random(0.25f);
                        set = true;
                    }});
                }
                heights.add(new HeightCalc.ClampHeight(0f, 0.8f));
                colors.addAll(
                        new ColorCalc.NoiseColorCalc(){{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 5;
                            magnitude = 1.2f;
                            min = 0.41f;
                            max = 0.8f;
                            out = Color.valueOf("5c5c5c");
                            offset.set(1000f, 000f, -200f);
                        }},
                        new ColorCalc.NoiseColorCalc(){{
                            scale = 1.5;
                            persistence = 0.5;
                            octaves = 5;
                            magnitude = 1.2f;
                            min = 0.1f;
                            max = 0.43f;
                            out = Color.valueOf("d67c97");
                            offset.set(1000f, 000f, -200f);
                        }});
                for(int i = 0; i < 7; i++){
                    colors.add(new ColorCalc.SphereColorCalc(new Vec3().setToRandomDirection(), 0.06f, Color.valueOf("4d0b3d")));
                }
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
