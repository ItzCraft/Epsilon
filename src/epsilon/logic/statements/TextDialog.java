package epsilon.logic.statements;

import arc.Core;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.TextField;
import arc.scene.ui.layout.Table;
import epsilon.logic.EpsilonLogic;
import epsilon.logic.instructions.TextDialogI;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.logic.*;
import mindustry.type.UnitType;
import mindustry.ui.Styles;

import static mindustry.Vars.iconSmall;

public class TextDialog extends LStatement{
    public String text = ":3", unit = "@dagger";
    public String duration = "5";
    public String useBundles = "true";

    public TextDialog(String[] tokens){
        text = tokens[1];
        unit = tokens[2];
        duration = tokens[3];
        useBundles = tokens[4];
    }
    public TextDialog(){}

    @Override
    public void build(Table table){
        table.add(" text ");

        fields(table, text, v -> text = v);

        table.add(" unit ");

        TextField field = field(table, unit, str -> unit = str).get();

        table.button(b -> {
            b.image(Icon.pencilSmall);
            b.clicked(() -> showSelectTable(b, (t, hide) -> {
                t.row();
                t.table(i -> {
                    i.left();
                    int c = 0;
                    for(UnitType item : Vars.content.units()){
                        if(!item.unlockedNow() || item.isHidden() || !item.logicControllable) continue;
                        i.button(new TextureRegionDrawable(item.uiIcon), Styles.flati, iconSmall, () -> {
                            unit = "@" + item.name;
                            field.setText(unit);
                            hide.run();
                        }).size(40f);

                        if(++c % 6 == 0) i.row();
                    }
                }).colspan(3).width(240f).left();
            }));
        }, Styles.logict, () -> {}).size(40f).padLeft(-2).color(table.color);

        table.add(" duration ");

        fields(table, duration, c -> duration = c);

        table.add(" use Bundles ");

        fields(table, useBundles, g -> useBundles = g);
    }

    @Override
    public boolean privileged() {
        return true;
    }

    @Override
    public LExecutor.LInstruction build(LAssembler builder) {
        return new TextDialogI(builder.var(text), builder.var(unit), builder.var(duration), builder.var(useBundles));
    }

    @Override
    public LCategory category() {
        return EpsilonLogic.epsilonCategory;
    }

    public void write(StringBuilder builder){
        builder.append("textdialog");
        builder.append(" ");
        builder.append(text);
        builder.append(" ");
        builder.append(unit);
        builder.append(" ");
        builder.append(duration);
        builder.append(" ");
        builder.append(useBundles);
    }
}
