/*     */ package dev.tenviki.eventplugin.blocker;
/*     */ 
/*     */ import dev.tenviki.eventplugin.Main;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class BlockerController
/*     */ {
/*     */   private static Main plugin;
/*     */   private static HttpURLConnection connection;
/*     */   
/*     */   public BlockerController(Main plugin) {
/*  19 */     BlockerController.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getBlockedNames() {
/*  26 */     StringBuffer responseContent = new StringBuffer();
/*     */     
/*  28 */     List<String> blockedNames = null;
/*     */     
/*     */     try {
/*  31 */       URL url = new URL("https://lukynka.cz/text-db/c/65nsBFu6vJ/blacklist.txt");
/*  32 */       connection = (HttpURLConnection)url.openConnection();
/*     */       
/*  34 */       connection.setRequestMethod("GET");
/*  35 */       connection.setConnectTimeout(2000);
/*  36 */       connection.setReadTimeout(2000);
/*     */       
/*  38 */       int status = connection.getResponseCode();
/*     */       
/*  40 */       if (status > 299) {
/*  41 */         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
/*     */         String line;
/*  43 */         while ((line = reader.readLine()) != null) {
/*  44 */           responseContent.append(line);
/*     */         }
/*     */         
/*  47 */         reader.close();
/*     */       }
/*     */       else {
/*     */         
/*  51 */         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */         String line;
/*  53 */         while ((line = reader.readLine()) != null) {
/*  54 */           responseContent.append(String.valueOf(line) + "\n");
/*     */         }
/*     */         
/*  57 */         String[] items = responseContent.toString().split("\n");
/*  58 */         blockedNames = Arrays.asList(items);
/*     */         
/*  60 */         reader.close();
/*     */       } 
/*  62 */       return blockedNames;
/*  63 */     } catch (IOException e) {
/*     */       
/*  65 */       e.printStackTrace();
/*     */       
/*  67 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getBlockedIPs() {
/*  74 */     StringBuffer responseContent = new StringBuffer();
/*     */     
/*  76 */     List<String> blockedIPs = null;
/*     */     
/*     */     try {
/*  79 */       URL url = new URL("https://lukynka.cz/text-db/c/65nsBFu6vJ/blacklist-ip.txt");
/*  80 */       connection = (HttpURLConnection)url.openConnection();
/*     */       
/*  82 */       connection.setRequestMethod("GET");
/*  83 */       connection.setConnectTimeout(2000);
/*  84 */       connection.setReadTimeout(2000);
/*     */       
/*  86 */       int status = connection.getResponseCode();
/*     */       
/*  88 */       if (status > 299) {
/*  89 */         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
/*     */         String line;
/*  91 */         while ((line = reader.readLine()) != null) {
/*  92 */           responseContent.append(line);
/*     */         }
/*     */         
/*  95 */         reader.close();
/*     */       }
/*     */       else {
/*     */         
/*  99 */         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
/*     */         String line;
/* 101 */         while ((line = reader.readLine()) != null) {
/* 102 */           responseContent.append(String.valueOf(line) + "\n");
/*     */         }
/*     */         
/* 105 */         String[] items = responseContent.toString().split("\n");
/* 106 */         blockedIPs = Arrays.asList(items);
/*     */         
/* 108 */         reader.close();
/*     */       } 
/* 110 */       return blockedIPs;
/* 111 */     } catch (IOException e) {
/*     */       
/* 113 */       e.printStackTrace();
/*     */       
/* 115 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\blocker\BlockerController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */