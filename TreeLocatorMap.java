public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	Map<K,Location> Bst;
	Locator<K> locator;

	public TreeLocatorMap(){
		Bst = new BST<K,Location>();
		locator = new TreeLocator<K>();
	}

	@Override
	public Map<K, Location> getMap() {
		return Bst;
	}

	@Override
	public Locator<K> getLocator() {
		return locator;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		Pair<Boolean, Integer> res = Bst.find(k);
		if(res.first){
			return res;
		}else{
			Bst.insert(k, loc);
			locator.add(k, loc);
			return res;
		}
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair<Boolean, Integer> res = Bst.find(k);
		if(!res.first){
			return res;
		}else{
			locator.remove(k, Bst.retrieve());
			locator.add(k, loc);
			Bst.update(loc);
			return res;
		}
		
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		return null;
	}

}
