package epsilon.content.Kallistea;


import arc.graphics.Color;
import arc.math.Interp;
import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.type.*;

public class KallisteaUnitTypes{
    public static UnitType
    // core
    penumbraStarter;
    public static void load(){
        penumbraStarter = new UnitType("penumbra-starter"){{
            constructor = MechUnit::create;
            controller = u -> new BuilderAI(true, 500f); 
            health = 200;
            speed = 1.1f;
            buildSpeed = 1.4f;
            armor = 2f;
            riseSpeed = 0.1f;

            mineTier = 2;
            mineSpeed = 4.5f;

            weapons.add(new Weapon("gun"){{
                top = false;
                mirror = true;
                shake = 1.7f;
                reload = 85f;
                inaccuracy = 25;
                shoot.shots = 2;
                shoot.shotDelay = 1f;
                recoil = 2f;
                shootSound = Sounds.spark;
                bullet = new LightningBulletType(){{
                    lightningColor = hitColor = Color.valueOf("9caedb");
                    damage = 30f;
                    lightningLength = 5;
                    shootEffect = new ParticleEffect(){{
                        particles = 6;
                        cone = 15;
                        colorFrom = Color.valueOf("6789e0");
                        colorTo = Color.valueOf("455273");
                        sizeFrom = 5;
                        sizeTo = 1.5f;
                        interp = Interp.pow3;
                        sizeInterp = Interp.pow3Out;
                    }};
                    lightningType = new BasicBulletType(0.0001f, 0f){{
                        lifetime = Fx.lightning.lifetime;
                        despawnEffect = Fx.none;
                    }};
                }};
            }});
        }};
    }
}
