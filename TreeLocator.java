class TreeLocatorNode<T>{
	Location loc;
	List<T> data;
	TreeLocatorNode<T> c1;
	TreeLocatorNode<T> c2;
	TreeLocatorNode<T> c3;
	TreeLocatorNode<T> c4;

	public TreeLocatorNode(Location loc, T e) {
		data = new LinkedList<T>();
		this.loc = loc;
		data.insert(e);
		this.c1 = null;
		this.c2 = null;
		this.c3 = null;
		this.c4 = null;
	}
}
public class TreeLocator<T> implements Locator<T> {

	TreeLocatorNode<T> root;
	TreeLocatorNode<T> current;

	public TreeLocator() {
		current = root = null;
	}

	@Override
	public int add(T e, Location loc) {
		TreeLocatorNode<T> p = root;
		TreeLocatorNode<T> q = root;
		int compare = 0;
		if (root == null) {
			root = current = new TreeLocatorNode<T>(loc, e);
			return 0;
		}
		while(p != null){
			q = p;
			compare++;
			if(loc.x == p.loc.x && loc.y == p.loc.y){
				p.data.insert(e);
				return compare;
			}else if(loc.x <= p.loc.x && loc.y > p.loc.y){
				p =  p.c2;
			}else if(loc.x > p.loc.x && loc.y >= p.loc.y){
				p = p.c3;
			}else if(loc.x >= p.loc.x && loc.y < p.loc.y){
				p = p.c4;
			}else if(loc.x < p.loc.x && loc.y <= p.loc.y){
				p = p.c1;
			}
		}
		TreeLocatorNode<T> n = new TreeLocatorNode<T>(loc, e);
		if(loc.x < q.loc.x && loc.y <= q.loc.y){
			q.c1 = n;
		}else if(loc.x <= q.loc.x && loc.y > q.loc.y){
			q.c2 = n;
		}else if(loc.x > q.loc.x && loc.y >= q.loc.y){
			q.c3 = n;
		}else if(loc.x >= q.loc.x && loc.y < q.loc.y){
			q.c4 = n;
		}
		return compare;
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		TreeLocatorNode<T> p = root;
		int compare = 0;
		if(root == null){
			return new Pair<List<T>,Integer>(new LinkedList<T>(), 0);
		}
		while(p != null){
			compare++;
			if(loc.x == p.loc.x && loc.y == p.loc.y){
				return new Pair<List<T>,Integer>(p.data, compare);
			}else if(loc.x <= p.loc.x && loc.y > p.loc.y){
				p =  p.c2;
			}else if(loc.x > p.loc.x && loc.y >= p.loc.y){
				p = p.c3;
			}else if(loc.x >= p.loc.x && loc.y < p.loc.y){
				p = p.c4;
			}else if(loc.x < p.loc.x && loc.y <= p.loc.y){
				p = p.c1;
			}
		}
		return new Pair<List<T>,Integer>(new LinkedList<T>(), compare);
	}
	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		if (root == null) {
			return new Pair<Boolean,Integer>(false, 0);
		}
		TreeLocatorNode<T> p = root;
		int compare = 0;
		while(p != null){
			compare++;
			if(loc.x == p.loc.x && loc.y == p.loc.y){
				if(removeSpecified(p.data, e)){
					return new Pair<Boolean,Integer>(true, compare);
				}else {
					return new Pair<Boolean,Integer>(false, 0);
				}
			}else if(loc.x <= p.loc.x && loc.y > p.loc.y){
				p =  p.c2;
			}else if(loc.x > p.loc.x && loc.y >= p.loc.y){
				p = p.c3;
			}else if(loc.x >= p.loc.x && loc.y < p.loc.y){
				p = p.c4;
			}else if(loc.x < p.loc.x && loc.y <= p.loc.y){
				p = p.c1;
			}
		}
		return new Pair<Boolean,Integer>(false, compare);
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> res = new LinkedList<Pair<Location, List<T>>>();
		if (root == null){
			return res;
		}
		preOrder(root, res);
		return res;
	}
	private void preOrder(TreeLocatorNode<T> p , List<Pair<Location, List<T>>> res){
		if(p == null){
			return;
		}else {
			res.insert(new Pair<Location,List<T>>(p.loc,p.data));
			preOrder(p.c1, res);
			preOrder(p.c2, res);
			preOrder(p.c3, res);
			preOrder(p.c4, res);
		}
	}
		
	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		return null;
	}
	private boolean removeSpecified(List<T> list,T e){
		boolean f = false;
		list.findFirst();
		while(!list.last()){
			if(list.retrieve().equals(e)){
				list.remove();
				f = true;
			}else {
				list.findNext();
			}
		}
		if(list.retrieve().equals(e)){
			list.remove();
			f = true;
		}
		return f;
	}
}
