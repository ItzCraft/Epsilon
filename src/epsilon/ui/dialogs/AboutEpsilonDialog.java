package epsilon.ui.dialogs;

import mindustry.ui.dialogs.BaseDialog;

public class AboutEpsilonDialog extends BaseDialog {
    public AboutEpsilonDialog(){
        super("@about.epsilon");
        addCloseButton();
        shouldPause = true;
    };
}