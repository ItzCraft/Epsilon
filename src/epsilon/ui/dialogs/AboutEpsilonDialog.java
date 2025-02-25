package epsilon.ui.dialogs;

import mindustry.ui.dialogs.BaseDialog;

public class AboutEpsilonDialog extends BaseDialog{

    public BaseDialog EpsDataBase;

    public AboutEpsilonDialog(){
        super("@about.epsilon");
        addCloseButton();
        shouldPause = true;

        EpsDataBase = new BaseDialog(){
            EpsDataBase.addCloseButton();
                    dataDialog.cont.table(Tex.button, t -> {
            t.defaults().size(280f, 60f).left();
            TextButtonStyle style = Styles.flatt;

                t.button("@settings.epsilon-solar-system-database", "@epsilon-solar-system-icon", style, () -> {

t.cont.add(Core.bundle.get("settings.epsilon-solar-system-text")).row();
              t.cont.button(Core.bundle.get("settings.back"), dialog::hide).size(100f, 50f); 
                }) ;
        } 
    } 
}