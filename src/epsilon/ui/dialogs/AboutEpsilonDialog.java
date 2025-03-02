package epsilon.ui.dialogs;

import arc.*;
import arc.util.*;
import arc.scene.ui.layout.Table;
import mindustry.ui.dialogs.BaseDialog;

import static arc.Core.bundle;

public class AboutEpsilonDialog extends BaseDialog {
    public AboutEpsilonDialog(){
        super("@about.epsilon");
        addCloseButton();
        shouldPause = true;

        setup();
    }

    private void setup(){
        Table buttons = new Table();

        buttons.button(Core.bundle.get("epsilon-database-ss-button"), () -> {
            BaseDialog EpsilonSolarSystem = new BaseDialog("@epsilon-solar-system-title");
            EpsilonSolarSystem.addCloseButton();
            EpsilonSolarSystem.cont.add(Core.bundle.get("epsilon-database-as-text1")).row();
            EpsilonSolarSystem.show();
        }).pad(20f).size(165f, 120f);
        cont.add(buttons);
    }
}
