package epsilon.world.blocks.distribution;

import arc.graphics.g2d.Draw;
import mindustry.graphics.Layer;
import mindustry.world.blocks.distribution.DuctBridge;

public class EpsDuctBridge extends DuctBridge {
    public float speed = 5f;

    public EpsDuctBridge(String name){
        super(name);

        itemCapacity = 4;
        hasItems = true;
        underBullets = true;
        isDuct = true;
    }

    public class EpsDuctBridgeBuild extends DuctBridgeBuild{
        public float progress = 0f;

        @Override
        public void draw(){
            Draw.rect(block.region, x, y);
            Draw.rect(dirRegion, x, y, rotdeg());
            var link = findLink();
            if(link != null){
                Draw.z(Layer.blockOver + 1);
                drawBridge(rotation, x, y, link.x, link.y, null);
            }
        }
    }
}