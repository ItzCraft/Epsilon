package epsilon.ui;

import arc.scene.ui.layout.Table;
import mindustry.ui.*;

/*public class EpsUi extends Ui{
    public void showDialog(String unitIconName, String text, float duration){
         var cinfo = Core.scene.find("coreinfo");
        Table table = new Table();
        table.touchable = Touchable.disabled;
        table.setFillParent(true);
        if(cinfo.visible && !state.isMenu()) table.marginTop(cinfo.getPrefHeight() / Scl.scl() / 2);
        table.actions(Actions.delay(duration * 0.9f), Actions.fadeOut(duration * 0.1f, Interp.fade), Actions.remove());
        table.bottom().table(Styles.black3, t -> t.margin(1).image(Core.atlas.find(unitIconName)).style(Styles.outlineLabel)).padLeft(10f);
        // wanna make it through Flabel but idk how
        table.bottom().table(Styles. black3, t -> t.margin(10f).add(text).style(Styles.outlineLabel)).padRight(10f);
        Core.scene.add(table);
    }
} */
