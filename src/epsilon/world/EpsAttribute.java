package epsilon.world;

import mindustry.world.meta.Attribute;

public class EpsAttribute {
    public static Attribute
            infection;

    public static void load(){
        infection = Attribute.add("infection");
    }
}
