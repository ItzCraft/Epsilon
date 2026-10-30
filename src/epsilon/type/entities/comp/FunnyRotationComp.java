package epsilon.type.entities.comp;

import arc.util.Time;
import ent.anno.Annotations;
import mindustry.gen.Entityc;
import mindustry.gen.Physicsc;

@Annotations.EntityComponent
abstract class FunnyRotationComp implements Physicsc, Entityc {

    @Annotations.Import float rotation;

    public void update(){
        rotation += Time.delta;
    }
}
