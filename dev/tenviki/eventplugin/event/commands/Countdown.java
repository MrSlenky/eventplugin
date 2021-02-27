/*    */ package dev.tenviki.eventplugin.event.commands;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.utils.Config;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitScheduler;
/*    */ 
/*    */ public class Countdown
/*    */ {
/*    */   private static Main plugin;
/*    */   
/*    */   public Countdown(Main plugin) {
/* 15 */     Countdown.plugin = plugin;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void countdown(int num) {
/* 20 */     BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
/*    */ 
/*    */     
/* 23 */     final int id = scheduler.scheduleSyncRepeatingTask((Plugin)plugin, new Runnable(num)
/*    */         {
/*    */           int secs;
/*    */           
/*    */           public void run() {
/* 28 */             Msg.broadcastRaw(Config.getString("event-countdown").replace("{s}", String.valueOf(this.secs)));
/* 29 */             this.secs--;
/*    */           }
/* 31 */         }0L, 20L);
/*    */     
/* 33 */     scheduler.scheduleSyncDelayedTask((Plugin)plugin, new Runnable()
/*    */         {
/*    */           
/*    */           public void run()
/*    */           {
/* 38 */             Bukkit.getScheduler().cancelTask(id);
/* 39 */             Msg.broadcast("event-countdown-end");
/*    */           }
/* 41 */         },  (num * 20));
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\event\commands\Countdown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */