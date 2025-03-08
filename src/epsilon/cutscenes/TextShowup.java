package epsilon.cutscenes;

import arc.flabel.FLabel;
import epsilon.ui.EpsUi;

public class TextShowup extends CutsceneBase{
    public String text;
    public String unitIconName;
    public int duration;
    public TextShowup(String text, String unitIconName, int duration){
        this.text = text;
        this.unitIconName = unitIconName;
        this.duration = duration;
    }

    @Override
    public void begin(){
        
    }

    Override
    public String phaseToString() {
        return "info_text" + " " + "[" + text + unitIconName + duration + "]";
    }
}
