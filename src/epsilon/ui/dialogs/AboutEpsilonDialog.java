package epsilon.ui.dialogs;

import arc.Core;
import arc.scene.ui.layout.Table;
import mindustry.gen.Tex;
import mindustry.ui.*;
import mindustry.ui.dialogs.BaseDialog;

import static arc.input.KeyCode.t;

public class AboutEpsilonDialog extends BaseDialog {

    public BaseDialog EpsDataBase;

    public AboutEpsilonDialog(){
        super("@about.epsilon");
        addCloseButton();
        shouldPause = true;
        Table table = new Table();
        table.button(Core.bundle.get("@bebe"), () -> {
            table.add(Core.bundle.get("@bebebe"));
        });

    };
}
