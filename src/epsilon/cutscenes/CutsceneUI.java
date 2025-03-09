package epsilon.cutscenes;

import arc.Core;
import arc.Events;
import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.scene.event.Touchable;
import arc.scene.ui.layout.Table;
import arc.scene.ui.layout.WidgetGroup;
import arc.math.Mathf;
import mindustry.gen.Tex;
import mindustry.Vars;
import mindustry.ui.Styles;
import mindustry.game.EventType;

public class CutsceneUI{
    public WidgetGroup overlay, curtain, root;
    public Table textArea, textTable;

    public final float speed = 0.0065f;
    public float curtainProgress = 0;
    public boolean controlOverride = false;
    public float targetOverlayAlpha;
    public float overlaySpeed = speed;

    public CutsceneUI(){
        init();
        Events.on(EventType.WorldLoadEvent.class, e -> resetSave());
    }

    public void init(){
        buildRoot();

        buildOverlay();

        buildTextTable();

        buildCutsceneUI();
    }

    private void buildRoot(){
        root = new WidgetGroup(){{
            setFillParent(true);
            touchable = Touchable.childrenOnly;
        }};
    } 

    private void buildOverlay(){
        overlay = new WidgetGroup(){{
            fillParent = true;
            touchable = Touchable.disabled;
        }};
    }

    private void buildTextTable(){
        textTable = new Table(Tex.buttonEdge3){{
            touchable(() -> {
                return Touchable.disabled;
            });
            visible(() -> Vars.state.isGame());

            if(Vars.headless){
                textArea = new Table();
                }else {
                    pane(Styles.smallPane, t -> {
                        textArea = t;
                        textArea.defaults().grow().pad(2f);
                        textArea.exited(() -> Core.scene.unfocus(textArea));
                        t.fillParent = true;
                    }).grow();
                }
            }};
    }

    private void buildCutsceneUI(){
        if(!Vars.headless){
            Vars.control.input.addLock(() -> controlOverride);
            Core.scene.root.addChildAt(0, root);
            root.addChild(overlay);
            root.addChild(textTable);
        }
    } 
    
    public void reset(){
        controlOverride = false;
        curtainProgress = 0;
        targetOverlayAlpha = 0;
        overlaySpeed = speed;

       overlay.clear();
       textArea.clear();
   }

    public void resetSave(){
        reset();
    }
    
    public void update(){
      if(Vars.headless){
          reset();
          return;
      }
    }
} 
