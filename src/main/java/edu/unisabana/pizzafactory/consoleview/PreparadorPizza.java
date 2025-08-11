package edu.unisabana.pizzafactory.consoleview;

// Importa las nuevas interfaces y fábricas
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.unisabana.pizzafactory.factory.PizzaDelgadaFactory;
import edu.unisabana.pizzafactory.factory.PizzaFactory;
import edu.unisabana.pizzafactory.factory.PizzaGruesaFactory;
import edu.unisabana.pizzafactory.factory.PizzaIntegralFactory;
import edu.unisabana.pizzafactory.model.Amasador;
import edu.unisabana.pizzafactory.model.ExcepcionParametrosInvalidos;
import edu.unisabana.pizzafactory.model.Horneador;
import edu.unisabana.pizzafactory.model.Ingrediente;
import edu.unisabana.pizzafactory.model.Moldeador;
import edu.unisabana.pizzafactory.model.Tamano;

public class PreparadorPizza {

    public static void main(String args[]){
        try {
            Ingrediente[] ingredientes = new Ingrediente[]{new Ingrediente("queso", 1), new Ingrediente("jamon", 2)};
            
            // --- ¡AQUÍ ESTÁ LA MAGIA! ---
            // Para preparar una pizza delgada:
            System.out.println("--- Preparando Pizza Delgada ---");
            PizzaFactory fabricaDelgada = new PizzaDelgadaFactory();
            prepararPizza(fabricaDelgada, ingredientes, Tamano.MEDIANO);
            
            System.out.println("\n");
            
            // Para preparar una pizza gruesa, solo cambiamos la fábrica:
            System.out.println("--- Preparando Pizza Gruesa ---");
            PizzaFactory fabricaGruesa = new PizzaGruesaFactory();
            prepararPizza(fabricaGruesa, ingredientes, Tamano.PEQUENO);

            System.out.println("\n");

            // --- Preparando Pizza Integral ---
            System.out.println("--- Preparando Pizza Integral ---");
            PizzaFactory fabricaIntegral = new PizzaIntegralFactory();
            prepararPizza(fabricaIntegral, ingredientes, Tamano.MEDIANO);

        } catch (ExcepcionParametrosInvalidos ex) {
            Logger.getLogger(PreparadorPizza.class.getName()).log(Level.SEVERE, "Problema en la preparacion de la Pizza", ex);
        }
    }
    
    // El método ahora recibe una fábrica y depende de las interfaces, no de clases concretas
    public static void prepararPizza(PizzaFactory factory, Ingrediente[] ingredientes, Tamano tam)
            throws ExcepcionParametrosInvalidos {
        
        Amasador am = factory.crearAmasador();
        Horneador hpd = factory.crearHorneador();
        Moldeador mp = factory.crearMoldeador();

        am.amasar();

        if (tam == Tamano.PEQUENO) {
            mp.moldearPizzaPequena();
        } else if (tam == Tamano.MEDIANO) {
            // Usamos el nombre corregido del método
            mp.moldearPizzaMediana();
        } else {
            throw new ExcepcionParametrosInvalidos("Tamano de pizza invalido:" + tam);
        }
        
        aplicarIngredientes(ingredientes);
        hpd.hornear();
    }

    private static void aplicarIngredientes(Ingrediente[] ingredientes) {
        Logger.getLogger(PreparadorPizza.class.getName())
                .log(Level.INFO, "APLICANDO INGREDIENTES!: {0}", Arrays.toString(ingredientes));
    }
}