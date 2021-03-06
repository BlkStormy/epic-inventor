package com.weem.epicinventor.placeable;

import com.weem.epicinventor.*;

public class IronMine extends Building {
    
    private static final long serialVersionUID = 10000L;
    transient private final static int generationRate = 60 * 6; //in seconds
    private long generationTimerEnd;
    
    public IronMine(PlaceableManager pm, Registry rg, String sm, String am, int x, int y, Placeable.State cs) {
        super(pm, rg, sm, am, x, y, cs);

        type = "IronMine";
        
        totalBuildTime = 60;
        totalHitPoints  = 600;
        powerRequired = 10;
        powerGenerated = 0;
        
        hitPoints = totalHitPoints;
    }
    
    @Override
    public void setTransient(Registry rg) {
        totalHitPoints  = 600;
        super.setTransient(rg);
    }
    
    private void resetGenerationTimer() {
        generationTimerEnd = System.currentTimeMillis() + (generationRate * 1000);
    }

    @Override
    public void updateLong() {
        super.updateLong();
        
        if(generationTimerEnd == 0) {
            resetGenerationTimer();
        }
        
        if(generationTimerEnd <= System.currentTimeMillis() && this.isActivated()) {
            placeableManager.playerAddItem("Iron", 1);
            registry.getIndicatorManager().createImageIndicator(mapX + (width / 2), mapY + height + 32, "Iron");
            registry.showMessage("Success", type + " has generated Iron");
            resetGenerationTimer();
        }
    }
}
