package epsilon.world.blocks.environment;

import arc.*;
import arc.graphics.*;
import mindustry.world.blocks.environment.Floor;

public class ColorableFloor extends Floor{
    public Color color;

    public ColorableFloor(String name, Color color){
        this(name, 3, color);
    }

    public ColorableFloor(String name, int variants, Color color){
        super(name);
        this.variants = variants;
        this.mapColor.set(color);
        this.useColor = true;
    }

    @Override
    public void init(){
        super.init();
        this.mapColor.set(color);
    }
}
