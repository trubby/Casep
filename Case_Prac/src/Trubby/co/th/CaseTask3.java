package Trubby.co.th;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class CaseTask3 extends BukkitRunnable{

	public Random ran = new Random();
	public Player p;
	public ArrayList<ItemStack> glasses;
	public ArrayList<ItemStack> items;
	public ArrayList<ItemStack> items_random;
	public ArrayList<Integer> itemSlot;
	public ArrayList<Integer> glassSlot;
	public Inventory inv;
	public CaseTask2 caseTask;
	
	public CaseTask3(CaseTask2 cs) {
		this.caseTask = cs;
		p = caseTask.p;
		glasses = caseTask.glasses;
		items = caseTask.items;
		items_random = caseTask.items_random;
		itemSlot = caseTask.itemSlot;
		glassSlot = caseTask.glassSlot;
		inv = caseTask.inv;
		itemRotate = caseTask.itemRotate;
		sound = caseTask.sound;
	}
	
	int count = 0;
	int itemRotate;
	
	@Override
	public void run() {
		if(count > 6){
			new CaseTask4(this).runTaskTimer(case_prac.instance, 0, 6);
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
	
	int sound;
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
