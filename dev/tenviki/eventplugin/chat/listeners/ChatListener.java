/*     */ package dev.tenviki.eventplugin.chat.listeners;
/*     */ 
/*     */ import dev.tenviki.eventplugin.Main;
/*     */ import dev.tenviki.eventplugin.event.EventUI;
/*     */ import dev.tenviki.eventplugin.utils.Config;
/*     */ import dev.tenviki.eventplugin.utils.Msg;
/*     */ import java.io.IOException;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*     */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class ChatListener
/*     */   implements Listener {
/*     */   private Main plugin;
/*     */   
/*     */   public ChatListener(Main plugin) {
/*  21 */     this.plugin = plugin;
/*  22 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)plugin);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onChat(AsyncPlayerChatEvent e) {
/*  27 */     Player p = e.getPlayer();
/*     */     
/*  29 */     if (this.plugin.currentlyEditing && this.plugin.playerEditing == p) {
/*  30 */       String newVal = e.getMessage();
/*  31 */       String editID = this.plugin.editID;
/*     */       
/*  33 */       e.setCancelled(true);
/*     */       
/*  35 */       this.plugin.currentlyEditing = false;
/*  36 */       this.plugin.playerEditing = null;
/*  37 */       this.plugin.editID = null;
/*     */       
/*     */       try {
/*  40 */         this.plugin.getEventConfig().set("event." + editID, newVal);
/*  41 */         this.plugin.getEventConfig().save(this.plugin.eventConfigFile);
/*     */         
/*  43 */         Msg.sendRawToPlayer(p, Config.getString("text-input-done").replace("{value}", newVal));
/*  44 */       } catch (IOException ex) {
/*  45 */         Msg.sendRawToPlayer(p, "save-error");
/*  46 */         ex.printStackTrace();
/*     */       } 
/*     */       
/*  49 */       Bukkit.getScheduler().runTask((Plugin)this.plugin, () -> EventUI.openSettings(paramPlayer));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     if (Config.getBool("boadcast-chat")) {
/*  61 */       e.setCancelled(true);
/*     */     }
/*  63 */     if (this.plugin.chatMuted && !p.hasPermission("ep.chat.mute.bypass")) {
/*  64 */       e.setCancelled(true);
/*  65 */       Msg.sendToPlayer(p, "chat-trytosend");
/*     */     }
/*     */     else {
/*     */       
/*  69 */       String prefix = Main.getChat().getPlayerPrefix(p);
/*  70 */       String suffix = Main.getChat().getPlayerSuffix(p);
/*  71 */       String playerName = p.getDisplayName();
/*  72 */       String message = e.getMessage();
/*     */       
/*  74 */       if (Config.getBool("boadcast-chat")) {
/*  75 */         if (p.hasPermission("ep.chat.color")) {
/*  76 */           Msg.broadcastRaw(
/*  77 */               Config.getString("chat-format")
/*  78 */               .replace("{prefix}", prefix)
/*  79 */               .replace("{displayname}", playerName)
/*  80 */               .replace("{suffix}", suffix)
/*  81 */               .replace("{message}", message));
/*     */         
/*     */         }
/*     */         else {
/*     */           
/*  86 */           Bukkit.broadcastMessage(
/*  87 */               Msg.clr(
/*  88 */                 Config.getString("chat-format")
/*  89 */                 .replace("{prefix}", prefix)
/*  90 */                 .replace("{displayname}", playerName)
/*  91 */                 .replace("{suffix}", suffix))
/*     */               
/*  93 */               .replace("{message}", message));
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void onCommand(PlayerCommandPreprocessEvent e) {
/* 102 */     Player p = e.getPlayer();
/* 103 */     if (!p.hasPermission("ep.chat.blockedcmds.bypass")) {
/* 104 */       String msg = e.getMessage();
/* 105 */       for (String cmd : this.plugin.getConfig().getStringList("chat-blocked-commands")) {
/*     */         
/* 107 */         if (msg.contains(cmd)) {
/* 108 */           Msg.sendToPlayer(p, "no-permission");
/* 109 */           e.setCancelled(true);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     for (Player op : Bukkit.getOnlinePlayers()) {
/*     */       
/* 116 */       if (p.hasPermission("ep.cmdspy"))
/*     */         return; 
/* 118 */       if (op.hasPermission("ep.cmdspy"))
/* 119 */         Msg.sendRawToPlayer(op, 
/* 120 */             Config.getString("command-spy")
/* 121 */             .replace("{player}", p.getName())
/* 122 */             .replace("{command}", e.getMessage().split(" ")[0])); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\chat\listeners\ChatListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */