package epsilon.ui;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Font;
import arc.scene.style.Drawable;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.ImageButton;
import arc.scene.ui.TextButton;
import mindustry.gen.Tex;
import mindustry.ui.Fonts;

public class EpsStyles{
    public static Drawable purpleBackground, blackBackground;
    public static ImageButton.ImageButtonStyle epsButtonImage;
    public static TextButton.TextButtonStyle epsButtonText;


    public EpsStyles(){
    }

    public static void load(){
        final TextureRegionDrawable whiteui = (TextureRegionDrawable) Tex.whiteui;
        purpleBackground = whiteui.tint(0.15F, 0.0F, 0.15F, 0.5F);
        blackBackground = whiteui.tint(0.0F, 0.0F, 0.0F, 1F);
        epsButtonImage = new ImageButton.ImageButtonStyle() {{
            up = imageUp = Core.atlas.drawable("epsilon-button-epsilon");
        }};
        epsButtonText = new TextButton.TextButtonStyle() {{
            up = down = Core.atlas.drawable("epsilon-button-epsilon");
            font = Fonts.def;
            fontColor = Color.white;
            disabledFontColor = Color.gray;
        }};
    }
}
