package epsilon.tools;

import arc.files.Fi;
import arc.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BundleUpdater {

    private static final String BUNDLES_DIR = "assets/bundles";
    private static final String BASE_NAME   = "bundle.properties";

    private static final class Token {
        enum Type { COMMENT, PROPERTY }
        final Type type;
        final String textOrKey;
        Token(Type type, String textOrKey){ this.type = type; this.textOrKey = textOrKey; }
        static Token comment(String line){ return new Token(Type.COMMENT, line); }
        static Token property(String key){ return new Token(Type.PROPERTY, key); }
    }

    public static void main(String[] args){
        new BundleUpdater().update();
    }

    public void update(){
        Fi dir = new Fi(BUNDLES_DIR);
        Fi baseFile = dir.child(BASE_NAME);
        if(!baseFile.exists()){
            Log.err("Not found: @", baseFile.path());
            return;
        }

        try{

            List<Token> layout = parseBaseLayout(baseFile);
            LinkedHashMap<String, String> base = loadProps(baseFile);

            Log.info(">>> Bundle update in @", dir.path());
            dir.walk(child -> {
                String name = child.name();
                if(!name.endsWith(".properties")) return;
                if(name.equals(BASE_NAME)) return;
                if(name.contains("output")) return;

                try{
                    LinkedHashMap<String, String> other = loadProps(child);


                    int removed = 0;
                    Iterator<String> it = other.keySet().iterator();
                    while(it.hasNext()){
                        String k = it.next();
                        if(!base.containsKey(k)){
                            it.remove();
                            removed++;
                        }
                    }


                    int added = 0, filled = 0;
                    for(Map.Entry<String, String> e : base.entrySet()){
                        String k = e.getKey();
                        String v = e.getValue();
                        if(!other.containsKey(k)){
                            other.put(k, v);
                            added++;
                        }else if(other.get(k) == null || other.get(k).trim().isEmpty()){
                            other.put(k, v);
                            filled++;
                        }
                    }


                    StringBuilder out = new StringBuilder();
                    for(Token t : layout){
                        if(t.type == Token.Type.COMMENT){
                            out.append(t.textOrKey).append("\n");
                        }else{
                            String key = t.textOrKey;
                            String val = other.get(key);
                            if(val == null) continue;
                            out.append(key).append(" = ").append(escape(val)).append("\n");

                            other.remove(key);
                        }
                    }


                    child.writeString(out.toString());
                    Log.info("✔ @: +@ added, ~@ auto, -@ deleted",
                            name, added, filled, removed);
                }catch(Exception ex){
                    Log.err("error in @: @", name, ex.getMessage());
                }
            });

            Log.info("Done.");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private static LinkedHashMap<String, String> loadProps(Fi file) throws IOException{
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try(BufferedReader br = new BufferedReader(file.reader())){
            String line;
            while((line = br.readLine()) != null){
                String trimmed = line.trim();
                if(trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith("!")) continue;

                int eq = indexOfAssign(line);
                if(eq < 0) continue;
                String key = line.substring(0, eq).trim();
                String val = line.substring(eq + 1).trim();

                if(!key.isEmpty()){
                    map.put(key, val);
                }
            }
        }
        return map;
    }

    private static List<Token> parseBaseLayout(Fi base) throws IOException{
        List<Token> tokens = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(base.reader())){
            String line;
            while((line = br.readLine()) != null){
                String trimmed = line.trim();
                if(trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith("!")){
                    tokens.add(Token.comment(line));
                    continue;
                }
                int eq = indexOfAssign(line);
                if(eq < 0){
                    tokens.add(Token.comment(line));
                    continue;
                }
                String key = line.substring(0, eq).trim();
                if(key.isEmpty()){
                    tokens.add(Token.comment(line));
                }else{
                    tokens.add(Token.property(key));
                }
            }
        }
        return tokens;
    }

    private static int indexOfAssign(String line){
        int eq = line.indexOf('=');
        int colon = line.indexOf(':');
        if(eq < 0) return colon;
        if(colon < 0) return eq;
        return Math.min(eq, colon);
    }

    private static String escape(String s){
        if(s == null) return "";
        StringBuilder out = new StringBuilder(s.length() + 16);
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            switch(ch){
                case '\\': out.append("\\\\"); break;
                case '\n': out.append("\\n"); break;
                case '\r': out.append("\\r"); break;
                case '\t': out.append("\\t"); break;
                default:
                    if(ch >= 0xE000 && ch <= 0xF8FF){
                        out.append(String.format("\\u%04x", (int) ch));
                    }else{
                        out.append(ch);
                    }
            }
        }
        return out.toString();
    }
}