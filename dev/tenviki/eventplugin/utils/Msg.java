/*    */ package dev.tenviki.eventplugin.utils;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import net.md_5.bungee.api.ChatColor;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class Msg
/*    */ {
/*    */   public static Main plugin;
/*    */   
/*    */   public Msg(Main plugin) {
/* 14 */     Msg.plugin = plugin;
/*    */   }
/*    */   
/*    */   public static void broadcast(String config) {
/* 18 */     String msg = ChatColor.translateAlternateColorCodes('&', Config.getString(config));
/* 19 */     Bukkit.broadcastMessage(msg);
/*    */   }
/*    */   
/*    */   public static void broadcastRaw(String msg) {
/* 23 */     msg = ChatColor.translateAlternateColorCodes('&', msg);
/* 24 */     Bukkit.broadcastMessage(msg);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void sendToPlayer(Player p, String config) {
/* 29 */     String msg = ChatColor.translateAlternateColorCodes('&', Config.getString(config));
/* 30 */     p.sendMessage(msg);
/*    */   }
/*    */   
/*    */   public static void sendToPlayer(CommandSender p, String config) {
/* 34 */     String msg = ChatColor.translateAlternateColorCodes('&', Config.getString(config));
/* 35 */     p.sendMessage(msg);
/*    */   }
/*    */   
/*    */   public static void sendRawToPlayer(Player p, String msg) {
/* 39 */     msg = ChatColor.translateAlternateColorCodes('&', msg);
/* 40 */     p.sendMessage(msg);
/*    */   }
/*    */   
/*    */   public static void sendRawToPlayer(CommandSender p, String msg) {
/* 44 */     msg = ChatColor.translateAlternateColorCodes('&', msg);
/* 45 */     p.sendMessage(msg);
/*    */   }
/*    */   
/*    */   public static String clr(String s) {
/* 49 */     return ChatColor.translateAlternateColorCodes('&', s);
/*    */   }
/*    */   
/*    */   public static void sendConsole(String msg) {
/* 53 */     msg = ChatColor.translateAlternateColorCodes('&', msg);
/* 54 */     plugin.getServer().getConsoleSender().sendMessage(msg);
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugi\\utils\Msg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */