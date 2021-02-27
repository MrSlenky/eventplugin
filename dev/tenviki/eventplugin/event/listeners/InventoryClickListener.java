/*    */ package dev.tenviki.eventplugin.event.listeners;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.event.EventUI;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.bukkit.event.player.PlayerQuitEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class InventoryClickListener implements Listener {
/*    */   private Main plugin;
/*    */   
/*    */   public InventoryClickListener(Main plugin) {
/* 17 */     this.plugin = plugin;
/* 18 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onClick(InventoryClickEvent e) {
/* 23 */     if (e.getInventory() != EventUI.inv)
/*    */       return; 
/* 25 */     e.setCancelled(true);
/*    */     
/* 27 */     if (e.getCurrentItem() != null) {
/* 28 */       EventUI.clicked((Player)e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
/*    */     }
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void onLeave(PlayerQuitEvent e) {
/* 34 */     Player p = e.getPlayer();
/*    */     
/* 36 */     if (this.plugin.alreadyRated.contains(p))
/* 37 */       this.plugin.alreadyRated.remove(p); 
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\event\listeners\InventoryClickListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */