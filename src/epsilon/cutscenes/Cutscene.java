package epsilon.cutscene;

import arc.struct.Seq;
import mindustry.gen.Building;

import static mindustry.Vars.ui;

public class Cutscene{
     public static CutsceneBus phaseCode(String code, Building source){
        CutsceneBus bus = new CutsceneBus();
        phaseLine(code).each(line -> bus.add(phaseCutscene(line, source)));
        return bus;
    }

    public static Seq<String> phaseLine(String code){
        String[] lines = code.split("\\R");
        return Seq.with(lines);
    }
    
    public static CutsceneBase phaseCutscene(String tokens, Building source){
         Seq<String> tokensArray = parseString(tokens);
         String actionName = tokensArray.remove(0);
         String[] args = tokensArray.toArray(String.class);
         try{
             return switch (actionName){
                 case "text" -> new TextShowup(args);

                 default -> new CutsceneNull();
             };
          }catch(Exception e){
            ui.announce("Failed to parse action: " + tokens);
            return new CutsceneNull();
         }
    }
}
