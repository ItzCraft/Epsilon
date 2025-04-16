package epsilon.ui;

import arc.scene.style.Drawable;
import arc.scene.style.TextureRegionDrawable;
import mindustry.gen.Tex;

public class EpsStyles{
    public static Drawable purpleBackground;

    public EpsStyles(){
    }

    public static void load(){
        final TextureRegionDrawable whiteui = (TextureRegionDrawable) Tex.whiteui;
        purpleBackground = whiteui.tint(0.15F, 0.0F, 0.15F, 0.5F);
    }
}
