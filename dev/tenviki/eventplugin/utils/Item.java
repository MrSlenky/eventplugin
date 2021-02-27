/*    */ package dev.tenviki.eventplugin.utils;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Item
/*    */ {
/*    */   public static ItemStack createItem(Inventory inv, Material material, int amount, int invSlot, String displayname, String... loreString) {
/* 15 */     List<String> lore = new ArrayList<>();
/*    */     
/* 17 */     ItemStack item = new ItemStack(material, amount);
/* 18 */     ItemMeta meta = item.getItemMeta();
/* 19 */     meta.setDisplayName(Msg.clr(displayname)); byte b; int i; String[] arrayOfString;
/* 20 */     for (i = (arrayOfString = loreString).length, b = 0; b < i; ) { String s = arrayOfString[b];
/* 21 */       lore.add(Msg.clr(s));
/*    */       b++; }
/*    */     
/* 24 */     meta.setLore(lore);
/* 25 */     item.setItemMeta(meta);
/*    */     
/* 27 */     inv.setItem(invSlot, item);
/* 28 */     return item;
/*    */   }
/*    */ }


/* Location:              C:\User\\ubvg.lan\Documents\EventPlugin-v1.3.jar!\dev\tenviki\eventplugi\\utils\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */