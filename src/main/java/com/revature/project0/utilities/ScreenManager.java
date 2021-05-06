package com.revature.project0.utilities;

import com.revature.project0.screens.Screen;
import com.revature.project0.screens.StartScreen;

public class ScreenManager {

    private MyList<Screen> screens;
    private static ScreenManager instance;

    private ScreenManager()
    {
        screens = new MyList<>();
        screens.add(StartScreen.getInstance());
    }

    public static ScreenManager getInstance()
    {
        if (instance == null)
        {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void navigate(String identifier)
    {
        for (Screen screen : screens) {
            if (screen.getIdentifier().equals(identifier))
            {
                screen.render();
            }
        }
    }


}
