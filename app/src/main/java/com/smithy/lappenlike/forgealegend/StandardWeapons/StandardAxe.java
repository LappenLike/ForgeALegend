package com.smithy.lappenlike.forgealegend.StandardWeapons;

public class StandardAxe {

   public static String name = "Axe";
   public static double weight = 1.0;
   public static int damageMin = 30;
   public static int damageMax = 40;
   public static double speed = 0.0;
   public static double crit = 10.0;
//Noma
   public static String getDamageDescription(){
       return damageMin+" - "+damageMin;
   }
}
