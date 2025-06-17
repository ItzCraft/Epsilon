package epsilon.content.Kallistea.blocks;

import epsilon.content.Kallistea.EpsFx;
import epsilon.content.Kallistea.KallisteaUnitTypes;
import mindustry.world.Block;
import mindustry.world.blocks.storage.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.storage.*;
import mindustry.world.draw.DrawCrucibleFlame;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class KallisteaStorage{
    public static Block
    coreObscurityBroken, coreObscurity;

    public static void load(){
        coreObscurityBroken = new BrokenCoreBlock("core-obscurity-broken"){{
            requirements(Category.effect, BuildVisibility.editorOnly, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40));
            health = 1475;
            size = 4;
            itemCapacity = 828;
            landDuration = 100f;
            fogRadius = 5;
            unitType = KallisteaUnitTypes.penumbraStarter;
            captureInvicibility = 1f;
            alwaysReplace = true;
            requiresCoreZone = false;
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawCrucibleFlame(){{
                        circleSpace = 0.01f;
                        circleStroke = 0.01f;
                        particleRad = 5f;
                        rotateScl = 1.45f;
                        flameRad = 6f;
                        flameRadiusMag = 2f;
                    }},
                    new DrawCrucibleFlame(){{
                        circleSpace = 0f;
                        circleStroke = 0;
                        particleRad = 5f;
                        rotateScl = 1.2f;
                        flameRad = 5f;
                    }},
                    new DrawCrucibleFlame(){{
                        circleSpace = 0;
                        circleStroke = 0;
                        particleRad = 6f;
                        rotateScl = 1f;
                        flameRad = 5f;
                    }}

            );
        }};
        coreObscurity = new EpsilonCoreBlock("core-obscurity"){{
            requirements(Category.effect, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40));
            health = 1475;
            size = 4;
            itemCapacity = 1450;
            fogRadius = 5;
            unitType = KallisteaUnitTypes.penumbraStarter;
            captureInvicibility = 5f;
            alwaysUnlocked = true;
            requiresCoreZone = false;
            launchEffect = EpsFx.launchCore;
        }};
    }
}
