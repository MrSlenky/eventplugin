/*    */ package dev.tenviki.eventplugin.event.listeners;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class IntroListener
/*    */   implements Listener {
/*    */   public IntroListener(Main plugin) {
/* 13 */     this.plugin = plugin;
/* 14 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   private Main plugin;
/*    */   @EventHandler
/*    */   public void onEvent(PlayerMoveEvent e) {
/* 19 */     if (!this.plugin.intro.booleanValue())
/* 20 */       return;  if (e.getPlayer().hasPermission("\"ep.event.start\""))
/*    */       return; 
/* 22 */     e.setCancelled(true);
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\event\listeners\IntroListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */