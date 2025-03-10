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

public class TextShowup extends CutsceneBase{
    public String text;
    public String unitIconName;
    public int duration;
    public TextShowup(String text, String unitIconName, int duration){
        super(0f);
        this.text = text;
        this.unitIconName = unitIconName;
        this.duration = duration;
    }

    public TextShowup(String[] args){
        super(0f);
        this.text = Cutscene.phaseString(args[0]);
        this.unitIconName = Cutscene.phaseString(args[1]);
        this.duration = Integer.parseInt(args[2]);
    }

    @Override
    public void begin(){
        Table table = new Table();
        table.touchable = Touchable.disabled;
        table.setFillParent(true);
        table.actions(Actions.delay(duration * 0.8f), Actions.fadeOut(duration * 0.3f, Interp.fade), Actions.remove());
        table.bottom().table(Styles.black5, t -> t.margin(1).image(Core.atlas.find(unitIconName)).style(Styles.outlineLabel)).padLeft(-15f).padBottom(70f).size(65f, 80f);
        // wanna make it through Flabel but idk how
        table.bottom().table(Styles.black5, t -> t.margin(10f).add(Core.bundle.get(text)).style(Styles.outlineLabel)).padRight(65f).padBottom(70f).size(Core.bundle.get(text).length() * 13f, 60f);
        Core.scene.add(table);
    }
  
    @Override
    public String phaseToString() {
        return "text_dialog" + " " + "[" + text + " " + unitIconName + " " + duration + "]";
    }
}
