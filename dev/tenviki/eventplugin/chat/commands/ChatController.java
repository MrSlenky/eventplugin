/*    */ package dev.tenviki.eventplugin.chat.commands;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.utils.Config;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class ChatController
/*    */ {
/*    */   private static Main plugin;
/*    */   
/*    */   public ChatController(Main plugin) {
/* 16 */     ChatController.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void muteChat(CommandSender s) {
/* 21 */     plugin.chatMuted = !plugin.chatMuted;
/*    */     
/* 23 */     String name = "";
/*    */     
/* 25 */     if (s instanceof Player) {
/* 26 */       Player p = (Player)s;
/* 27 */       name = p.getDisplayName();
/*    */     } else {
/* 29 */       name = "Server";
/*    */     } 
/*    */     
/* 32 */     if (plugin.chatMuted) {
/* 33 */       Msg.broadcastRaw(Config.getString("chat-mute").replace("{player}", name));
/*    */     } else {
/* 35 */       Msg.broadcastRaw(Config.getString("chat-unmute").replace("{player}", name));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void clearChat() {
/* 41 */     for (Player p : Bukkit.getOnlinePlayers()) {
/*    */       
/* 43 */       for (int i = 0; i < 150; i++)
/* 44 */         p.sendMessage(" "); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\chat\commands\ChatController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */