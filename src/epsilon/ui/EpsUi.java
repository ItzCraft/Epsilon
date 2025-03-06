package epsilon.ui;

import arc.scene.ui.layout.Table;
import arc.scene.actions.TouchableAction;
import arc.math.*;
import mindustry.core.UI;
import mindustry.ui.Styles;

import static arc.Core.bundle;
import static mindustry.Vars.ui;
import static arc.scene.actions.Actions.*;

public class EpsUi extends UI{
    public void showDialog(String unitIconName, String text, float duration){
        Table table = new Table();
        table.touchable = Touchable.disabled;
        table.setFillParent(true);
        table.actions(Actions.delay(duration * 1.2f), Actions.fadeOut(duration * 0.2f, Interp.fade), Actions.remove());
        table.bottom().table(Styles.black3, t -> t.margin(1).image(Core.atlas.find(unitIconName)).style(Styles.outlineLabel)).padLeft(10f);
        // wanna make it through Flabel but idk how
        table.bottom().table(Styles.black3, t -> t.margin(10f).add(text).style(Styles.outlineLabel)).padRight(10f);
        Core.scene.add(table);
    }
}
