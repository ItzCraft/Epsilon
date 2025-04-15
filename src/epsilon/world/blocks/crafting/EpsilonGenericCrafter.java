package epsilon.world.blocks.crafting;

import arc.Core;
import arc.scene.ui.layout.Table;
import arc.util.*;
import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.blocks.production.GenericCrafter;

public class EpsilonGenericCrafter extends GenericCrafter{
    public float oldCraftTime;
    public float activationTime = 300;

    public EpsilonGenericCrafter(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        canOverdrive = false;
    }

    @Override
    public void load(){
        super.load();
        oldCraftTime = craftTime;
    }

    public class EpsilonGenericCrafterBuild extends GenericCrafterBuild{
        public ProductionTypes selectedType = ProductionTypes.passive;
        private boolean activated;
        private float activationTimeB = activationTime;

        public void selectType(ProductionTypes type){
            this.selectedType = type;
        }

        public ProductionTypes getSelectedType(){
            return selectedType;
        }


        @Override
        public void updateTile(){
            switch(getSelectedType()){
                case passive:
                    Log.info("passive");
                case active:
                    if(activated && getSelectedType() == ProductionTypes.active){
                        Log.info("active");
                        activationTimeB -= Time.delta;
                        if(activationTimeB <= 0){
                            activated = false;
                            activationTimeB = activationTime;
                        }
                    }
            }
        }

        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.pencil, () -> {
                BaseDialog configureUI = new BaseDialog(Core.bundle.get("configureUI"));
                configureUI.cont.add(Core.bundle.get("passive")).padLeft(40f).left();
                configureUI.cont.button(Icon.resize, () -> {
                    // question mark, desc
                }).left().padBottom(25f).padLeft(20f).size(35f, 35f).row();
                configureUI.button(Core.bundle.get("select"), () -> {
                    selectType(ProductionTypes.passive);
                }).padLeft(30f).padBottom(40f).left().size(135f, 50f);

                configureUI.cont.add(Core.bundle.get("active")).padRight(40f).right();
                configureUI.cont.button(Icon.resize, () -> {
                    // question mark, desc
                }).left().padBottom(25f).padRight(20f).size(35f, 35f).row();
                configureUI.button(Core.bundle.get("select"), () -> {
                    selectType(ProductionTypes.active);
                }).padRight(-30f).padBottom(40f).right().size(135f, 50f);
                configureUI.cont.button(Core.bundle.get("back"), configureUI::hide).size(145f, 50f).padBottom(30f);
                configureUI.show();
            }).size(50f);

            if(getSelectedType() == ProductionTypes.active){
                table.button(Icon.play, () -> {
                    activated = true;
                    deselect();
                }).size(50f);
            }
        }
    }
}
