public class VehicleHiringManager {

	LocatorMap<String> locatorMap;

	public VehicleHiringManager() {
		locatorMap = new TreeLocatorMap<String>();
	}
	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return locatorMap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		this.locatorMap = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		Pair<Boolean,Integer> result = locatorMap.add(id, loc);
		if (result.first){
			return true;
		}else {
			return false;
		}
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		Pair<Boolean,Integer> result = locatorMap.move(id, loc);
		if (result.first){
			return true;
		}else {
			return false;
		}
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		Pair<Boolean,Integer> result = locatorMap.remove(id);
		if (result.first){
			return true;
		}else {
			return false;
		}
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		Pair<Location,Integer> result = locatorMap.getLoc(id);
		return result.first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		return null;
	}
}
