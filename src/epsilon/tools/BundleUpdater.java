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
        final String textOrKey; // для COMMENT: оригинальная строка; для PROPERTY: ключ
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
            Log.err("Не найден английский файл: @", baseFile.path());
            return;
        }

        try{
            // Парсим английский как эталон: порядок ключей + комментарии/пустые строки
            List<Token> layout = parseBaseLayout(baseFile);
            LinkedHashMap<String, String> base = loadProps(baseFile);

            Log.info(">>> Обновление bundles в @", dir.path());
            dir.walk(child -> {
                String name = child.name();
                if(!name.endsWith(".properties")) return;
                if(name.equals(BASE_NAME)) return;             // пропускаем английский
                if(name.contains("output")) return;            // пропускаем временные

                try{
                    LinkedHashMap<String, String> other = loadProps(child);

                    // Удаляем ключи, которых нет в базе
                    int removed = 0;
                    Iterator<String> it = other.keySet().iterator();
                    while(it.hasNext()){
                        String k = it.next();
                        if(!base.containsKey(k)){
                            it.remove();
                            removed++;
                        }
                    }

                    // Добавляем недостающие/пустые ключи из базы
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

                    // Перезаписываем файл по макету английского (комментарии/пустые строки сохраняем)
                    StringBuilder out = new StringBuilder();
                    for(Token t : layout){
                        if(t.type == Token.Type.COMMENT){
                            out.append(t.textOrKey).append("\n");
                        }else{
                            String key = t.textOrKey;
                            String val = other.get(key);
                            if(val == null) continue; // теоретически не должно случиться
                            out.append(key).append(" = ").append(escape(val)).append("\n");
                            // удаляем, чтобы видно было «лишние» в конце (мы их не пишем)
                            other.remove(key);
                        }
                    }
                    // Незапланированных ключей (не из базы) быть не должно — мы их удалили выше.

                    child.writeString(out.toString());
                    Log.info("✔ @: +@ добавлено, ~@ автозаполнено, -@ удалено",
                            name, added, filled, removed);
                }catch(Exception ex){
                    Log.err("Ошибка при обработке @: @", name, ex.getMessage());
                }
            });

            Log.info("Готово.");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    /** Загружаем .properties в LinkedHashMap (с сохранением порядка). */
    private static LinkedHashMap<String, String> loadProps(Fi file) throws IOException{
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        try(BufferedReader br = new BufferedReader(file.reader())){
            String line;
            while((line = br.readLine()) != null){
                // Пропускаем комментарии и пустые
                String trimmed = line.trim();
                if(trimmed.isEmpty() || trimmed.startsWith("#") || trimmed.startsWith("!")) continue;

                // Простое разделение по первому '=' (или ':')
                int eq = indexOfAssign(line);
                if(eq < 0) continue;
                String key = line.substring(0, eq).trim();
                String val = line.substring(eq + 1).trim();

                // Не разворачиваем \n -> оставляем как есть; при записи снова экранируем
                if(!key.isEmpty()){
                    map.put(key, val);
                }
            }
        }
        return map;
    }

    /** Размечаем английский бандл: комментарии/пустые и порядок ключей. */
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
                    // строка без '=' — трактуем как комментарий, чтобы не терять
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

    /** Минимальное экранирование под .properties. */
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
                    // при желании — экранировать приватную зону Юникода \uE000–\uF8FF
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