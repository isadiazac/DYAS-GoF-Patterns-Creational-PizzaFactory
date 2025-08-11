package edu.unisabana.pizzafactory.model;

import java.util.logging.Level;
import java.util.logging.Logger;

// Añadimos "implements Amasador"
public class AmasadorPizzaDelgada implements Amasador {
    
    @Override // Buena práctica añadir @Override
    public void amasar() {
        Logger.getLogger(AmasadorPizzaDelgada.class.getName())
                .log(Level.INFO, "[@@] Amasando la pizza delgada con masa convencional.");
    }
}