/*    */ package dev.tenviki.eventplugin.hodnoceni.commands;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import java.util.Arrays;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandExecutor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class HodnoceniCmd
/*    */   implements CommandExecutor
/*    */ {
/*    */   private Main plugin;
/*    */   
/*    */   public HodnoceniCmd(Main plugin) {
/* 17 */     this.plugin = plugin;
/* 18 */     plugin.getCommand("hodnoceni").setExecutor(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
/* 24 */     if (args.length < 1) {
/* 25 */       Msg.sendToPlayer(s, "rating-help");
/* 26 */       return true;
/*    */     } 
/*    */     
/* 29 */     if (!(s instanceof Player)) {
/* 30 */       System.out.println("This command can be executed only by players");
/* 31 */       return true;
/*    */     } 
/*    */     
/* 34 */     Player p = (Player)s;
/*    */     
/* 36 */     if (!hasPermission(s, "ep.rating")) return true; 
/* 37 */     if (!this.plugin.enableEventSystem) {
/* 38 */       Msg.sendToPlayer(s, "event-disabled");
/* 39 */       return true;
/*    */     } 
/*    */     
/* 42 */     if (this.plugin.alreadyRated.contains(p)) {
/* 43 */       Msg.sendToPlayer(p, "already-rated");
/* 44 */       return true;
/*    */     } 
/*    */ 
/*    */     
/*    */     try {
/* 49 */       if (args.length > 1) {
/*    */         
/* 51 */         String[] msgArr = Arrays.<String>copyOfRange(args, 1, args.length);
/* 52 */         String msg = convertStringArrayToString(msgArr, " ");
/*    */         
/* 54 */         HttpController.sendRating(args[0], s, msg);
/* 55 */         this.plugin.alreadyRated.add(p);
/*    */       } else {
/*    */         
/* 58 */         HttpController.sendRating(args[0], s, "");
/*    */       } 
/* 60 */     } catch (Exception e) {
/* 61 */       e.printStackTrace();
/*    */     } 
/*    */     
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public boolean hasPermission(CommandSender s, String msg) {
/* 68 */     if (s.hasPermission(msg)) return true; 
/* 69 */     Msg.sendToPlayer(s, "no-permission");
/*    */     
/* 71 */     return false;
/*    */   }
/*    */   
/*    */   private static String convertStringArrayToString(String[] strArr, String delimiter) {
/* 75 */     String str = "";
/*    */     
/* 77 */     for (int i = 0; i < strArr.length; i++) {
/* 78 */       str = String.valueOf(str) + strArr[i] + delimiter;
/*    */     }
/*    */     
/* 81 */     return str.substring(0, str.length() - 1);
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\hodnoceni\commands\HodnoceniCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */