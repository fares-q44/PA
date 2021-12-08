class BSTNode <K extends Comparable<K>,T> {
	public K key;
	public T data;
	public BSTNode<K,T> left, right;
	
	public BSTNode(K k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}
}

public class BST<K extends Comparable<K>, T> implements Map<K, T> {

	BSTNode<K,T> root;
	BSTNode<K, T> current;

	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		int counter = 0;
		BSTNode<K,T> p = root;
		if (empty()) {
			return new Pair<Boolean,Integer>(false, 0);
		}
		while(p != null) {
			if (p.key.compareTo(key) == 0) {
				current = p;
				counter+=1;
				return new Pair<Boolean,Integer>(true,counter);
			}else if(p.key.compareTo(key) > 0){
				p = p.left;
				counter+=1;
			}else {
				p = p.right;
				counter+=1;
			}
		}
		return new Pair<Boolean,Integer>(false,counter);
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<K,T> p = root, q = root;
		Pair<Boolean,Integer> check = find(key);
		if (check.first) {
			return new Pair<Boolean,Integer>(false, find(key).second);
		}else if(empty()){
			current = root = new BSTNode<K,T>(key, data);
			return new Pair<Boolean,Integer>(true, find(key).second);
		}else {
			while(p != null) {
				q = p;
				if(p.key.compareTo(key) > 0)
					p = p.left;
				else
					p = p.right;
			}
			if(q.key.compareTo(key) > 0){
				q.left = current = new BSTNode<K,T>(key, data);
				return new Pair<Boolean,Integer>(true, find(key).second);
			}else {
				q.right = current = new BSTNode<K,T>(key, data);
				return new Pair<Boolean,Integer>(true, find(key).second);
			}
		}
	}
	@Override
	public Pair<Boolean, Integer> remove(K key) {
		Pair<Boolean, Integer> f = find(key);
		if (f.first == false)
			return new Pair<Boolean, Integer>(false, f.second);
		BSTNode<K, T> tmp = root;
		BSTNode<K, T> parent = null;
		K key1 = key;
		int comparison = 0;
		boolean flag = false;
		while (tmp != null && key.compareTo(tmp.key) != 0) {
			parent = tmp;
			if (key.compareTo(tmp.key) < 0) {
				tmp = tmp.left;
				comparison++;
			} else {
				tmp = tmp.right;
				comparison++;
			}
		}
		Pair<Boolean, Integer> check = new Pair<Boolean, Integer>(flag, comparison);
		if (tmp == null)
			return check;
		else {
			flag = true;
			// Remove an element --> Case 3
			if (tmp.left != null && tmp.right != null) {
				BSTNode<K, T> min = tmp.right;
				parent = tmp;
				while (min.left != null) {
					parent = min;
					min = min.left;
				}
				tmp.key = min.key;
				tmp.data = min.data;
				key = min.key;
				tmp = min;

			}
			// remove an element ---> Case 2 && 1
			if (tmp.left != null)
				tmp = tmp.left;
			else if (tmp.right != null)
				tmp = tmp.right;
			
			else{
				if (tmp.key.compareTo(parent.key) < 0) {
					comparison++;
					parent.left = null;
				} else
					parent.right = null;
				current = root;
			}
			check = new Pair<Boolean, Integer>(flag, comparison);
			return check;
		}
	}
	public void displayin(){
		if(root == null){
			System.out.println("Empty bst");
		}else{
			displayin(root);
		}
		System.out.println();
	}
	private void displayin(BSTNode<K,T> p){
		if(p != null){
			displayin(p.left);
			System.out.println(p.key+" "+p.data+",   ");
			displayin(p.right);
		}
	}

	@Override
	public List<K> getAll() {
		List<K> newList = new LinkedList<K>();
		BSTNode<K, T> t = root;
		getAll(newList, t);
		return newList;
	}
	private void getAll(List<K> t, BSTNode<K, T> tmp) {
		if (tmp == null)
			return;
		getAll(t, tmp.left);
		t.insert(tmp.key);
		getAll(t, tmp.right);
	}
}
