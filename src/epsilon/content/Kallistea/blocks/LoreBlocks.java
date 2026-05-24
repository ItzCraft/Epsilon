package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import epsilon.EpsilonVars;
import epsilon.content.Kallistea.EpsFx;
import mindustry.content.Fx;
import mindustry.entities.effect.*;
import mindustry.graphics.Pal;
import mindustry.world.Block;
import mindustry.type.Category;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.lore.*;
import mindustry.world.blocks.production.GenericCrafter;
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

        radioTower = new EffectableBlock("radio-tower"){{
            requirements(Category.logic, with(KallisteaItems.calcite, 120, KallisteaItems.gelionyte, 100, KallisteaItems.fylion, 35));
            size = 4;
            health = 235;
            squareSprite = false;
            consumePower(2.5f);
            craftTime = 240f;
            craftEffect = new MultiEffect(
                    EpsFx.circleFlashOut,
                    new WaveEffect(){{
                        colorFrom = Color.valueOf("ba8ede");
                        colorTo = Color.valueOf("351f47");
                        lightColor = Color.valueOf("ba8ede");
                        sides = 4;
                        lightScl = 0.7f;
                    }}
            );
        }};
    }
}
