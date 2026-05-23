package epsilon.content.Kallistea.blocks;

import epsilon.EpsilonVars;
import mindustry.content.Fx;
import mindustry.entities.effect.*;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.type.Category;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.lore.*;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.with;

public class LoreBlocks{
    public static Block
    testBlock1, newspaper1, cutsceneBlock, radioTower;

    public static void load(){
        if(EpsilonVars.testingMode) {
            testBlock1 = new DialogBlock("test-block1") {{
                requirements(Category.distribution, with(KallisteaItems.calcite, 14884252));
                health = 1;
                size = 1;
                imageName = "epsilon-icon";
                dialogName = "Router of god";
            }};
        }

        cutsceneBlock = new CutsceneBlock("cutscene-block"){{
            requirements(Category.logic, with(KallisteaItems.gelionyte, 1448425269));
            size = 1;
            health = 99999;
        }};
        newspaper1 = new DialogBlock("newspaper1") {{
            requirements(Category.logic, buildVisibility= BuildVisibility.editorOnly, with(KallisteaItems.calcite, 14884252));
            health = 1;
            size = 1;
            imageName = "newspaper-1";
            dialogName = "incers-newspaper";
        }};
    }
}
