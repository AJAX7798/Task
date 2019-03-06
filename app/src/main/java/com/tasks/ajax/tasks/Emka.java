package com.tasks.ajax.tasks;

import android.app.Application;
import android.os.Bundle;

/**
 * Created by AsaRayan on 30/01/2019.
 */

public class Emka extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/IRANSansMobile.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/IRANSansMobile.ttf");
    }
}
