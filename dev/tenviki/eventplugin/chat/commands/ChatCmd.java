/*    */ package dev.tenviki.eventplugin.chat.commands;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.utils.Config;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ChatCmd
/*    */   implements CommandExecutor
/*    */ {
/*    */   private Main plugin;
/*    */   
/*    */   public ChatCmd(Main plugin) {
/* 17 */     this.plugin = plugin;
/* 18 */     plugin.getCommand("chat").setExecutor(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
/* 24 */     if (args.length >= 1) {
/*    */       
/* 26 */       if (args[0].equalsIgnoreCase("mute")) {
/*    */         
/* 28 */         if (hasPermission(s, "ep.chat.mute")) {
/* 29 */           ChatController.muteChat(s);
/*    */         
/*    */         }
/*    */       }
/* 33 */       else if (args[0].equalsIgnoreCase("clear")) {
/* 34 */         if (!hasPermission(s, "ep.chat.clear")) return true;
/*    */ 
/*    */         
/* 37 */         String name = "";
/*    */         
/* 39 */         if (s instanceof Player) {
/* 40 */           Player p = (Player)s;
/* 41 */           name = p.getDisplayName();
/*    */         } else {
/* 43 */           name = "Server";
/*    */         } 
/*    */         
/* 46 */         ChatController.clearChat();
/*    */         
/* 48 */         Msg.broadcastRaw(Config.getString("chat-clear").replace("{player}", name));
/*    */       }
/*    */       else {
/*    */         
/* 52 */         sendHelp(s);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 57 */       sendHelp(s);
/*    */     } 
/*    */     
/* 60 */     return true;
/*    */   }
/*    */   
/*    */   public void sendHelp(CommandSender s) {
/* 64 */     for (String msg : this.plugin.getConfig().getStringList("chat-help"))
/*    */     {
/* 66 */       Msg.sendRawToPlayer(s, msg);
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean hasPermission(CommandSender s, String msg) {
/* 71 */     if (s.hasPermission(msg)) return true; 
/* 72 */     Msg.sendToPlayer(s, "no-permission");
/*    */     
/* 74 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\chat\commands\ChatCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */