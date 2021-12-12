import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KdTreePointST<Value> implements PointST<Value> {
    private Node root;
    private int size;
    
    // 2d-tree (generalization of a BST in 2d) representation.
    private class Node {
        private Point2D p;   // the point
        private Value val;   // the symbol table maps the point to this value
        private RectHV rect; // the axis-aligned rectangle corresponding to 
                             // this node
        private Node lb;     // the left/bottom subtree
        private Node rt;     // the right/top subtree

        // Construct a node given the point, the associated value, and the 
        // axis-aligned rectangle corresponding to the node.
        Node(Point2D p, Value val, RectHV rect) {
            this.p = p;
            this.val = val;
            this.rect = rect;
        }
    }

    // Construct an empty symbol table of points.
    public KdTreePointST() {
        root = null;
        size = 0;
    }

    // Is the symbol table empty?
    public boolean isEmpty() { 
        return size == 0;
    }

    // Number of points in the symbol table.
    public int size() {
        return size;
    }

    // Associate the value val with point p.
    public void put(Point2D p, Value val) {
        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
        root = put(root, p, val, rect, true);
    }

    // Helper for put(Point2D p, Value val).
    private Node put(Node x, Point2D p, Value val, RectHV rect, boolean lr) {
        if (x == null) {
            size++;
            return new Node(p, val, rect);
        }
        
        else if (x.p.x() == p.x() && x.p.y() == p.y()) return x;
        
        if (lr) {
            double compare = p.x() - x.p.x();
            if (compare < 0) {
                rect = new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax());
                x.lb = put(x.lb, p, val, rect, !lr);
            }
            else {
                rect = new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
                x.rt = put(x.rt, p, val, rect, !lr);
            }
        }
        
        else {
            double compare = p.y() - x.p.y();
            if (compare < 0) {
                rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y());
                x.lb = put(x.lb, p, val, rect, !lr);
            }
            else {
                rect = new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax());
                x.rt = put(x.rt, p, val, rect, !lr);
            }
        }
        
        return x;
    }

    // Value associated with point p.
    public Value get(Point2D p) {
        return get(root, p, true);
    }

    // Helper for get(Point2D p).
    private Value get(Node x, Point2D p, boolean lr) {
        if (x == null) return null;
        if (x.p.compareTo(p) == 0) return x.val;
        
        if (lr) {
            double compare = p.x() - x.p.x();
            if (compare < 0) {
                return get(x.lb, p, !lr);
            }
            else if (compare > 0){
                return get(x.rt, p, !lr);
            }
            else {
                return get(x, p, !lr);
            }
        }
        else {
            double compare = p.y() - x.p.y();
            if (compare < 0) {
                return get(x.lb, p, !lr);
            }
            else if (compare > 0){
                return get(x.rt, p, !lr);
            }
            else {
                return get(x, p, !lr);
            }
        }
    }

    // Does the symbol table contain the point p?
    public boolean contains(Point2D p) {
        return get(p) != null;
    }

    // All points in the symbol table, in level order.
    public Iterable<Point2D> points() {
        Queue<Point2D> q = new Queue();
        Queue<Node> temp = new Queue();
        
        temp.enqueue(root);
        while (!temp.isEmpty()) {
            Node curr = temp.dequeue();
            if (curr != null) {
                q.enqueue(curr.p);
                temp.enqueue(curr.lb);
                temp.enqueue(curr.rt);
            }
        }
        
        return q;
    }

    // All points in the symbol table that are inside the rectangle rect.
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> q = new Queue();
        range(root, rect, q);
        return q;
    }

    // Helper for public range(RectHV rect).
    private void range(Node x, RectHV rect, Queue<Point2D> q) {
        if (x == null) return;
        
        if (rect.contains(x.p)) q.enqueue(x.p);
        
        if (rect.intersects(x.rect)) {
            range(x.lb, rect, q);
            range(x.rt, rect, q);
        }
    }

    // A nearest neighbor to point p; null if the symbol table is empty.
    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        
        return nearest(root, p, root.p, Double.MAX_VALUE, true);
    }
    
    // Helper for public nearest(Point2D p).
    private Point2D nearest(Node x, Point2D p, Point2D nearest, 
                            double nearestDistance, boolean lr) {
        if (x == null) return nearest;
        
        Point2D closest = new Point2D(nearest.x(), nearest.y());
        double closestDistance = nearestDistance;
        
        double distance = x.p.distanceSquaredTo(p);
        if (distance < closestDistance) {
            closest = x.p;
            closestDistance = distance;
        }
        
        
        if ((lr && (p.x() < x.p.x())) || (!lr && (p.y() < x.p.y()))){
            if (x.lb != null && x.lb.rect.distanceSquaredTo(p) < closestDistance)
                closest = nearest(x.lb, p, closest, closestDistance, !lr);
            if (x.rt != null && x.rt.rect.distanceSquaredTo(p) < closestDistance)
                closest = nearest(x.rt, p, closest, closestDistance,!lr);
        }
        else {
            if (x.rt != null && x.rt.rect.distanceSquaredTo(p) < closestDistance)
            closest = nearest(x.rt, p, closest, closestDistance, !lr);
            if (x.lb != null && x.lb.rect.distanceSquaredTo(p) < closestDistance)
            closest = nearest(x.lb, p, closest, closestDistance, !lr);
        }
        
        
        return closest;
    }

    // k points that are closest to point p.
    public Iterable<Point2D> nearest(Point2D p, int k) {
        MaxPQ<Point2D> pq = new MaxPQ();
        nearest(root, p, k, pq, true);
        while (pq.size() != k) pq.delMax();
        return pq;
    }

    // Helper for public nearest(Point2D p, int k).
    private void nearest(Node x, Point2D p, int k, MaxPQ<Point2D> pq, 
                         boolean lr) {
        if (x == null) return;
        if (pq.isEmpty()) pq.insert(x.p);
        
        if (x.p.distanceTo(p) < pq.max().distanceTo(p)) {
            pq.insert(x.p);
        }
        if (x.rect.distanceTo(p) < pq.max().distanceTo(p)) {
            if ((lr && (p.x() < x.p.x())) || (!lr && (p.y() < x.p.y()))){
                nearest(x.lb, p, k, pq, !lr);
                nearest(x.rt, p, k, pq, !lr);
            }
            else {
                nearest(x.rt, p, k, pq, !lr);
                nearest(x.lb, p, k, pq, !lr);
            }
        }
        
        return;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        KdTreePointST<Integer> st = new KdTreePointST<Integer>();
        double qx = Double.parseDouble(args[0]);
        double qy = Double.parseDouble(args[1]);
        double rx1 = Double.parseDouble(args[2]);
        double rx2 = Double.parseDouble(args[3]);
        double ry1 = Double.parseDouble(args[4]);
        double ry2 = Double.parseDouble(args[5]);
        int k = Integer.parseInt(args[6]);
        Point2D query = new Point2D(qx, qy);
        RectHV rect = new RectHV(rx1, ry1, rx2, ry2);
        int i = 0;
        while (!StdIn.isEmpty()) {
            double x = StdIn.readDouble();
            double y = StdIn.readDouble();
            Point2D p = new Point2D(x, y);
            st.put(p, i++);
        }
        StdOut.println("st.empty()? " + st.isEmpty());
        StdOut.println("st.size() = " + st.size());
        StdOut.println("First " + k + " values:");
        i = 0;
        for (Point2D p : st.points()) {
            StdOut.println("  " + st.get(p));
            if (i++ == k) {
                break;
            }
        }
        StdOut.println("st.contains(" + query + ")? " + st.contains(query));
        StdOut.println("st.range(" + rect + "):");
        for (Point2D p : st.range(rect)) {
            StdOut.println("  " + p);
        }
        StdOut.println("st.nearest(" + query + ") = " + st.nearest(query));
        StdOut.println("st.nearest(" + query + ", " + k + "):");
        for (Point2D p : st.nearest(query, k)) {
            StdOut.println("  " + p);
        }
    }
}
