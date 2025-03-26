package epsilon;

import arc.audio.Music;
import arc.scene.ui.layout.Scl;
import arc.util.Nullable;
import epsilon.world.blocks.storage.LaunchAnimator;
import mindustry.core.Renderer;

import static arc.Core.settings;
import static mindustry.Vars.control;

public class EpsRenderer extends Renderer {
    private @Nullable LaunchAnimator launchAnimator;
    private boolean launching;
    private float landTime, targetscale = Scl.scl(4), camerascale = targetscale;

    public EpsRenderer(){
           
    }

    public static float getLandTimeIn(){
        if(launchAnimator == null) return 0f;
        float fin = landTime / launchAnimator.launchDuration();
        if(!launching) fin = 1f - fin;
        return fin;
    }

    public void showLanding(LaunchAnimator landCore){
        this.launchAnimator = landCore;
        launching = false;
        landTime = landCore.launchDuration();

        landCore.beginLaunch(false);
        camerascale = landCore.zoomLaunch();
    }

    public void showLaunch(LaunchAnimator landCore){
        control.input.config.hideConfig();
        control.input.inv.hide();

        this.launchAnimator = landCore;
        launching = true;
        landTime = landCore.launchDuration();

        Music music = landCore.launchMusic();
        music.stop();
        music.play();
        music.setVolume(settings.getInt("musicvol") / 100f);

        landCore.beginLaunch(true);
    }
}