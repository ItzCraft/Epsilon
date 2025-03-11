package epsilon.ui;

import arc.scene.Element;
import arc.scene.Group;
import arc.scene.ui.ImageButton;
import arc.scene.ui.layout.Table;
import mindustry.game.Team;
import mindustry.gen.Tex;
import mindustry.ui.Styles;

import static arc.Core.*;
import static mindustry.Vars.*;
import static mindustry.Vars.ui;

public class TeamsUI{
    public static void init(){
        TeamsUIInit();
    }

    static void TeamsUIInit() {
        ui.editor.shown(() -> {
            //Some code taked from FOS because i'm dumb :/
            Element teambuttons = ui.editor.getChildren().get(0);
            teambuttons = ((Group)teambuttons).getChildren().get(0);
            teambuttons = ((Group)teambuttons).getChildren().get(0);

            ((Table)teambuttons).row();

            for (int i = 42; i <= 43; i++) {
                Team team = Team.get(i);

                ImageButton button = new ImageButton(Tex.whiteui, Styles.clearNoneTogglei);
                button.margin(5f);
                button.getImageCell().grow();
                button.getStyle().imageUpColor = team.color;
                button.clicked(() -> editor.drawTeam = team);
                button.update(() -> button.setChecked(editor.drawTeam == team));

                ((Table)teambuttons).add(button);
            }
        });
    }
}