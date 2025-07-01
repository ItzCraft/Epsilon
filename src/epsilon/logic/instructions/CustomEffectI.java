package epsilon.logic.instructions;


import arc.util.Tmp;
import epsilon.logic.EpsLogicFx;
import mindustry.core.World;
import mindustry.logic.LExecutor;
import mindustry.logic.LVar;


public class CustomEffectI implements LExecutor.LInstruction{
    public EpsLogicFx.EffectEntry effect;
    public LVar x, y, rotation, color, data;

    public CustomEffectI(EpsLogicFx.EffectEntry effect, LVar x, LVar y, LVar rotation, LVar color, LVar data){
        this.effect = effect;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.color = color;
        this.data = data;
    }

    public CustomEffectI(){}

    @Override
    public void run(LExecutor exec){
        if(effect != null){
            double col = color.num();
            float rot = effect.rotate ? rotation.numf() :
                    Math.min(rotation.numf(), 1000f);

            effect.effect.at(World.unconv(x.numf()), World.unconv(y.numf()), rot, Tmp.c1.fromDouble(col), data.obj());
        }
    }
}
