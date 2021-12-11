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
		Pair<Boolean, Integer> result = Bst.find(k);
		if(result.first){
			return result;
		}else{
			Bst.insert(k, loc);
			locator.add(k, loc);
			return result;
		}
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		Pair<Boolean, Integer> result = Bst.find(k);
		if(!result.first){
			return result;
		}else{
			locator.remove(k, Bst.retrieve());
			locator.add(k, loc);
			Bst.update(loc);
			return result;
		}
		
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		Pair<Boolean, Integer> result = Bst.find(k);
		if (!result.first) {
			return new Pair<Location, Integer>(null,result.second);
		}else {
			return new Pair<Location, Integer>(Bst.retrieve(),result.second);
		}
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		Pair<Boolean, Integer> result = Bst.find(k);
		if (!result.first) {
			return result;
		}else {
			locator.remove(k, Bst.retrieve());
			Bst.remove(k);
			return result;
		}
	}

	@Override
	public List<K> getAll() {
		return Bst.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		return null;
	}

}
