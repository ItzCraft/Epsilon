package epsilon.content.Kallistea;


import arc.graphics.Color;
import arc.math.*;
import epsilon.EpsilonVars;
import epsilon.graphics.EpsPal;
import epsilon.type.units.EpsilonWeapon;
import mindustry.ai.types.*;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.ShootBarrel;
import mindustry.gen.*;
import mindustry.type.*;

import static mindustry.content.Fx.none;

public class KallisteaUnitTypes{
    public static UnitType
    // core
    penumbraStarter, penumbraBroken,

    // fenspor
    // spiders
    sporacrawler, mycelist,

    // mechs
    hyphastrider;
    public static void load(){
        penumbraStarter = new UnitType("penumbra-starter"){{
            outlineColor= Color.valueOf("18151a");
            constructor = MechUnit::create;
            controller = u -> new BuilderAI(true, 500f); 
            canBoost = true;
            boostMultiplier = 1.3f;
            health = 200;
            speed = EpsilonVars.testingMode ? 3.5f : 1f;
            buildSpeed = 1.2f;
            armor = 2f;
            riseSpeed = 0.05f;

            mineTier = 2;
            mineSpeed = 4.5f;

            weapons.add(new Weapon("epsilon-penumbra-starter-gun"){{
                top = false;
                mirror = true;
                x=0;
                shake = 1.7f;
                reload = 85f;
                inaccuracy = 10;
                shoot.shots = 2;
                shoot.shotDelay = 1f;
                recoil = 2f;
                shootSound = EpsMusic.electricShoot;
                bullet = new BasicBulletType(){{
                    width = 5;
                    height = 5;
                    x = -7f;
                    y = 7.5f;
                    speed = 2;
                    lifetime = 60;
                    damage = 30;
                    frontColor = Color.valueOf("fcc0ce");
                    backColor = trailColor = Color.valueOf("cc7085");
                    trailWidth = 1.05f;
                    trailLength = 3;
                    trailEffect = Fx.incendTrail;
                    shootEffect = new ParticleEffect(){{
                        particles = 6;
                        lifetime = 30f;
                        length = 30f;
                        baseLength = 0f;
                        cone = 0;
                        colorFrom = Color.valueOf("cc7085");
                        colorTo = Color.valueOf("fcc0ce");
                        sizeFrom = 5;
                        sizeTo = 0;
                        interp = Interp.pow3;
                        sizeInterp = Interp.pow3Out;
                    }};
                }};
            }});
        }};
        penumbraBroken = new UnitType("penumbra-broken"){{
            outlineColor= Color.valueOf("18151a");
            constructor = MechUnit::create;
            controller = u -> new BuilderAI(true, 500f);
            canBoost = true;
            boostMultiplier = 1.3f;
            health = 170;
            speed = EpsilonVars.testingMode ? 3.5f : 0.9f;
            buildSpeed = 1f;
            armor = 1.5f;
            riseSpeed = 0.05f;

            mineTier = 2;
            mineSpeed = 4.25f;

            weapons.add(new Weapon("epsilon-penumbra-broken-gun2"){{
                top = false;
                x = -7f;
                y = 5.5f;
                mirror = false;
                shake = 1.7f;
                reload = 85f;
                inaccuracy = 25;
                shoot.shots = 2;
                shoot.shotDelay = 1f;
                recoil = 2f;
                shootSound = EpsMusic.electricShoot;
                bullet = new BasicBulletType(){{
                    width = 5;
                    height = 5;
                    speed = 2;
                    lifetime = 60;
                    damage = 30;
                    frontColor = Color.valueOf("fcc0ce");
                    backColor = trailColor = Color.valueOf("cc7085");
                    trailWidth = 1.05f;
                    trailLength = 3;
                    trailEffect = Fx.missileTrail;
                    shootEffect = new ParticleEffect(){{
                        particles = 6;
                        lifetime = 30f;
                        length = 30f;
                        baseLength = 0f;
                        cone = 0;
                        colorFrom = Color.valueOf("cc7085");
                        colorTo = Color.valueOf("fcc0ce");
                        sizeFrom = 5;
                        sizeTo = 0;
                        interp = Interp.pow3;
                        sizeInterp = Interp.pow3Out;
                    }};
                }};
            }},
                    new EpsilonWeapon("epsilon-penumbra-broken-gun"){{
                        top = false;
                        x = 7f;
                        y = 5.5f;
                        mirror = false;
                        display = false;
                        shake = 0;
                        reload = 1f;
                        recoil = 0f;
                        shootSound = Sounds.none;
                        effectInterval = 30f;
                        effect = new MultiEffect(Fx.fire, EpsFx.energyOverloadPulse);
                        bullet = new BasicBulletType(){{
                            width = 0;
                            height = 0;
                            damage = 0;
                            speed = 0;
                            shootEffect = none;
                            smokeEffect = none;
                            hitEffect = none;
                            despawnEffect = none;
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
            outlineColor = EpsPal.fensporUnits;
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
            drawCell = false;
            weapons.add(new Weapon("epsilon-sporacrawler-mouth"){{
               top = true;
               mirror = false;
               reload = 30;
               bullet = new BulletType(){{
                   x = 0.5f;
                   damage = 25;
                   lifetime = 5;
                   speed = 0.5f;
                   shootEffect = none;
                   hitEffect = despawnEffect = new ParticleEffect(){{
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
               parts.addAll(
                       new RegionPart("-l"){{
                   progress = PartProgress.heat;
                   y = 4;
                   x = -2f;
                   moveRot = 15f;
               }},
                       new RegionPart("-r"){{
                           progress = PartProgress.heat;
                           y = 4;
                           x = 2f;
                           moveRot = -15f;
                       }}
               );
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
            drawCell = false;
            outlineColor = EpsPal.fensporUnits;
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
            weapons.add(new Weapon("epsilon-mycelist-mouth"){{
                            x = 0.5f;
                            mirror = false;
                            reload = 75;
                            top = true;
                            bullet = new BulletType(){{
                                damage = 60;
                                lifetime = 5;
                                speed = 0.5f;
                                shootEffect = none;
                                hitEffect = despawnEffect = new ParticleEffect(){{
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
                            parts.addAll(
                                    new RegionPart("-l"){{
                                        under = true;
                                        progress = PartProgress.warmup;
                                        y = 10;
                                        x = -4f;
                                        moveRot = 25f;
                                        moves.add(new PartMove(PartProgress.recoil, 0,0,-26));
                                    }},
                                    new RegionPart("-r"){{
                                        under = true;
                                        progress = PartProgress.warmup;
                                        y = 10;
                                        x = 4f;
                                        moveRot = -25f;
                                        moves.add(new PartMove(PartProgress.recoil, 0,0,26));
                                    }}
                            );
                        }},
                    new Weapon("epsilon-mycelist-barrel"){{
                        top = true;
                        mirror = false;
                        reload = 60;
                        recoil = 0.1f;
                        x = -3f;
                        shoot = new ShootBarrel(){{
                            shots = 2;
                            shotDelay = 20;
                            inaccuracy = 0;
                            barrels = new float[]{
                                    -2.5f, 4f, 15f,
                                    3f, 4.75f, -15f
                            };
                        }};
                        bullet = new BasicBulletType(){{
                            damage = 75;
                            lifetime = 60;
                            speed = 1.5f;
                            width = 9;
                            height = 9;
                            pierce = true;
                            homingPower = 10;
                            homingRange = 40;
                            frontColor = Color.valueOf("a7cddb");
                            backColor = trailColor = Color.valueOf("658f9e");
                            trailWidth = 1.05f;
                            trailLength = 7;
                            trailSinScl = 3f;
                            trailSinMag = 2;
                            trailEffect = Fx.shootSmokeDisperse;
                        }};
                        parts.addAll(
                                new RegionPart("-l"){{
                                    progress = PartProgress.recoil;
                                    under = true;
                                    top = true;
                                    y = 4;
                                    x = -3f;
                                    moveY = -1.5f;
                                }},
                                new RegionPart("-r"){{
                                    progress = PartProgress.heat;
                                    under = true;
                                    top = true;
                                    y = 4;
                                    x = 1f;
                                    moveY = -1.5f;
                                }}
                        );
                    }});

        }};

        hyphastrider = new UnitType("hyphastrider"){{
            constructor = LegsUnit::create;
            health = 130;
            speed = 1.45f;
            singleTarget = true;
            faceTarget = true;
            armor = 0;
            rotateSpeed = 1.3f;
            outlineColor = EpsPal.fensporUnits;
            legStraightness = 0.7f;
            legContinuousMove = true;
            lockLegBase = true;
            legGroupSize = 3;
            legCount = 5;
            legExtension = -1f;
            legLength = 10f;
            legSpeed = 1.2f;
            legForwardScl = 0.8f;
            legMoveSpace = 1f;
            drawCell = false;
            weapons.add(new Weapon("epsilon-hyphastrider-mouth"){{
                top = true;
                mirror = false;
                reload = 30;
                bullet = new BulletType(){{
                    x = 0;
                    damage = 10;
                    lifetime = 5;
                    speed = 0.5f;
                    shootEffect = none;
                    hitEffect = despawnEffect = new ParticleEffect(){{
                        particles = 5;
                        lifetime = 20f;
                        length = 35f;
                        baseLength = 0f;
                        cone = 360;
                        colorFrom = Color.valueOf("6ea7ba");
                        colorTo = Color.valueOf("3e6187");
                        sizeFrom = 3;
                        sizeTo = 0;
                        interp = Interp.pow2;
                        sizeInterp = Interp.pow2Out;
                    }};
                }};
                parts.addAll(
                        new RegionPart("-l"){{
                            progress = PartProgress.heat;
                            moveRot = -15f;
                        }},
                        new RegionPart("-r"){{
                            progress = PartProgress.heat;
                            moveRot = 15f;
                        }}
                );
            }});
        }};
    }
}
