package epsilon.cutscene;

import arc.struct.Seq;

import static mindustry.Vars.ui;

public class Cutscene{
    /*public static phaseCutscene(String tokens){
         Seq<String> tokensArray = parseString(tokens);
         String actionName = tokensArray.remove(0);
         String[] args = tokensArray.toArray(String.class);
         try{
             return switch(actionName){
                 case "text" -> new TextShowup(args);

                 default -> new CutsceneNull();
             };
          }catch(Exception e){
            ui.announce("Failed to parse action: " + tokens);
            return new CutsceneNull();
         }
    } */
}
