/*    */ package dev.tenviki.eventplugin.utils;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ 
/*    */ public class Config {
/*    */   public static Main plugin;
/*    */   
/*    */   public Config(Main plugin) {
/*  9 */     Config.plugin = plugin;
/*    */   }
/*    */   
/*    */   public static String getString(String s) {
/* 13 */     return plugin.getConfig().getString(s);
/*    */   }
/*    */   
/*    */   public static int getInt(String s) {
/* 17 */     return plugin.getConfig().getInt(s);
/*    */   }
/*    */   
/*    */   public static boolean getBool(String s) {
/* 21 */     return plugin.getConfig().getBoolean(s);
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugi\\utils\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */