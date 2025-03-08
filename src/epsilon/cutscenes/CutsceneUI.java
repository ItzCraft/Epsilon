package epsilon.cutscenes;

import arc.scene.ui.layout.Table;
import arc.scene.ui.layout.WidgetGroup;
import mindustry.Vars;

public class CutsceneUI{
    public WidgetGroup overlay, curtain;
    public Table textArea;

    public final float speed = 0.0065f;

    public float overlaySpeed = speed;

    public void reset(){
        controlOverride = false;
        curtainProgress = 0;
        targetOverlayAlpha = 0;
        overlaySpeed = speed;

       overlay.clear();
       textArea.clear();
       markers.clear();
   }

    public void update(){
      if(Vars.headless){
          reset();
          return;
      }

      curtain.color.a = Mathf.approachDelta(curtain.color.a, targetOverlayAlpha, overlaySpeed);
    }
} 
