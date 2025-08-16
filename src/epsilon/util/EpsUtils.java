package epsilon.util;

import arc.util.*;

public class EpsUtils{
    public void wait(int time){
        while (time <= 0){
            time -= Time.delta;
        }
    }
}