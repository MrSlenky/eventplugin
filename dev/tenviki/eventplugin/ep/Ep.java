/*    */ package dev.tenviki.eventplugin.ep;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import java.io.IOException;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Ep
/*    */   implements CommandExecutor
/*    */ {
/*    */   private Main plugin;
/*    */   
/*    */   public Ep(Main plugin) {
/* 18 */     this.plugin = plugin;
/* 19 */     plugin.getCommand("ep").setExecutor(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
/* 25 */     if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
/* 26 */       sendHelp(s);
/* 27 */       return true;
/*    */     } 
/*    */     
/* 30 */     if (args[0].equalsIgnoreCase("reload")) {
/* 31 */       if (!hasPermission(s, "ep.admin.reload")) return true;
/*    */       
/*    */       try {
/* 34 */         this.plugin.getMsgConfig().load(this.plugin.msgConfigFile);
/* 35 */         this.plugin.getEventConfig().load(this.plugin.eventConfigFile);
/*    */       }
/* 37 */       catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/*    */ 
/*    */         
/* 40 */         Msg.sendRawToPlayer(s, "Could not load custom configs. Check console for details.");
/* 41 */         e.printStackTrace();
/* 42 */         return true;
/*    */       } 
/* 44 */       this.plugin.reloadConfig();
/* 45 */       Msg.sendToPlayer(s, "config-reload");
/*    */     } 
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void sendHelp(CommandSender s) {
/* 54 */     for (String msg : this.plugin.getConfig().getStringList("help"))
/*    */     {
/* 56 */       Msg.sendRawToPlayer(s, msg.replace("{v}", this.plugin.pluginVersion));
/*    */     }
/*    */   }
/*    */   
/*    */   public boolean hasPermission(CommandSender s, String msg) {
/* 61 */     if (s.hasPermission(msg)) return true; 
/* 62 */     Msg.sendToPlayer(s, "no-permission");
/*    */     
/* 64 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\ep\Ep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */