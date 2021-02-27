/*    */ package dev.tenviki.eventplugin.event;
/*    */ 
/*    */ import dev.tenviki.eventplugin.Main;
/*    */ import dev.tenviki.eventplugin.hodnoceni.commands.HttpController;
/*    */ import dev.tenviki.eventplugin.utils.Config;
/*    */ import dev.tenviki.eventplugin.utils.Item;
/*    */ import dev.tenviki.eventplugin.utils.Msg;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ public class EventUI
/*    */ {
/*    */   public static Inventory inv;
/* 19 */   public static String inventoryName = Config.getString("settings.title");
/* 20 */   public static int invRows = 9;
/*    */   
/*    */   private static Main plugin;
/*    */   
/*    */   public static void settingsGui(Main plugin) {
/* 25 */     inv = Bukkit.createInventory(null, invRows, inventoryName);
/* 26 */     EventUI.plugin = plugin;
/* 27 */     initItems();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void initItems() {
/* 32 */     int i = 0;
/* 33 */     for (String s : plugin.getEventConfig().getConfigurationSection("event").getKeys(false)) {
/*    */       
/* 35 */       if (s.equalsIgnoreCase("foto-url"))
/*    */         continue; 
/* 37 */       String setting = plugin.getEventConfig().getString("event." + s);
/* 38 */       inv.setItem(i, Item.createItem(
/* 39 */             inv, 
/* 40 */             Material.STONE, 1, i, 
/* 41 */             Config.getString("settings.item-name").replace("{thing}", s), new String[] {
/* 42 */               Config.getString("settings.item-lore").replace("{value}", setting)
/*    */             }));
/*    */       
/* 45 */       i++;
/*    */     } 
/*    */     
/* 48 */     inv.setItem(8, Item.createItem(inv, Material.DIAMOND_BLOCK, 1, i, Config.getString("settings.submit"), new String[0]));
/*    */   }
/*    */   
/*    */   public static void openSettings(Player p) {
/* 52 */     initItems();
/* 53 */     p.openInventory(inv);
/*    */   }
/*    */   
/*    */   public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
/* 57 */     if (slot < 8) {
/* 58 */       String setting = (String)plugin.getEventConfig().getConfigurationSection("event").getKeys(false).toArray()[slot];
/* 59 */       System.out.println(setting);
/*    */       
/* 61 */       textInput(setting, p);
/*    */     } else {
/* 63 */       HttpController.sendEvent((CommandSender)p);
/*    */     } 
/* 65 */     p.closeInventory();
/*    */   }
/*    */   
/*    */   public static void textInput(String id, Player p) {
/* 69 */     Msg.sendRawToPlayer(p, Config.getString("text-input").replace("{key}", id));
/* 70 */     plugin.currentlyEditing = true;
/* 71 */     plugin.editID = id;
/* 72 */     plugin.playerEditing = p;
/*    */     
/* 74 */     p.closeInventory();
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugin\event\EventUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */