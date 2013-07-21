package com.karoshi.androidgames.ponkout;

import com.karoshi.androidgames.framework.Screen;
import com.karoshi.androidgames.framework.impl.AndroidGame;

public class PonkoutGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen(this); 
    }
}