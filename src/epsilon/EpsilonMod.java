package epsilon;

import arc.*;
import arc.util.*;
import epsilon.content.Kallistea.blocks.KallisteaBlocks;
import epsilon.content.Kallistea.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class EpsilonMod extends Mod{

    public EpsilonMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
          Events.on(ClientLoadEvent.class, e -> {
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Epsilon");
                dialog.cont.add(Core.bundle.get("warn-text1")).row();
dialog.cont.image(Core.atlas.find("epsilon-mod-frog")).pad(20f).row();
                dialog.cont.button("OK", dialog::hide).size(100f, 50f);
                dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
        KallisteaItems.load();
        KallisteaBlocks.load();
    }

}
