package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import arc.math.Interp;
import epsilon.content.Kallistea.EpsFx;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.defense.EpsItemTurret;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class KallisteaDefense {
    public static Block dispersive, plasmaTurret;

    public static void load() {

        dispersive = new EpsItemTurret("dispersive"){{
            requirements(Category.turret, with(KallisteaItems.calcite, 90, KallisteaItems.quartz, 50));
            health = 210;
            size = 3;
            fraction = "Incers";
            itemCapacity = 20;
            targetAir = false;
            range = 150;
            inaccuracy = 3;
            reload = 90;
            ammo(
                    KallisteaItems.quartz, new BasicBulletType(){{
                        width = 10;
                        height = 12;
                        damage = 55;
                        speed = 2;
                        lifetime = 75;
                        shoot.shots = 3;
                        shoot.shotDelay = 10f;
                        ammoMultiplier = 1;
                        ammoPerShot = 3;
                        backColor = trailColor = Color.valueOf("8a762f");
                        frontColor = Color.valueOf("d1bc71");
                        trailWidth = 2.6f;
                        trailLength = 20;
                        weaveScale = 7f;
                        weaveMag = 1.8f;
                        shootEffect = new ParticleEffect(){{
                            particles = 6;
                            line = true;
                            lifetime = 40;
                            cone = 15;
                            length = 15f;
                            baseLength = 10f;
                            colorFrom = Color.valueOf("8a762f");
                            colorTo = Color.valueOf("d1bc71");
                            sizeFrom = 7;
                            sizeTo = 0;
                            strokeFrom = 2f;
                            strokeTo = 0;
                            sizeInterp = Interp.pow3Out;
                            interp = Interp.pow3;
                        }};
                        hitEffect = despawnEffect = new WaveEffect(){{
                            colorFrom = Color.valueOf("8a762f");
                            colorTo = Color.valueOf("d1bc71");
                            sizeTo = 20;
                            lightColor = Color.valueOf("d1bc71");
                        }};
                    }}
            );
        }};
        plasmaTurret = new EpsItemTurret("") {{
            requirements(Category.turret, BuildVisibility.sandboxOnly, with(Items.copper, 1));

            ammo(
                    KallisteaItems.gelionyte, new BasicBulletType(2.3f, 150, "epsilon-plasm-bullet"){{
                        width = 29f;
                        height = 34f;
                        smokeEffect = Fx.shootBigSmoke;
                        lifetime = 170;
                        ammoMultiplier = 1;
                        hitColor = backColor = Color.valueOf("8b1d2a");
                        frontColor = trailColor = Color.valueOf("ffb3bc");
                        trailWidth = 4f;
                        trailLength = 13;
                        splashDamage = 450;
                        splashDamageRadius = 73;
                        hitEffect = despawnEffect = new MultiEffect(new ExplosionEffect(){{
                            lifetime = 150;
                            waveStroke = 5;
                            waveLife = 30;
                            waveColor = Color.valueOf("ffb5bc");
                            sparkColor = Color.valueOf("ffb3bc");
                            waveRad = 70;
                            smokeSize = 13;
                            smokes = 14;
                            smokeRad = 80;
                            smokeColor = Color.valueOf("ffb3bc");
                            smokeSizeBase = 0;
                            sparks = 12;
                            sparkRad = 50;
                            sparkLen = 8;
                            sparkStroke = 2f;
                        }},
                                new WaveEffect() {{
                                    lifetime = 30f;
                                    colorFrom = Color.valueOf("ffb3bc");
                                    colorTo = Color.valueOf("ffb5bc");
                                    sizeFrom = 72;
                                    sizeTo = 72;
                                    lightScl = 3;
                                    lightOpacity = 1;
                                    strokeFrom = 6f;
                                    strokeTo = 0;
                                }},
                                new ParticleEffect(){{
                                    colorFrom = Color.valueOf("ffb5bc");
                                    colorTo = Color.valueOf("ffb3bc");
                                    particles = 24;
                                    cone = 360;
                                    baseLength = 80;
                                    lightScl = 3;
                                    lightOpacity = 2;
                                    spin = 0;
                                    sizeFrom = 5.4f;
                                    sizeTo = 0;
                                    offset = 1;
                                }});
                        buildingDamageMultiplier = 0.3f;
                        chargeEffect = new MultiEffect(EpsFx.plasmaCharge, new WaveEffect(){{
                            rotWithParent = true;
                            followParent = true;
                            lifetime = 85;
                            sizeFrom = 35;
                            sizeTo = 0;
                            strokeFrom = 0;
                            strokeTo = 2;
                            colorFrom = Color.valueOf("8b1d2a");
                            colorTo = Color.valueOf("ffb3bc");
                        }},
                                new WaveEffect(){{
                                    rotWithParent = true;
                                    followParent = true;
                                    lifetime = 150;
                                    sizeFrom = 32;
                                    sizeTo = 0;
                                    strokeFrom = 0;
                                    strokeTo = 2;
                                    colorFrom = Color.valueOf("8b1d2a");
                                    colorTo = Color.valueOf("ffb3bc");
                                }});

                        intervalBullets = 2;
                        bulletInterval = 6;
                        intervalSpread = 0;
                        intervalRandomSpread = 45;
                        intervalBullet = new BasicBulletType(3, 20){{
                            width = 9f;
                            height = 13f;
                            lifetime = 1;
                            hitColor = backColor = trailColor = Color.valueOf("8b1d2a");
                            frontColor = Color.valueOf("ffb3bc");
                            trailWidth = 1.8f;
                            trailLength = 5;
                            buildingDamageMultiplier = 0.3f;
                            hitEffect = despawnEffect = new ParticleEffect(){{
                                lifetime = 36f;
                                colorFrom = Color.valueOf("ffb3bc");
                                colorTo = Color.valueOf("ffb3bc");
                                particles = 4;
                                cone = 45;
                                length = 19;
                                baseLength = 2;
                                spin = 0;
                                sizeFrom = 4.9f;
                                sizeTo = 0;
                                offset = 1;
                            }};
                        }};
                    }});

            shoot.firstShotDelay = 150;
            size = 5;
            recoil = 1.6f;
            reload = 320;
            range = 294;
        }};
    }
}
