package epsilon;

import arc.ApplicationListener;
import mindustry.Vars;

import static epsilon.EpsilonVars.*;

public class EpsCore implements ApplicationListener{
    public EpsCore(){}

    @Override
    public void update(){
        if(Vars.state.isPlaying()){
            cutsceneControl.update();        
        }
    }
  
    @Override
    public void dispose() {
        ApplicationListener.super.dispose();
    }

    @Override
    public void init() {
        ApplicationListener.super.init();
    }
}
