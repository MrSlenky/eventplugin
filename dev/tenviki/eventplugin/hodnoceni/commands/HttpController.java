/*     */ package dev.tenviki.eventplugin.hodnoceni.commands;
/*     */ 
/*     */ import dev.tenviki.eventplugin.Main;
/*     */ import dev.tenviki.eventplugin.utils.Config;
/*     */ import dev.tenviki.eventplugin.utils.Msg;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class HttpController
/*     */ {
/*     */   private static Main plugin;
/*     */   
/*     */   public HttpController(Main plugin) {
/*  20 */     HttpController.plugin = plugin;
/*     */   }
/*     */   
/*     */   public static void sendRating(String rating, CommandSender s, String message) {
/*  24 */     String name = "";
/*  25 */     if (s instanceof Player) {
/*  26 */       name = ((Player)s).getDisplayName();
/*     */     } else {
/*  28 */       name = "Server";
/*     */     } 
/*  30 */     String data = "{\"rating\":\"" + 
/*  31 */       rating + "\"," + 
/*  32 */       "\"player\":\"" + name + "\"," + 
/*  33 */       "\"author\":\"" + plugin.getEventConfig().getString("event.author") + "\"," + 
/*  34 */       "\"ip\":\"" + plugin.getEventConfig().getString("event.ip") + "\"," + 
/*  35 */       "\"message\":\"" + message + "\"}";
/*     */     
/*     */     try {
/*  38 */       sendHttpRequest("rate", data, plugin.ratingJWT);
/*  39 */       Msg.sendToPlayer(s, "rating-success");
/*  40 */     } catch (Exception e) {
/*     */       
/*  42 */       Msg.sendToPlayer(s, "rating-error");
/*  43 */       Msg.sendConsole("&4Something went wrong. &c" + e.getMessage());
/*  44 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendEvent(CommandSender s) {
/*  49 */     String data = "{\"name\":\"" + 
/*  50 */       plugin.getEventConfig().getString("event.name") + "\"," + 
/*  51 */       "\"author\":\"" + plugin.getEventConfig().getString("event.author") + "\"," + 
/*  52 */       "\"version\":\"" + plugin.getEventConfig().getString("event.version") + "\"," + 
/*  53 */       "\"ip\":\"" + plugin.getEventConfig().getString("event.ip") + "\"," + 
/*  54 */       "\"dynip\":\"" + plugin.getEventConfig().getString("event.dynip") + "\"," + 
/*  55 */       "\"prize\":\"" + plugin.getEventConfig().getString("event.prize") + "\"," + 
/*  56 */       "\"start\":\"" + plugin.getEventConfig().getString("event.start") + "\"," + 
/*  57 */       "\"foto\":\"" + plugin.getEventConfig().getString("event.foto-url") + "\"," + 
/*  58 */       "\"note\":\"" + plugin.getEventConfig().getString("event.note") + "\"}";
/*     */     
/*     */     try {
/*  61 */       sendHttpRequest("event", data, plugin.ratingJWT);
/*  62 */       Msg.sendToPlayer(s, "http-success");
/*  63 */     } catch (Exception e) {
/*     */       
/*  65 */       Msg.sendToPlayer(s, "http-error");
/*  66 */       Msg.sendConsole("&4Something went wrong. &c" + e.getMessage());
/*  67 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void sendWinner(CommandSender s, String name) {
/*  73 */     String data = "{\"name\":\"" + 
/*  74 */       plugin.getEventConfig().getString("event.name") + "\"," + 
/*  75 */       "\"author\":\"" + plugin.getEventConfig().getString("event.author") + "\"," + 
/*  76 */       "\"player\":\"" + name + "\"," + 
/*  77 */       "\"ip\":\"" + plugin.getEventConfig().getString("event.ip") + "\"}";
/*     */     try {
/*  79 */       plugin.getEventConfig().save(plugin.eventConfigFile);
/*  80 */     } catch (IOException e) {
/*     */       
/*  82 */       e.printStackTrace();
/*     */     } 
/*     */     
/*     */     try {
/*  86 */       sendHttpRequest("winner", data, plugin.ratingJWT);
/*  87 */       Msg.sendToPlayer(s, "http-success");
/*  88 */     } catch (Exception e) {
/*     */       
/*  90 */       Msg.sendToPlayer(s, "http-error");
/*  91 */       Msg.sendConsole("&4Something went wrong. &c" + e.getMessage());
/*  92 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String sendHttpRequest(String endpoint, String data, String jsonWebToken) throws Exception {
/*  98 */     String url = String.valueOf(Config.getString("event-endpoint")) + endpoint;
/*  99 */     URL obj = new URL(url);
/* 100 */     HttpURLConnection con = (HttpURLConnection)obj.openConnection();
/*     */     
/* 102 */     con.setConnectTimeout(2000);
/* 103 */     con.setReadTimeout(2000);
/*     */ 
/*     */     
/* 106 */     con.setRequestMethod("POST");
/*     */     
/* 108 */     con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
/* 109 */     con.setRequestProperty("Content-Type", "application/json");
/* 110 */     con.setRequestProperty("x-auth-token", jsonWebToken);
/*     */     
/* 112 */     String postJsonData = data;
/*     */ 
/*     */     
/* 115 */     con.setDoOutput(true);
/* 116 */     DataOutputStream wr = new DataOutputStream(con.getOutputStream());
/* 117 */     wr.write(postJsonData.getBytes("UTF-8"));
/* 118 */     wr.flush();
/* 119 */     wr.close();
/*     */     
/* 121 */     int responseCode = con.getResponseCode();
/*     */ 
/*     */     
/* 124 */     StringBuffer response = new StringBuffer();
/*     */     
/* 126 */     if (responseCode > 299) {
/* 127 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
/*     */       String str;
/* 129 */       while ((str = bufferedReader.readLine()) != null) {
/* 130 */         response.append(str);
/*     */       }
/* 132 */       bufferedReader.close();
/*     */       
/* 134 */       throw new Exception(String.valueOf(responseCode) + " - " + response);
/*     */     } 
/*     */     
/* 137 */     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
/*     */     String output;
/* 139 */     while ((output = in.readLine()) != null) {
/* 140 */       response.append(output);
/*     */     }
/* 142 */     in.close();
/*     */ 
/*     */ 
/*     */     
/* 146 */     return response.toString();
/*     */   }
/*     */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\hodnoceni\commands\HttpController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */