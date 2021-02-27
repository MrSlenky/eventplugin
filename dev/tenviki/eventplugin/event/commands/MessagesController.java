/*     */ package dev.tenviki.eventplugin.event.commands;
/*     */ 
/*     */ import dev.tenviki.eventplugin.Main;
/*     */ import dev.tenviki.eventplugin.chat.commands.ChatController;
/*     */ import dev.tenviki.eventplugin.utils.Config;
/*     */ import dev.tenviki.eventplugin.utils.Msg;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Set;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class MessagesController
/*     */ {
/*     */   private static Main plugin;
/*     */   
/*     */   public MessagesController(Main plugin) {
/*  24 */     MessagesController.plugin = plugin;
/*     */   }
/*     */   
/*     */   public static void setFinal(Player p) {
/*  28 */     int yaw = Math.round(p.getLocation().getYaw());
/*  29 */     int pitch = Math.round(p.getLocation().getPitch());
/*     */     
/*  31 */     double x = p.getLocation().getX();
/*  32 */     double y = p.getLocation().getY();
/*  33 */     double z = p.getLocation().getZ();
/*     */     
/*  35 */     plugin.getMsgConfig().set("final.pitch", Integer.valueOf(pitch));
/*  36 */     plugin.getMsgConfig().set("final.yaw", Integer.valueOf(yaw));
/*  37 */     plugin.getMsgConfig().set("final.world", p.getWorld().getName());
/*     */     
/*  39 */     plugin.getMsgConfig().set("final.x", Double.valueOf(x));
/*  40 */     plugin.getMsgConfig().set("final.y", Double.valueOf(y));
/*  41 */     plugin.getMsgConfig().set("final.z", Double.valueOf(z));
/*  42 */     plugin.getMsgConfig().set("final.time", Integer.valueOf(5));
/*     */     
/*     */     try {
/*  45 */       plugin.getMsgConfig().save(plugin.msgConfigFile);
/*  46 */     } catch (IOException e) {
/*  47 */       Msg.sendToPlayer(p, "save-error");
/*  48 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  51 */     Msg.sendToPlayer(p, "final-save");
/*     */   }
/*     */   
/*     */   public static void addMessage(Player p, String s) {
/*  55 */     int yaw = Math.round(p.getLocation().getYaw());
/*  56 */     int pitch = Math.round(p.getLocation().getPitch());
/*     */     
/*  58 */     double x = p.getLocation().getX();
/*  59 */     double y = p.getLocation().getY();
/*  60 */     double z = p.getLocation().getZ();
/*     */     
/*  62 */     String id = String.valueOf(generateId());
/*  63 */     while (messageExists(id)) {
/*  64 */       id = String.valueOf(generateId());
/*     */     }
/*     */     
/*  67 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".location.pitch", Integer.valueOf(pitch));
/*  68 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".location.yaw", Integer.valueOf(yaw));
/*     */     
/*  70 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".location.world", p.getWorld().getName());
/*     */     
/*  72 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".location.x", Double.valueOf(x));
/*  73 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".location.y", Double.valueOf(y));
/*  74 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".location.z", Double.valueOf(z));
/*  75 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".time", Integer.valueOf(5));
/*     */     
/*  77 */     ArrayList<String> list = new ArrayList<>();
/*  78 */     list.add("---------------------");
/*  79 */     list.add("");
/*  80 */     list.add(s);
/*  81 */     list.add("");
/*  82 */     list.add("---------------------");
/*     */     
/*  84 */     plugin.getMsgConfig().set("messages." + String.valueOf(id) + ".message", list);
/*     */     
/*     */     try {
/*  87 */       plugin.getMsgConfig().save(plugin.msgConfigFile);
/*     */     }
/*  89 */     catch (IOException e) {
/*  90 */       Msg.sendToPlayer(p, "save-error");
/*     */       
/*  92 */       e.printStackTrace();
/*     */       
/*     */       return;
/*     */     } 
/*  96 */     Msg.sendRawToPlayer(p, Config.getString("file-save").replace("{id}", String.valueOf(id)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void rollIntro() {
/* 101 */     if (plugin.intro.booleanValue())
/*     */       return; 
/* 103 */     String[] msgs = getMessages();
/*     */     
/* 105 */     for (Player p : Bukkit.getOnlinePlayers()) {
/* 106 */       if (p.hasPermission("ep.event.start"))
/*     */         continue; 
/* 108 */       p.setGameMode(GameMode.SPECTATOR);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 113 */     plugin.intro = Boolean.valueOf(true);
/*     */     
/* 115 */     int delay = 0;
/* 116 */     for (int i = 0; i < msgs.length; i++) {
/* 117 */       int index = i;
/* 118 */       int time = plugin.getMsgConfig().getInt("messages." + msgs[i] + ".time");
/* 119 */       Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)plugin, () -> {
/*     */             ChatController.clearChat();
/*     */             
/*     */             for (String msg : plugin.getMsgConfig().getStringList("messages." + paramArrayOfString[paramInt] + ".message")) {
/*     */               Msg.broadcastRaw(msg);
/*     */             }
/*     */             
/*     */             double x = plugin.getMsgConfig().getDouble("messages." + paramArrayOfString[paramInt] + ".location.x");
/*     */             
/*     */             double y = plugin.getMsgConfig().getDouble("messages." + paramArrayOfString[paramInt] + ".location.y");
/*     */             
/*     */             double z = plugin.getMsgConfig().getDouble("messages." + paramArrayOfString[paramInt] + ".location.z");
/*     */             
/*     */             int pitch = plugin.getMsgConfig().getInt("messages." + paramArrayOfString[paramInt] + ".location.pitch");
/*     */             
/*     */             int yaw = plugin.getMsgConfig().getInt("messages." + paramArrayOfString[paramInt] + ".location.yaw");
/*     */             
/*     */             World w = Bukkit.getWorld(plugin.getMsgConfig().getString("messages." + paramArrayOfString[paramInt] + ".location.world"));
/*     */             for (Player p : Bukkit.getOnlinePlayers()) {
/*     */               if (p.hasPermission("ep.event.start")) {
/*     */                 continue;
/*     */               }
/*     */               p.teleport(new Location(w, x, y, z, yaw, pitch));
/*     */             } 
/*     */             if (plugin.getMsgConfig().getString("messages." + paramArrayOfString[paramInt] + ".cmd") != null) {
/*     */               ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/*     */               String command = plugin.getMsgConfig().getString("messages." + paramArrayOfString[paramInt] + ".cmd");
/*     */               Bukkit.dispatchCommand((CommandSender)console, command);
/*     */             } 
/* 148 */           }toTicks(time + delay));
/*     */       
/* 150 */       delay += time;
/*     */     } 
/*     */     
/* 153 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)plugin, () -> {
/*     */           plugin.intro = Boolean.valueOf(false);
/*     */           
/*     */           double x = plugin.getMsgConfig().getDouble("final.x");
/*     */           
/*     */           double y = plugin.getMsgConfig().getDouble("final.y");
/*     */           
/*     */           double z = plugin.getMsgConfig().getDouble("final.z");
/*     */           
/*     */           int pitch = plugin.getMsgConfig().getInt("final.pitch");
/*     */           
/*     */           int yaw = plugin.getMsgConfig().getInt("final.yaw");
/*     */           World w = Bukkit.getWorld(plugin.getMsgConfig().getString("final.world"));
/*     */           for (Player p : Bukkit.getOnlinePlayers()) {
/*     */             if (p.hasPermission("ep.event.start")) {
/*     */               continue;
/*     */             }
/*     */             p.teleport(new Location(w, x, y, z, yaw, pitch));
/*     */             p.setGameMode(GameMode.ADVENTURE);
/*     */           } 
/*     */           System.out.print(plugin.getMsgConfig().getString("final.cmd"));
/*     */           if (plugin.getMsgConfig().getString("final.cmd") != null) {
/*     */             ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
/*     */             String command = plugin.getMsgConfig().getString("final.cmd");
/*     */             Bukkit.dispatchCommand((CommandSender)console, command);
/*     */           } 
/* 179 */         }toTicks(delay + plugin.getMsgConfig().getInt("final.time")));
/*     */   }
/*     */   
/*     */   public static int toTicks(int seconds) {
/* 183 */     return seconds * 20;
/*     */   }
/*     */   
/*     */   public static String[] getMessages() {
/* 187 */     if (plugin.getMsgConfig().getConfigurationSection("messages") == null) {
/* 188 */       return new String[0];
/*     */     }
/*     */     
/* 191 */     Set<String> set = plugin.getMsgConfig().getConfigurationSection("messages").getKeys(false);
/*     */     
/* 193 */     int k = 0;
/* 194 */     String[] spawns = new String[set.size()];
/*     */     
/* 196 */     for (String str : set) {
/* 197 */       spawns[k++] = str;
/*     */     }
/* 199 */     return spawns;
/*     */   }
/*     */   
/*     */   public static boolean messageExists(String id) {
/* 203 */     if (plugin.getMsgConfig().get("spawns." + id) == null) return false; 
/* 204 */     return true;
/*     */   }
/*     */   
/*     */   public static int generateId() {
/* 208 */     return (int)Math.floor(Math.random() * 10000.0D + 0.0D);
/*     */   }
/*     */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\event\commands\MessagesController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */