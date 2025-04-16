package epsilon.world.blocks.crafting;

import arc.Core;
import arc.scene.ui.layout.Table;
import arc.util.*;
import epsilon.ui.EpsStyles;
import mindustry.gen.Icon;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.world.blocks.production.HeatCrafter;

public class EpsilonHeatCrafter extends HeatCrafter{
    protected float oldCraftTime;
    public float activationTime = 300;

    public EpsilonHeatCrafter(String name){
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

    public class EpsilonHeatCrafterBuild extends HeatCrafterBuild{
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
                    craftTime = oldCraftTime;
                case active:
                    if(activated && getSelectedType() == ProductionTypes.active){
                        Log.info("active");
                        craftTime = oldCraftTime*1.35f;
                        activationTimeB -= Time.delta;
                        if(activationTimeB <= 0){
                            activated = false;
                            activationTimeB = activationTime;
                        }
                    } else if(!activated && getSelectedType() == ProductionTypes.active){
                        this.efficiency = 0.0F;
                    }
            }
            super.updateTile();
        }

        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.pencil, () -> {
                BaseDialog configureUI = new BaseDialog(Core.bundle.get("configureUI"));
                Table left = new Table();
                Table passiveHeader = new Table();
                Table passiveLabel = new Table();
                passiveLabel.background(EpsStyles.purpleBackground);
                passiveLabel.add(Core.bundle.get("passive")).pad(5f);

                passiveHeader.add(passiveLabel).padLeft(40f).left();
                passiveHeader.button(Icon.resize, () -> {
                    // question mark, desc
                }).size(35f, 35f).padLeft(10f).padBottom(25f).right();
                left.add(passiveHeader).left().row();

                left.button(Core.bundle.get("select"), () -> {
                    selectType(ProductionTypes.passive);
                }).padLeft(30f).padBottom(40f).left().size(135f, 50f);
                left.row();
                Table right = new Table();
                Table activeHeader = new Table();
                Table activeLabel = new Table();
                activeLabel.background(EpsStyles.purpleBackground);
                activeLabel.add(Core.bundle.get("active")).pad(5f);

                activeHeader.add(activeLabel).padRight(40f).right();
                activeHeader.button(Icon.resize, () -> {
                    // question mark, active desc
                }).size(35f, 35f).padRight(10f).padBottom(25f).right();
                right.add(activeHeader).right().row();

                right.button(Core.bundle.get("select"), () -> {
                    selectType(ProductionTypes.active);
                }).size(135f, 50f).padBottom(40f).right();
                right.row();
                configureUI.cont.table(t -> {
                    t.add(left).left().expandX();
                    t.add().width(250f);
                    t.add(right).right().expandX();
                }).padTop(10f).row();

                configureUI.cont.add().height(40f).row();
                configureUI.cont.button(Core.bundle.get("back"), configureUI::hide).colspan(3).center().size(145f, 50f).padBottom(30f).row();

                configureUI.show();
                deselect();
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
