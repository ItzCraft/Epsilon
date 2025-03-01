package epsilon.ui.dialogs;

import arc.*;
import arc.util.*;
import mindustry.ui.dialogs.BaseDialog;

import static arc.Core.bundle;

public class AboutEpsilonDialog extends BaseDialog {
    public AboutEpsilonDialog(){
        super("@about.epsilon");
        addCloseButton();
        shouldPause = true;

        BaseDialog buttons = new BaseDialog()

        buttons.addCloseButton();

        buttons.cont.button(Core.bundle.get("epsilon-database-ss-button"), () -> {
            BaseDialog EpsilonSolarSystem = new BaseDialog("@epsilon-solar-system-title")
            EpsilonSolarSystem.cont.add(Core.bundle.get("epsilon-database-as-text1")).row();
        }).size(100f, 50f);
    };
}