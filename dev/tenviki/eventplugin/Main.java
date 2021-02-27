/*     */ package dev.tenviki.eventplugin;
/*     */ 
/*     */ import dev.tenviki.eventplugin.event.EventUI;
/*     */ import dev.tenviki.eventplugin.utils.Config;
/*     */ import dev.tenviki.eventplugin.utils.Msg;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import net.milkbowl.vault.chat.Chat;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.RegisteredServiceProvider;
/*     */ import org.bukkit.plugin.java.JavaPlugin;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Main
/*     */   extends JavaPlugin
/*     */ {
/*  39 */   public String pluginVersion = getDescription().getVersion();
/*     */ 
/*     */   
/*     */   public boolean chatMuted = false;
/*     */   
/*  44 */   private static Chat chat = null;
/*     */   
/*     */   public File msgConfigFile;
/*     */   
/*     */   private FileConfiguration msgConfig;
/*     */   public File eventConfigFile;
/*     */   private FileConfiguration eventConfig;
/*  51 */   public Boolean intro = Boolean.valueOf(false);
/*     */   
/*     */   public boolean currentlyEditing = false;
/*     */   
/*  55 */   public String editID = null;
/*  56 */   public Player playerEditing = null;
/*     */   
/*     */   public String ratingJWT;
/*     */   
/*     */   public boolean enableEventSystem;
/*  61 */   public ArrayList<Player> alreadyRated = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  66 */     saveDefaultConfig();
/*  67 */     createMsgConfig();
/*  68 */     createEventConfig();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     System.out.println("");
/*     */     
/*  77 */     Msg.sendConsole("&4 _______  &b ______   &3|");
/*  78 */     Msg.sendConsole("&4|   ____| &b|   _  \\  &3| &fEnabling EventPlugin by TenViki");
/*  79 */     Msg.sendConsole("&4|  |__    &b|  |_)  | &3|");
/*  80 */     Msg.sendConsole("&4|   __|   &b|   ___/  &3| &7Current version: &6v" + this.pluginVersion);
/*  81 */     Msg.sendConsole("&4|  |____  &b|  |      &3|");
/*  82 */     Msg.sendConsole("&4|_______| &b|__|      &3|");
/*  83 */     Msg.sendConsole("&3-------------------------------------------");
/*     */ 
/*     */     
/*  86 */     Msg.sendConsole("&6Setting up commands...");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     Msg.sendConsole("&6Setting up controllers...");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     setupChat();
/*     */     
/* 101 */     Msg.sendConsole("&6SLoading GUIs...");
/* 102 */     EventUI.settingsGui(this);
/*     */ 
/*     */     
/* 105 */     Msg.sendConsole("&6Setting up listeners...");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.enableEventSystem = Config.getBool("event-enable");
/*     */     
/* 115 */     if (this.enableEventSystem) {
/* 116 */       Msg.sendConsole("&6Hooking into discord rating bot...");
/*     */       
/*     */       try {
/* 119 */         this.ratingJWT = loginRating();
/* 120 */       } catch (Exception e) {
/* 121 */         Msg.sendConsole("&4Something went wrong. " + e.getMessage());
/* 122 */         Msg.sendConsole("&4Disabling rating system...");
/* 123 */         this.enableEventSystem = false;
/*     */       } 
/*     */     } 
/*     */     
/* 127 */     Msg.sendConsole("&aEventPlugin is fully loaded!");
/* 128 */     System.out.println("");
/*     */   }
/*     */   
/*     */   private boolean setupChat() {
/* 132 */     RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
/* 133 */     chat = (Chat)rsp.getProvider();
/* 134 */     return (chat != null);
/*     */   }
/*     */   
/*     */   public FileConfiguration getMsgConfig() {
/* 138 */     return this.msgConfig;
/*     */   }
/*     */   
/*     */   private void createMsgConfig() {
/* 142 */     this.msgConfigFile = new File(getDataFolder(), "msg.yml");
/* 143 */     if (!this.msgConfigFile.exists()) {
/* 144 */       this.msgConfigFile.getParentFile().mkdirs();
/* 145 */       saveResource("msg.yml", false);
/*     */     } 
/*     */     
/* 148 */     this.msgConfig = (FileConfiguration)new YamlConfiguration();
/*     */     try {
/* 150 */       this.msgConfig.load(this.msgConfigFile);
/* 151 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 152 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public FileConfiguration getEventConfig() {
/* 158 */     return this.eventConfig;
/*     */   }
/*     */   
/*     */   private void createEventConfig() {
/* 162 */     this.eventConfigFile = new File(getDataFolder(), "event.yml");
/* 163 */     if (!this.eventConfigFile.exists()) {
/* 164 */       this.eventConfigFile.getParentFile().mkdirs();
/* 165 */       saveResource("event.yml", false);
/*     */     } 
/*     */     
/* 168 */     this.eventConfig = (FileConfiguration)new YamlConfiguration();
/*     */     try {
/* 170 */       this.eventConfig.load(this.eventConfigFile);
/* 171 */     } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
/* 172 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Chat getChat() {
/* 178 */     return chat;
/*     */   }
/*     */ 
/*     */   
/*     */   private String loginRating() throws Exception {
/* 183 */     String url = String.valueOf(Config.getString("event-endpoint")) + "login";
/* 184 */     URL obj = new URL(url);
/* 185 */     HttpURLConnection con = (HttpURLConnection)obj.openConnection();
/*     */ 
/*     */     
/* 188 */     con.setRequestMethod("POST");
/*     */     
/* 190 */     con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
/* 191 */     con.setRequestProperty("Content-Type", "application/json");
/*     */     
/* 193 */     String postJsonData = "{\"username\":\"" + Config.getString("event-username") + "\", \"password\":\"" + Config.getString("event-password") + "\"}";
/*     */ 
/*     */     
/* 196 */     con.setDoOutput(true);
/* 197 */     DataOutputStream wr = new DataOutputStream(con.getOutputStream());
/* 198 */     wr.write(postJsonData.getBytes("UTF-8"));
/* 199 */     wr.flush();
/* 200 */     wr.close();
/*     */     
/* 202 */     int responseCode = con.getResponseCode();
/*     */ 
/*     */ 
/*     */     
/* 206 */     StringBuffer response = new StringBuffer();
/*     */     
/* 208 */     if (responseCode > 299) {
/* 209 */       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
/*     */       String str;
/* 211 */       while ((str = bufferedReader.readLine()) != null) {
/* 212 */         response.append(str);
/*     */       }
/* 214 */       bufferedReader.close();
/*     */       
/* 216 */       throw new Exception(String.valueOf(responseCode) + " - " + response);
/*     */     } 
/*     */     
/* 219 */     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
/*     */     String output;
/* 221 */     while ((output = in.readLine()) != null) {
/* 222 */       response.append(output);
/*     */     }
/* 224 */     in.close();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 230 */     return response.toString();
/*     */   }
/*     */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */