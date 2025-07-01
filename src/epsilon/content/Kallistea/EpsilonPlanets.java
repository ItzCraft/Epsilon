package epsilon.content.Kallistea;

import arc.func.Cons;
import arc.graphics.Color;
import arc.math.Rand;
import arc.math.geom.Mat3D;
import arc.util.Tmp;
import arc.struct.Seq;
import epsilon.EpsilonVars;
import epsilon.content.Kallistea.blocks.KallisteaEnv;
import epsilon.content.Kallistea.blocks.KallisteaStorage;
import epsilon.planet.*;
import mindustry.content.Blocks;
import mindustry.graphics.g3d.*;
import mindustry.maps.planet.AsteroidGenerator;
import mindustry.maps.planet.ErekirPlanetGenerator;
import mindustry.type.*;
import mindustry.world.Block;
import mindustry.world.meta.Env;

public class EpsilonPlanets{
    public static Planet
            //star
            epsilon,

    // planets
    kallistea, eryphos, keraunos,

    // moons
    faniera, hyrokat, losphyros, enviros,

    // asteroids
    veraunius, ardium, kerainius, keraunosB, keraunosC, larita;

    public static void load(){
        // regions stars
        epsilon = new Planet("epsilon", null, 7.5f, 0){{
            bloom = true;
            accessible = true;
            hasAtmosphere = true;
            solarSystem = this;

            meshLoader = () -> new SunMesh(
                    this, 5, 8, 0.4f, 0.7f, 1.4f, 1.6f, 1.2f,

                    Color.valueOf("6c72c4"),
                    Color.valueOf("60749e"),
                    Color.valueOf("7b8ad4"),
                    Color.valueOf("8d9ff7"),
                    Color.valueOf("b4c7de")
            );
        }};

        // region planets
        kallistea = new Planet("kallistea", epsilon, 0.82f, 1){{
            accessible = true;
            hasAtmosphere = true;
            landCloudColor = Color.valueOf("6a7d9e");
            atmosphereColor = Color.valueOf("7d1bb3");
            atmosphereRadIn = 0.01f;
            atmosphereRadOut = 0.1f;
            orbitTime = 60f*20f;
            rotateTime = 60f*12.3f;
            orbitRadius = 40f;
            iconColor = Color.valueOf("891dcc");
            solarSystem = epsilon;
            alwaysUnlocked = clearSectorOnLose = true;
            allowLaunchLoadout = allowLaunchSchematics = false;
            defaultCore = KallisteaStorage.coreObscurity;
            ruleSetter = r -> {
              r.defaultTeam = EpsTeams.ganieris;
              r.waveTeam = EpsTeams.incers;
              r.fog = true;
              r.staticFog = false;
              r.ambientLight = Color.valueOf("0f001fa7");
              r.onlyDepositCore = true;
            };
            allowLaunchToNumbered = false;
            updateLighting = false;
            campaignRuleDefaults.fog = true;
            startSector = 13;
            minZoom = 0.75f;
            generator = new KallisteaPlanetGenerator();
            meshLoader = () -> new MultiMesh(
                    new HexMesh(this, EpsilonVars.detailedSolarSystem ? 7 : 5)
            );
            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 59, 2.7f, 0.1f, 5, Color.valueOf("6a7d9e").a(0.95f), 3, 0.42f, 1f, 0.43f),
                    new HexSkyMesh(this, 1, 2f, 0.16f, 5, Color.valueOf("253785").a(0.55f), 3, 0.42f, 1.2f, 0.45f)
            );
        }};

        //due a vertices limit which is set by Anuken i have to minimalize vertices for bad devices (it should crash and on good devices but for some people it is somehow works lmao)
        if(EpsilonVars.detailedSolarSystem){
            eryphos = new Planet("eryphos", epsilon, 1.75f, 1){{
                accessible = false;
                hasAtmosphere = true;
                atmosphereColor = Color.valueOf("929dd1");
                atmosphereRadIn = 0f;
                atmosphereRadOut = 3f;
                orbitTime = 60f*47f;
                orbitRadius = 85f;
                rotateTime = 60f*836f;
                solarSystem = epsilon;
                updateLighting = true;
                alwaysUnlocked = allowLaunchLoadout = allowLaunchSchematics = clearSectorOnLose = true;
                generator = new EryphosPlanetGenerator();
                meshLoader = () -> new MultiMesh(
                        new HexMesh(this, 7)
                );
            }};
            /*keraunos = new Planet("keraunos", epsilon, 2.75f, 1){{
               accessible = false;
               hasAtmosphere = true;
               atmosphereColor = Color.valueOf("a6eb81");
               atmosphereRadIn = 0f;
               atmosphereRadOut = 6f;
               minZoom = 6f;
               solarSystem = epsilon;
               orbitRadius = 110f;
               orbitTime = 60f*70f;
               rotateTime = 60f*6f;
               alwaysUnlocked = true;
               generator = new ErekirPlanetGenerator();
               meshLoader = () -> new MultiMesh(
                        new HexMesh(this, 7)
               );
               cloudMeshLoader = () -> new MultiMesh(
                        new HexSkyMesh(this, 12, 7.7f, 2.26f, 5, Color.valueOf("d1c99d").a(0.65f), 6, 0.1f, 2f, 1f),
                        new HexSkyMesh(this, 22, 5f, 2.36f, 5, Color.valueOf("bab66e").a(0.65f), 6, 0.1f, 0.2f, 0.565f),
                       new HexSkyMesh(this, 33, 4.2f, 2.48f, 5, Color.valueOf("75543d").a(0.65f), 6, 0.12f, 0.16f, 0.75f),
                       new HexSkyMesh(this, 44, 3.1f, 2.51f, 5, Color.valueOf("8c5343").a(0.65f), 6, 0.12f, 0.12f, 0.75f),
                       new HexSkyMesh(this, 55, 1.74f, 2.56f, 5, Color.valueOf("a63721").a(0.65f), 6, 0.12f, 0.19f, 0.75f)
                       );
            }};*/


            // region moons


            // region asteroids
            veraunius = makeAsteroid("verainius", epsilon, KallisteaEnv.gelionyticWall, Blocks.ice, 34, 0.6f, 3, 1.3f, gen -> {
               gen.iceChance = 0.43f;
               gen.berylChance = 0.6f;
               gen.carbonChance = 0.1f;
               gen.stoneChance = 0.7f;
            });
            ardium = makeAsteroid("ardium", epsilon, Blocks.redIce, Blocks.deepwater, 64, 0.2f, 1, 0.1f, gen -> {
               gen.iceChance = 1f;
            });
            kerainius = makeAsteroid("kerainius", epsilon, KallisteaEnv.calciteOre, KallisteaEnv.eadstonWallDeanytic, 21, 0.45f, 5, 0.35f, gen -> {
                gen.carbonChance = 0.45f;
                gen.stoneChance = 0.93f;
            });
            /*keraunosB = makeAsteroid("keraunosB", keraunos, Blocks.sand, KallisteaEnv.meraporaWall, 12, 0.2f, 4,0.1f,gen -> {
               gen.stoneChance = 0.53f;
               gen.berylChance = 0.21f;
               gen.carbonChance = 0.52f;
            });
            keraunosC = makeAsteroid("keraunosC", keraunos, Blocks.arkyciteFloor, Blocks.basalt, 69, 0.15f, 2,0.05f,gen -> {
                gen.stoneChance = 0.33f;
                gen.berylChance = 0.71f;
                gen.carbonChance = 0.12f;
            });*/
            larita = makeAsteroid("larita", epsilon, KallisteaEnv.gelionyteCluster, KallisteaEnv.eadstonWallFenspor, 827, 0.63f, 7, 0.04f, gen -> {
               gen.iceChance = 1.42f;
            });
        }
    }
    private static Planet makeAsteroid(String name, Planet parent, Block base, Block tint, int seed, float tintThresh, int pieces, float scale, Cons<AsteroidGenerator> cgen) {
        return new Planet(name, parent, 0.12f) {{
            hasAtmosphere = false;
            updateLighting = false;
            sectors.add(new Sector(this, PlanetGrid.Ptile.empty));
            camRadius = 0.68f * scale;
            minZoom = 0.6f;
            drawOrbit = false;
            accessible = false;
            clipRadius = 2f;
            defaultEnv = Env.space;
            icon = "commandRally";
            generator = new AsteroidGenerator();
            cgen.get((AsteroidGenerator) generator);

            meshLoader = () -> {
                iconColor = tint.mapColor;
                Color tinted = tint.mapColor.cpy().a(1f - tint.mapColor.a);
                Seq<GenericMesh> meshes = new Seq<>();
                Color color = base.mapColor;
                Rand rand = new Rand(id + 2);

                meshes.add(new NoiseMesh(
                        this, seed, 2, radius, 2, 0.55f, 0.45f, 14f,
                        color, tinted, 3, 0.6f, 0.38f, tintThresh
                ));

                for (int j = 0; j < pieces; j++) {
                    meshes.add(new MatMesh(
                            new NoiseMesh(this, seed + j + 1, 1, 0.022f + rand.random(0.039f) * scale, 2, 0.6f, 0.38f, 20f,
                                    color, tinted, 3, 0.6f, 0.38f, tintThresh),
                            new Mat3D().setToTranslation(Tmp.v31.setToRandomDirection(rand).setLength(rand.random(0.44f, 1.4f) * scale)))
                    );
                }

                return new MultiMesh(meshes.toArray(GenericMesh.class));
            };
        }};
    }
}
