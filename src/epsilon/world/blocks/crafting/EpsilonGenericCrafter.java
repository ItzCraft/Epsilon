package epsilon.world.blocks.crafting;

import arc.scene.ui.layout.Table;
import mindustry.gen.Icon;
import mindustry.world.blocks.production.GenericCrafter;

public class EpsilonGenericCrafter extends GenericCrafter{
    public float oldCraftTime;

    public EpsilonGenericCrafter(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        canOverdrive = false;
    }

    @Override
    public void load(){
        oldCraftTime = craftTime;
    }

    public class EpsilonGenericCrafterBuild extends GenericCrafterBuild{
        public ProductionTypes selectedType;
        public boolean activated;

        public void selectType(ProductionTypes type) {
            this.selectedType = type;
        }

        public boolean isTypeSelected(){
            return selectedType != null;
        }

        public ProductionTypes getSelectedType(){
            return selectedType;
        }


        @Override
        public void updateTile(){

        }

        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.pencil, () -> {
                // TODO make ui for that
            });

            if(getSelectedType() == ProductionTypes.active){

            }
        }
    }
}
