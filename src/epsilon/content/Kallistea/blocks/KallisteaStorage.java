package epsilon.content.Kallistea.blocks;

import epsilon.content.Kallistea.EpsFx;
import epsilon.content.Kallistea.EpsMusic;
import epsilon.content.Kallistea.KallisteaUnitTypes;
import mindustry.world.Block;
import mindustry.world.blocks.storage.*;
import mindustry.type.Category;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.storage.*;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class KallisteaStorage{
    public static Block
    coreObscurityBroken, coreObscurity, coreObscurityb;

    public static void load(){
        coreObscurityBroken = new CoreBlock("core-obscurity-broken"){{
            requirements(Category.effect, BuildVisibility.editorOnly, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40));
            health = 1475;
            size = 4;
            itemCapacity = 828;
            landDuration = 150f;
            fogRadius = 5;
            unitType = KallisteaUnitTypes.penumbraStarter;
            captureInvicibility = 1f;
            alwaysReplace = true;
            requiresCoreZone = false;
            landMusic = EpsMusic.alarm;
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
        coreObscurityb = new EpsilonCoreBlock("core-obscurityb"){{
            requirements(Category.effect, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 40));
            health = 1475;
            size = 4;
            itemCapacity = 1450;
            fogRadius = 5;
            unitType = KallisteaUnitTypes.penumbraBroken;
            captureInvicibility = 5f;
            alwaysUnlocked = true;
            requiresCoreZone = false;
            launchEffect = EpsFx.launchCore;
        }};
    }
}
