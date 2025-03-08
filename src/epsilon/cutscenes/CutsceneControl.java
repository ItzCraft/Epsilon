package epsilon.cutscenes;

import arc.struct.Seq;

public class CutsceneControl{
    public Seq<CutsceneBus> cutsceneBuses = new Seq<>();

    public static void addCutsceneBus(CutsceneBus bus){
        cutsceneBuses.add(bus);
    }
}
