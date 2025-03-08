package epsilon.world.blocks.lore;

import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.gen.Icon;
import mindustry.ui.Styles;
import mindustry.logic.LAccess;
import mindustry.type.Category;
import mindustry.world.blocks.logic.MessageBlock;

import epsilon.cutscenes.Cutscene;
import epsilon.cutscenes.CutsceneControl;

import static mindustry.Vars.ui;

public class CutsceneBlock extends MessageBlock{
    public CutsceneBlock(String name){
        super(name);
        maxTextLength = 5000;
        maxNewlines = 200;
        configurable = true;
        destructible = true;
        solid = true;
        update = true;
        noUpdateDisabled = false;
        category = Category.logic;
    }

    @SuppressWarnings("InnerClassMayBeStatic")
    public class CutsceneControllerBuild extends MessageBuild implements CutsceneTrigger{
        @Override
        public void drawSelect(){}

        @Override
        public void buildConfiguration(Table table){
            super.buildConfiguration(table);
            table.button(Icon.play, Styles.cleari, this::activate).size(40f);
        }

        @Override
        public void created(){
            deactivate();
        }

        @Override
        public void updateTile(){
            super.updateTile();
            if (enabled) {
                playCutscene();
                enabled = false;
            }
        }
        public void playCutscene(){
            try{
                CutsceneControl.addCutsceneBus(Cutscene.phaseCode(message.toString(), this));
            }catch (Exception e){
                ui.announce("Failed to create cutscene in block: " + tileX() + " " + tileY());
            }
        }
        @Override
        public void activate() {
            enabled = true;
        }

        @Override
        public void deactivate() {
            enabled = false;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
        }

        @Override
        public void read(Reads read) {
            super.read(read);
        }
    }
}
