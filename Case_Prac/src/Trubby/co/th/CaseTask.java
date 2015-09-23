package Trubby.co.th;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CaseTask extends BukkitRunnable{

	public Random ran = new Random();
	public Player p;
	public ArrayList<ItemStack> glasses = new ArrayList<>();
	public ArrayList<ItemStack> items = new ArrayList<>();
	public ArrayList<ItemStack> items_random = new ArrayList<>();
	public ArrayList<Integer> itemSlot = new ArrayList<>();
	public ArrayList<Integer> glassSlot = new ArrayList<>();
	public Inventory inv;
	public CaseTask caseTask;
	
	/** 0  1  2  3  4  5  6  7  8
	 *  9  10 11 12 13 14 15 16 17
	 *  18 19 20 21 22 23 24 25 26
	 *  27 28 29 30 31 32 33 34 35
	 *  36 37 38 39 40 41 42 43 44
	 */ 
	
	public CaseTask(Player p) {
		this.p = p;
		
		inv = Bukkit.createInventory(null, 45, "case");
		p.openInventory(inv);
		
		glasses.add(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1));
		glasses.add(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3));
		glasses.add(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4));
		glasses.add(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5));
		glasses.add(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)6));
		glasses.add(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10));
		
		items.add(new ItemStack(Material.ANVIL));
		items.add(new ItemStack(Material.ACACIA_FENCE_GATE));
		items.add(new ItemStack(Material.APPLE));
		items.add(new ItemStack(Material.ARROW));
		items.add(new ItemStack(Material.ARMOR_STAND));
		items.add(new ItemStack(Material.BAKED_POTATO));
		items.add(new ItemStack(Material.IRON_SWORD));
		items.add(new ItemStack(Material.THIN_GLASS));
		items.add(new ItemStack(Material.EMERALD));
		items.add(new ItemStack(Material.IRON_SPADE));
		items.add(new ItemStack(Material.IRON_CHESTPLATE));
		items.add(new ItemStack(Material.MELON));
		
		Collections.addAll(itemSlot, 4, 13, 22, 31, 40);
		Collections.addAll(glassSlot, 0,1,2,3,5,6,7,8,9,10,11,12,14,15,16,17,18,19,20,24
				,25,26,27,28,29,30,32,33,34,35,36,37,38,39,41,42,43,44);
		
		inv.setItem(21, new ItemStack(Material.STAINED_GLASS, 1, (short)15));
		inv.setItem(23, new ItemStack(Material.STAINED_GLASS, 1, (short)15));
		
		//random items
		while(items_random.size() < 15){
			items_random.add(items.get(ran.nextInt(items.size())));
		}
	}
	
	int count = 0;
	public int itemRotate = 0;
	
	@Override
	public void run() {
		if(count > 23){
			new CaseTask2(this).runTaskTimer(case_prac.instance, 0, 2);
			this.cancel();
		}else{
			doGlasses();
			doItems(itemRotate);
			playSound();
		}
		
		
		
		
		if(itemRotate < 9){
			itemRotate++;
		}else{
			itemRotate = 0;
		}
		
		count++;
		
	}
	
	public void doGlasses(){
		for(int i : glassSlot){
			inv.setItem(i, glasses.get(ran.nextInt(glasses.size())));
		}
	}
	
	public void doItems(int rotate){//1
		int r = new Integer(rotate);
		boolean first = true;
		
		for(int i : itemSlot){
			if(first){
				inv.setItem(i, items_random.get(r));
				first = false;
			}else{
				if(r<9){
				r++;
				inv.setItem(i, items_random.get(r));
				}else{
					r = 0;
					inv.setItem(i, items_random.get(r));
				}
			}
			
		}
	}
	
	public int spin(int i){
		
		if(i<9){
			i = i+1;
		}else{
			i = 0;
		}
		
		return i;
	}
	
	public int sound = 0;
	public void playSound(){
		if(sound == 0){
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1f, 2f);
			sound++;
		}else if(sound == 1){
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1f, 1.5f);
			sound++;
		}else if(sound == 2){
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1f, 1f);
			sound++;
		}else{
			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1f, 1.5f);
			sound = 0;
		}
	}
	
	

}
