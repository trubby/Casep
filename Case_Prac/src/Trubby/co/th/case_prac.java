package Trubby.co.th;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class case_prac extends JavaPlugin{
	
	public static case_prac instance;

	@Override
	public void onEnable() {
		instance = this;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(label.equalsIgnoreCase("casep")){
			
			Player p = (Player) sender;
			
			new CaseTask(p).runTaskTimer(this, 0, 1);
		}
		
		return false;
	}
	
}
