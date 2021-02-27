/*    */ package dev.tenviki.eventplugin.blocker;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.utils.Config;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import java.util.List;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ public class BlockerListener
/*    */   implements Listener
/*    */ {
/*    */   private Main plugin;
/*    */   
/*    */   public BlockerListener(Main plugin) {
/* 21 */     this.plugin = plugin;
/* 22 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void onJoin(AsyncPlayerPreLoginEvent e) {
/* 28 */     List<String> blockedNames = BlockerController.getBlockedNames();
/*    */     
/* 30 */     boolean disabled = false;
/*    */     
/* 32 */     if (blockedNames.contains(e.getName())) {
/* 33 */       e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, Msg.clr(Config.getString("bedrock-squad-not-allowed")));
/* 34 */       disabled = true;
/*    */     } 
/*    */     
/* 37 */     List<String> blockedIPs = BlockerController.getBlockedIPs();
/* 38 */     String ip = e.getAddress().toString();
/*    */     
/* 40 */     ip = ip.substring(1, ip.length());
/*    */     
/* 42 */     if (blockedIPs.contains(ip)) {
/* 43 */       e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, Msg.clr(Config.getString("bedrock-squad-not-allowed")));
/* 44 */       disabled = true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 49 */     if (disabled)
/* 50 */       for (Player p : Bukkit.getOnlinePlayers()) {
/* 51 */         if (!p.hasPermission("ep.bedrocksquad.notify"))
/*    */           continue; 
/* 53 */         p.sendMessage(Msg.clr(
/* 54 */               Config.getString("bedrock-squad-try-to-connect")
/* 55 */               .replace("{bsmembername}", e.getName())
/* 56 */               .replace("{bsmemberip}", ip)));
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\blocker\BlockerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */