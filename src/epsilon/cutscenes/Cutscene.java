package epsilon.cutscenes;

import arc.struct.Seq;
import mindustry.gen.Building;
import mindustry.world.blocks.logic.MemoryBlock;
import epsilon.cutscenes.TextShowup;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static mindustry.Vars.ui;

public class Cutscene{
     public static CutsceneBus phaseCode(String code, Building source){
        CutsceneBus bus = new CutsceneBus();
        phaseLine(code).each(line -> bus.add(phaseCutscene(line, source)));
        return bus;
    }

    public static Seq<String> parseString(String line){
        Seq<String> result = new Seq<>();
        Matcher matcher = Pattern.compile("<([^>]*)>|\\S+").matcher(line);
        while (matcher.find()) {
            result.add(matcher.group(1) != null ? matcher.group(1) : matcher.group());
        }
        return result;
    };

    public static String phaseString(String token){
        return token.replace("[n]", "\n");
    }
     
    public static Seq<String> phaseLine(String code){
        String[] lines = code.split("\\R");
        return Seq.with(lines);
    }

    public static float parseFloat(String token, Building source) {
        if (token.startsWith("@") && source != null && world.build(source.tileX(), source.tileY() - 1).block instanceof MemoryBlock){
            MemoryBlock.MemoryBuild memory = (MemoryBlock.MemoryBuild)world.build(source.tileX(), source.tileY() - 1);
            return (float) memory.memory[Integer.parseInt(token.replace("@", ""))];
        }else return Float.parseFloat(token);
    }
    
    public static CutsceneBase phaseCutscene(String tokens, Building source){
         Seq<String> tokensArray = parseString(tokens);
         String actionName = tokensArray.remove(0);
         String[] args = tokensArray.toArray(String.class);
         try{
             return switch (actionName){
                 case "text_dialog" -> new TextShowup(args);

                 default -> new CutsceneNull();
             };
          }catch(Exception e){
            ui.announce("Failed to parse action: " + tokens);
            return new CutsceneNull();
         }
    }
}
