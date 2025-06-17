package epsilon.content.Kallistea;


import arc.graphics.Color;
import arc.math.*;
import epsilon.type.EpsilonWeapon;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.type.*;

import static mindustry.content.Fx.none;

public class KallisteaUnitTypes{
    public static UnitType
    // core
    penumbraStarter, sporacrawler, mycelist, fluorofang;
    public static void load(){
        penumbraStarter = new UnitType("penumbra-starter"){{
            outlineColor= Color.valueOf("2f2726");
            constructor = MechUnit::create;
            controller = u -> new BuilderAI(true, 500f); 
            canBoost = true;
            boostMultiplier = 1.3f;
            health = 200;
            speed = 1f;
            buildSpeed = 1.2f;
            armor = 2f;
            riseSpeed = 0.05f;

            mineTier = 2;
            mineSpeed = 4.5f;

            weapons.add(new Weapon("epsilon-penumbra-starter-gun"){{
                top = false;
                x = 7f;
                y = 5.5f;
                mirror = true;
                shake = 1.7f;
                reload = 85f;
                inaccuracy = 25;
                shoot.shots = 2;
                shoot.shotDelay = 1f;
                recoil = 2f;
                shootSound = Sounds.spark;
                bullet = new BasicBulletType(){{
                    width = 5;
                    height = 5;
                    speed = 2;
                    lifetime = 60;
                    damage = 30;
                    frontColor = Color.valueOf("97a6cc");
                    backColor = trailColor = Color.valueOf("4c5878");
                    trailWidth = 1.05f;
                    trailLength = 3;
                    trailEffect = Fx.missileTrail;
                    shootEffect = new ParticleEffect(){{
                        particles = 6;
                        lifetime = 30f;
                        length = 30f;
                        baseLength = 0f;
                        cone = 15;
                        colorFrom = Color.valueOf("6789e0");
                        colorTo = Color.valueOf("455273");
                        sizeFrom = 5;
                        sizeTo = 0;
                        interp = Interp.pow3;
                        sizeInterp = Interp.pow3Out;
                    }};
                }};
            }});
        }};


        // fenspor region
        // spiders
        sporacrawler = new UnitType("sporacrawler"){{
            constructor = LegsUnit::create;
            health = 300;
            speed = 1.6f;
            singleTarget = true;
            faceTarget = true;
            armor = 0;
            rotateSpeed = 2.5f;
            legStraightness = 0.7f;
            legContinuousMove = true;
            lockLegBase = true;
            legGroupSize = 3;
            legCount = 3;
            legExtension = -1.5f;
            legLength = 16f;
            legSpeed = 0.5f;
            legForwardScl = 0.8f;
            legMoveSpace = 1f;
            weapons.add(new Weapon("sporacrawler-rot"){{
               top = false;
               mirror = false;
               reload = 30;
               bullet = new BulletType(){{
                   x = 0.5f;
                   damage = 25;
                   lifetime = 5;
                   speed = 0.5f;
                   shootEffect = none;
                   hitEffect = new ParticleEffect(){{
                       particles = 5;
                       lifetime = 20f;
                       length = 35f;
                       baseLength = 0f;
                       cone = 360;
                       colorFrom = Color.valueOf("6ea7ba");
                       colorTo = Color.valueOf("3e6187");
                       sizeFrom = 7;
                       sizeTo = 0;
                       interp = Interp.pow2;
                       sizeInterp = Interp.pow2Out;
                   }};
               }};
           }});
        }};

        mycelist = new UnitType("mycelist"){{
           constructor = LegsUnit::create;
           health = 850;
            speed = 1.2f;
            singleTarget = true;
            faceTarget = true;
            armor = 3;
            rotateSpeed = 2f;
            legStraightness = 0.8f;
            legContinuousMove = true;
            lockLegBase = true;
            legGroupSize = 6;
            legCount = 4;
            legExtension = -1.2f;
            legLength = 21f;
            legSpeed = 0.85f;
            legForwardScl = 0.9f;
            legMoveSpace = 1.2f;
            weapons.add(new Weapon("mycelist-rot"){{
                x = 0.5f;
                top = false;
                mirror = false;
                reload = 75;
                bullet = new BulletType(){{
                    damage = 60;
                    lifetime = 5;
                    speed = 0.5f;
                    shootEffect = none;
                    hitEffect = new ParticleEffect(){{
                        particles = 5;
                        lifetime = 20f;
                        length = 35f;
                        baseLength = 0f;
                        cone = 360;
                        colorFrom = Color.valueOf("6ea7ba");
                        colorTo = Color.valueOf("3e6187");
                        sizeFrom = 7;
                        sizeTo = 0;
                        interp = Interp.pow2;
                        sizeInterp = Interp.pow2Out;
                    }};
                }};
                new Weapon("mycelist-gun"){{
                   top = true;
                   mirror = false;
                   x = -2f;
                   bullet = new BasicBulletType(){{
                       damage = 75;
                       lifetime = 60;
                       speed = 1.5f;
                       width = 9;
                       height = 9;
                       frontColor = Color.valueOf("a7cddb");
                       backColor = trailColor = Color.valueOf("658f9e");
                       trailWidth = 1.05f;
                       trailLength = 3;
                       trailEffect = Fx.shootSmokeDisperse;

                   }};
                }};
            }});
        }};
    }
}
