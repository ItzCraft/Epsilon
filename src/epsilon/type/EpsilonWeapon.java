package epsilon.type;

import arc.struct.Seq;
import arc.util.Time;
import mindustry.entities.Effect;
import mindustry.entities.units.WeaponMount;
import mindustry.gen.Unit;
import mindustry.type.Weapon;

public class EpsilonWeapon extends Weapon {
    private Seq<Effect> effects = new Seq<>();
    public float effectInterval = 60;
    private float effectTimer = 0f;
    public float effectX = 0;
    public float effectY = 0;
    public EpsilonWeapon(String name){
        super(name);
    }

    public void addEffects(Effect...effect){
        for(Effect eff : effect){
            effects.add(eff);
        }
    }

    @Override
    public void update(Unit unit, WeaponMount mount){
        super.update(unit, mount);
        effectTimer += Time.delta;

        if(effectTimer >= effectInterval){
            effectTimer = 0f;
            for(Effect eff : effects){
                eff.at(unit.x + effectX, unit.y + effectY, unit.rotation + mount.rotation);
            }
        }
    }

    @Override
    public void draw(Unit unit, WeaponMount mount){
        super.draw(unit, mount);
    }
}
