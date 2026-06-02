package epsilon.world.bullets;

import arc.math.Angles;
import mindustry.entities.bullet.BulletType;
import mindustry.gen.Bullet;

public class RingExplosionBullet extends BulletType {

    public int rings = 3;
    public int bulletsPerRing = 20;

    public float ringSpacing = 20f;

    public BulletType ringBullet;

    public RingExplosionBullet(){
        speed = 0f;
        lifetime = 1f;

        collides = false;
        hittable = false;
        absorbable = false;
        keepVelocity = false;
    }

    @Override
    public void init(Bullet b){
        super.init(b);

        float tx = b.aimX;
        float ty = b.aimY;

        b.set(tx, ty);

        createRings(b);

        hitEffect.at(tx, ty);
        despawnEffect.at(tx, ty);

        b.remove();
    }

    private void createRings(Bullet b){

        for(int ring = 0; ring < rings; ring++){

            float radius = (ring + 1) * ringSpacing;

            float angleOffset =
                    (360f / bulletsPerRing) * ring / (float)rings;

            for(int i = 0; i < bulletsPerRing; i++){

                float angle =
                        (360f / bulletsPerRing) * i + angleOffset;

                float x = b.x + Angles.trnsx(angle, radius);
                float y = b.y + Angles.trnsy(angle, radius);

                ringBullet.create(
                        b,
                        b.team,
                        x,
                        y,
                        angle
                );
            }
        }
    }
}
