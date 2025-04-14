package epsilon.content.Kallistea;


import arc.graphics.Color;
import arc.math.*;
import mindustry.ai.types.*;
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
            outlineColor= Color.valueOf("2f2726");
            constructor = MechUnit::create;
            controller = u -> new BuilderAI(true, 500f); 
            canBoost = true;
            boostMultiplier = 1.3f;
            health = 200;
            speed = 0.6f;
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
                        cone = 15;
                        colorFrom = Color.valueOf("6789e0");
                        colorTo = Color.valueOf("455273");
                        sizeFrom = 5;
                        sizeTo = 1.5f;
                        interp = Interp.pow3;
                        sizeInterp = Interp.pow3Out;
                    }};
                }};
            }});
        }};
    }
}
