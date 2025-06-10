package epsilon.type;

import mindustry.type.*;
import mindustry.ctype.*;

public class EpsilonSectorPreset extends SectorPreset{
    public EpsilonSector sector;
    public EpsilonSectorPreset(String name, Planet planet, int sector){
        super(name, planet, sector);
    }

    @Override
    public ContentType getContentType(){
        return ContentType.sector;
    }
}
