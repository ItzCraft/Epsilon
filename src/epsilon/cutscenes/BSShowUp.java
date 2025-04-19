package epsilon.cutscenes;

import arc.Core;
import arc.util.Strings;
import arc.util.Time;
import arc.scene.ui.layout.Table;
import arc.scene.actions.*;
import arc.scene.event.Touchable;
import arc.math.*;
import mindustry.core.UI;
import mindustry.ui.Styles;
import epsilon.cutscenes.CutsceneBase;

import static arc.Core.bundle;
import static mindustry.Vars.ui;
import static arc.scene.actions.Actions.*;

public class BSShowUp extends CutsceneBase{
    public int duration;
    public BSShowUp(int duration){
        super(0f);
        this.duration = duration;
    }

    public BSShowUp(String[] args){
        super(0f);
        this.duration = Integer.parseInt(args[0]);
    }

    @Override
    public void begin(){
        Table table = new Table();
        table.touchable = Touchable.disabled;
        table.setFillParent(true);
        table.actions(Actions.delay(duration * 0.8f), Actions.fadeOut(duration * 0.3f, Interp.fade), Actions.remove());
        table.bottom().table(Styles.black5, t -> t.margin(1).image(Core.atlas.find("epsilon-black-screen")).style(Styles.outlineLabel)).size(2500f, 2500f);
        Core.scene.add(table);
    }

    @Override
    public String phaseToString() {
        return "black-screen" + " " + "[" + duration + "]";
    }
}