package epsilon.type;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import mindustry.gen.Icon;
import mindustry.type.*;

public class EpsilonSectorPreset extends SectorPreset{
    public TextureRegion icon;
    public EpsilonSectorPreset(String name, Planet planet, int sector){
        super(name, planet, sector);
    }

    public void loadIcon(){
        uiIcon = fullIcon = Core.atlas.find(icon);
    }
}
