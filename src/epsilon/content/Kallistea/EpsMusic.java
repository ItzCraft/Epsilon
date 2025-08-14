package epsilon.content.Kallistea;

import arc.audio.*;
import arc.struct.*;
import arc.util.Log;
import mindustry.Vars;

//CODE FROM OMALOON
public class EpsMusic{
    private static final ObjectMap<String, Seq<Music>> musicSets = new ObjectMap<>();
    public static Music crashLand;
    public static Sound scan;

    public static Music
        inTheSecrets;

    public static void load(){
        initializeMusics();
        initializeMusicSets();
        scan = Vars.tree.loadSound("scan");
        crashLand = loadMusic("crash");
    }

    private static void initializeMusics(){

        String[] sounds = {"crashLand"};
        String[] ambientTracks = {"inTheSecrets"};
        //String[] darkTracks = {};
        //String[] bossTracks = {};

        loadMusicSet("epsilon/sounds/", sounds);
        loadMusicSet("epsilon/ambient/", ambientTracks);
        //loadMusicSet("epsilon/dark/", darkTracks);
        //loadMusicSet("epsilon/boss/", bossTracks);
    }

    private static void loadMusicSet(String basePath, String[] trackNames){
        for(String track : trackNames){
            try{
                Music music = loadMusic(basePath + track);
                EpsMusic.class.getField(track).set(null, music);
            }catch(Exception e){
                Log.err("Failed to load music: " + track, e);
            }
        }
    }

    private static void initializeMusicSets(){
        musicSets.put("vanillaAmbient", new Seq<>(Vars.control.sound.ambientMusic));
        musicSets.put("vanillaDark", new Seq<>(Vars.control.sound.darkMusic));
        musicSets.put("vanillaBoss", new Seq<>(Vars.control.sound.bossMusic));

        musicSets.put("epsilonAmbient", Seq.with(inTheSecrets));
    }

    private static Music loadMusic(String name){
        return Vars.tree.loadMusic(name);
    }
}