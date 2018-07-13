package net.farugames.hub;

import org.bukkit.Location;

public enum ParkourPlayerState
{
    LOBBY(false),  PARKOUR(true);

    private static ParkourPlayerState currentStatus;
    private boolean canCheckPoint;
    private Location loc;
    
    private ParkourPlayerState(boolean canCheckPoint)
    {
        this.canCheckPoint = canCheckPoint;
    }

    public static boolean isStatus(ParkourPlayerState status)
    {
        return currentStatus == status;
    }

    public static ParkourPlayerState getStatus()
    {
        return currentStatus;
    }

    public static void setStatus(ParkourPlayerState status)
    {
        currentStatus = status;
    }

    public boolean canCheckPoint()
    {
        return this.canCheckPoint;
    }

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
		this.loc = loc;
	}
}
