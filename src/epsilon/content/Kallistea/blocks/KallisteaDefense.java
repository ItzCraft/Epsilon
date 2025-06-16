package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import arc.math.Interp;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.defense.*;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.entities.effect.ParticleEffect;
import mindustry.entities.effect.WaveEffect;
import mindustry.type.Category;
import mindustry.world.Block;

import static mindustry.type.ItemStack.with;

public class KallisteaDefense {
    public static Block dispersive;

    public static void load(){
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
    }
}
