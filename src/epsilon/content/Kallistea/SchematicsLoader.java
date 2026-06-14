package epsilon.content.Kallistea;

import mindustry.game.Schematic;
import mindustry.game.Schematics;

public class SchematicsLoader {
    public static Schematic
            coreObscurity, coreObscurityd;
    public static void load(){
        coreObscurity = Schematics.readBase64("bXNjaAF4nBWLQQqAIBQFnyItat8tPEoniBZqfyHY/6K2iOjuKQyzmoGB6bC7CCZIIcxBuBG3zWXo98NyUg0l5haFAUzJeUoVej8UVso1JmE7Tiu+hrvE9vRMDbp+32MZWQ==");
        coreObscurityd = Schematics.readBase64("bXNjaAF4nBXKQQqAIBBG4T+TFnWATtFROkG0UJuFYDOmRkR09ww+3upBQ1dsdkJ7XITeCRfiMpsI9bwYNsou+Vi8MIAuGEshQy1rg5Fi9kF4cpJoEpvdmXy5bf2aX80H3S4ZXw==");
    }
}