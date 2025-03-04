package epsilon.world.blocks.lore;

import mindustry.world.Block;
import mindustry.ui.dialogs.BaseDialog;

public class ImageBlock extends Block{
    // idk If it gonna work with Textureregion
    public String image;
    public ImageBlock(String name){
        super(name);
        update = true;
        breakable = false;
    }

    /* public class ImageBlockBuild extends Building{
        @Override
        public void buildConfiguration(Table table){
            
        } 
    } */
}
