package epsilon.world.blocks.lore;

import arc.Core;
import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.gen.Icon;
import mindustry.ui.Styles;
import mindustry.world.Block;
import mindustry.world.meta.BuildVisibility;
import mindustry.ui.dialogs.BaseDialog;

public class ImageBlock extends Block{
    // idk If it gonna work with Textureregion
    public String imagePath;
    public String dialogName;

    public ImageBlock(String name){
        super(name);
        update = true;
        breakable = false;
        configurable = true;
        replaceable = false;
        targetable = false;
        destructible = false;
        drawTeamOverlay = false;
        buildVisibility = BuildVisibility.sandboxOnly;
    }

    public class ImageBlockBuild extends Building{
        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.eye, Styles.cleari, () -> {
                BaseDialog imageShow = new BaseDialog(Core.bundle.get(dialogName));
                imageShow.addCloseButton();
                imageShow.cont.image(Core.atlas.find(imagePath));
                imageShow.show();
            }).size(50f);
        } 
    } 
}
