package net.farugames.hub.runnables;

import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.farugames.api.tools.board.ScoreboardSign;
import net.farugames.hub.PluginMethods;
import net.farugames.hub.boards.ScoreboardManager;

public class ScoreboardAnimationsRunnable extends BukkitRunnable {

	int timer = 0;
	boolean direction = false;

	@Override
	public void run() {
		for (Entry<Player, ScoreboardSign> boards : ScoreboardManager.boards.entrySet()) {
			switch (timer) {
			case 1:
				boards.getValue().setLine(11, "§6play.farugames.net");
				break;
			case 2:
				boards.getValue().setLine(11, "§ep§6lay.farugames.net");
				break;
			case 3:
				boards.getValue().setLine(11, "§fp§el§6ay.farugames.net");
				break;
			case 5:
				boards.getValue().setLine(11, "§ep§fl§ea§6y.farugames.net");
				break;
			case 6:
				boards.getValue().setLine(11, "§6p§el§fa§ey§6.farugames.net");
				break;
			case 7:
				boards.getValue().setLine(11, "§6pl§ea§fy§e.§6farugames.net");
				break;
			case 8:
				boards.getValue().setLine(11, "§6pla§ey§f.§ef§6arugames.net");
				break;
			case 9:
				boards.getValue().setLine(11, "§6play§e.§ff§ea§6rugames.net");
				break;
			case 10:
				boards.getValue().setLine(11, "§6play.§ef§fa§er§6ugames.net");
				break;
			case 11:
				boards.getValue().setLine(11, "§6play.f§ea§fr§eu§6games.net");
				break;
			case 12:
				boards.getValue().setLine(11, "§6play.fa§er§fu§eg§6ames.net");
				break;
			case 13:
				boards.getValue().setLine(11, "§6play.far§eu§fg§ea§6mes.net");
				break;
			case 14:
				boards.getValue().setLine(11, "§6play.faru§eg§fa§em§6es.net");
				break;
			case 15:
				boards.getValue().setLine(11, "§6play.farug§ea§fm§ee§6s.net");
				break;
			case 16:
				boards.getValue().setLine(11, "§6play.faruga§em§fe§es§6.net");
				break;
			case 17:
				boards.getValue().setLine(11, "§6play.farugam§ee§fs§e.§6net");
				break;
			case 18:
				boards.getValue().setLine(11, "§6play.farugame§es§f.§en§6et");
				break;
			case 19:
				boards.getValue().setLine(11, "§6play.farugames§e.§fn§ee§6t");
				break;
			case 20:
				boards.getValue().setLine(11, "§6play.farugames.§en§fe§et");
				break;
			case 21:
				boards.getValue().setLine(11, "§6play.farugames.n§ee§ft");
				break;
			case 22:
				boards.getValue().setLine(11, "§6play.farugames.ne§et");
				break;
			case 23:
				boards.getValue().setLine(11, "§6play.farugames.net");
				break;
			case 30:
				boards.getValue().setLine(11, "§6play.farugames.net");
				boards.getValue().setObjectiveName(PluginMethods.getPrefixScoreboard());
				break;
			case 34:
				boards.getValue().setLine(11, "§eplay.farugames.net");
				boards.getValue().setObjectiveName(PluginMethods.getPrefixScoreboard().replaceAll("§f▼", "§b▼"));
				break;
			case 38:
				boards.getValue().setLine(11, "§6play.farugames.net");
				boards.getValue().setObjectiveName(PluginMethods.getPrefixScoreboard());
				break;
			case 42:
				boards.getValue().setLine(11, "§eplay.farugames.net");
				boards.getValue().setObjectiveName(PluginMethods.getPrefixScoreboard().replaceAll("§f▼", "§b▼"));
				break;
			case 46:
				boards.getValue().setObjectiveName(PluginMethods.getPrefixScoreboard());
				boards.getValue().setLine(11, "§6play.farugames.net");
				break;
			}
		}
		if (timer >= 60){
			direction = true;
		}
		if (timer <= 0){
			direction = false;
		}
		if (direction == false) {
			timer = timer + 1;
		} else {
			timer = timer - 1;
		}
	}

}
