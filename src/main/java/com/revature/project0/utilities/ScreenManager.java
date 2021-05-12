package com.revature.project0.utilities;

import com.revature.project0.screens.*;
import exceptions.IllegalInputException;

public class ScreenManager {

    private MyList<Screen> screens;

    public ScreenManager(MyList<Screen> screens)
    {
        this.screens = screens;
    }

    public void navigate(String identifier) throws IllegalInputException
    {
        for (Screen screen : screens) {
            if (screen.getIdentifier().equals(identifier))
            {
                screen.render();
            }
        }
    }


}
