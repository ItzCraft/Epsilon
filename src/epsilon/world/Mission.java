package epsilon.world;

import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;

public class Mission extends UnlockableContent {
    public boolean ARBITRASH;
    public Mission(String name){
        super(name);
    }
    @Override
    public ContentType getContentType(){
        return ContentType.status;
    }
}
