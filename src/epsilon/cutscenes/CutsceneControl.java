package epsilon.cutscenes;

import arc.struct.Seq;

public class CutsceneControl{
    public Seq<CutsceneBus> cutsceneBuses = new Seq<>();

    public void addCutsceneBus(CutsceneBus bus){
        cutsceneBuses.add(bus);
    }
}
