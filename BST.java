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
		boolean isRemoved = false;
		int counter = 0;
		K key1 = key;
        BSTNode<K, T> p = root;
        BSTNode<K, T> q = null;
        while (p != null) {
        	counter++;
            if (key1.compareTo(p.key) < 0) {
                q =p;
                p = p.left;
            } else if (key1.compareTo(p.key) > 0) {
                q = p;
                p = p.right;
            } else {
                if ((p.left != null) && (p.right != null)) {
                    BSTNode<K, T> min = p.right;
                    q = p;
                    while (min.left != null) {
                        q = min;
                        min = min.left;
                    }
                    p.key = min.key;
                    p.data = min.data;
                    key1 = min.key;
                    p = min;
                }
                if (p.left != null) {
                    p = p.left;
                } else {
                    p = p.right;
                }

                if (q == null) {
                    root = p;
                } else {
                    if (key1.compareTo(q.key) < 0) {
                        q.left = p;
                    } else {
                        q.right = p;
                    }
                }
                current = root;
                isRemoved = true;
                break;
            }
        }
        return new Pair<Boolean, Integer>(isRemoved, counter);
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
