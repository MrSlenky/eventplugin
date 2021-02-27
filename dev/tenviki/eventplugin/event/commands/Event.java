/*     */ package dev.tenviki.eventplugin.event.commands;
/*     */ 
/*     */ import dev.tenviki.eventplugin.Main;
/*     */ import dev.tenviki.eventplugin.event.EventUI;
/*     */ import dev.tenviki.eventplugin.hodnoceni.commands.HttpController;
/*     */ import dev.tenviki.eventplugin.utils.Msg;
/*     */ import java.util.Arrays;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ public class Event
/*     */   implements CommandExecutor
/*     */ {
/*     */   private Main plugin;
/*     */   
/*     */   public Event(Main plugin) {
/*  20 */     this.plugin = plugin;
/*  21 */     plugin.getCommand("event").setExecutor(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
/*  29 */     if (args.length >= 1) {
/*     */       
/*  31 */       if (args[0].equalsIgnoreCase("countdown")) {
/*     */         
/*  33 */         if (hasPermission(s, "ep.event.countdown")) {
/*  34 */           if (args.length != 2) {
/*  35 */             sendHelp(s);
/*  36 */             return true;
/*     */           } 
/*     */           
/*     */           try {
/*  40 */             int count = Integer.parseInt(args[1]);
/*     */             
/*  42 */             Countdown.countdown(count);
/*     */           
/*     */           }
/*  45 */           catch (NumberFormatException e) {
/*  46 */             Msg.sendToPlayer(s, "not-a-number");
/*  47 */             return true;
/*     */           }
/*     */         
/*     */         }
/*     */       
/*  52 */       } else if (args[0].equalsIgnoreCase("start")) {
/*     */         
/*  54 */         if (!hasPermission(s, "ep.event.start")) return true; 
/*  55 */         MessagesController.rollIntro();
/*     */ 
/*     */       
/*     */       }
/*  59 */       else if (args[0].equalsIgnoreCase("annouce")) {
/*     */         
/*  61 */         if (!hasPermission(s, "ep.event.annouce")) return true;
/*     */         
/*  63 */         if (!this.plugin.enableEventSystem) {
/*  64 */           Msg.sendToPlayer(s, "event-disabled");
/*  65 */           return true;
/*     */         } 
/*     */         
/*  68 */         HttpController.sendEvent(s);
/*     */       
/*     */       }
/*  71 */       else if (args[0].equalsIgnoreCase("edit")) {
/*  72 */         if (!(s instanceof Player)) {
/*  73 */           System.out.println("This command can be executed only by players");
/*  74 */           return true;
/*     */         } 
/*     */         
/*  77 */         if (!hasPermission(s, "ep.event.edit")) return true;
/*     */         
/*  79 */         if (!this.plugin.enableEventSystem) {
/*  80 */           Msg.sendToPlayer(s, "event-disabled");
/*  81 */           return true;
/*     */         } 
/*     */         
/*  84 */         EventUI.openSettings((Player)s);
/*     */ 
/*     */       
/*     */       }
/*  88 */       else if (args[0].equalsIgnoreCase("winner")) {
/*  89 */         if (!hasPermission(s, "ep.event.winner")) return true; 
/*  90 */         if (args.length != 2) {
/*  91 */           sendHelp(s);
/*  92 */           return true;
/*     */         } 
/*     */         
/*  95 */         if (!this.plugin.enableEventSystem) {
/*  96 */           Msg.sendToPlayer(s, "event-disabled");
/*  97 */           return true;
/*     */         } 
/*     */         
/* 100 */         HttpController.sendWinner(s, args[1]);
/*     */       
/*     */       }
/* 103 */       else if (args[0].equalsIgnoreCase("addmsg")) {
/*     */         
/* 105 */         if (!hasPermission(s, "ep.event.start")) return true; 
/* 106 */         if (!(s instanceof Player)) {
/* 107 */           System.out.println("This command can be executed only by players.");
/* 108 */           return true;
/*     */         } 
/*     */         
/* 111 */         String[] msgArr = Arrays.<String>copyOfRange(args, 1, args.length);
/*     */         
/* 113 */         Player p = (Player)s;
/* 114 */         MessagesController.addMessage(p, StringFromArray(msgArr));
/*     */       
/*     */       }
/* 117 */       else if (args[0].equalsIgnoreCase("setfinal")) {
/*     */         
/* 119 */         if (!hasPermission(s, "ep.event.start")) return true; 
/* 120 */         if (!(s instanceof Player)) {
/* 121 */           System.out.println("This command can be executed only by players.");
/* 122 */           return true;
/*     */         } 
/*     */         
/* 125 */         Player p = (Player)s;
/* 126 */         MessagesController.setFinal(p);
/*     */       }
/*     */       else {
/*     */         
/* 130 */         sendHelp(s);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 135 */       sendHelp(s);
/*     */     } 
/*     */     
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   public void sendHelp(CommandSender s) {
/* 142 */     for (String msg : this.plugin.getConfig().getStringList("event-help"))
/*     */     {
/* 144 */       Msg.sendRawToPlayer(s, msg);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean hasPermission(CommandSender s, String msg) {
/* 149 */     if (s.hasPermission(msg)) return true; 
/* 150 */     Msg.sendToPlayer(s, "no-permission");
/*     */     
/* 152 */     return false;
/*     */   }
/*     */   
/*     */   public String StringFromArray(String[] arr) {
/* 156 */     String str = "";
/*     */     
/* 158 */     for (int i = 0; i < arr.length; i++) {
/* 159 */       str = String.valueOf(str) + arr[i] + " ";
/*     */     }
/*     */     
/* 162 */     return str;
/*     */   }
/*     */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\event\commands\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */