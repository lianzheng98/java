package com.pg.cc.sb.core.alg;

import lombok.AllArgsConstructor;
import lombok.Data;


public class BPlusTree<Key extends Comparable<Key>, Value> {

    private static final int M = 4;
    private Node root;
    private int height;
    private int n;

    private static final class Node {
        int m;
        Entry[] children = new Entry[M];

        private Node(int k) {
            m = k;
        }
    }

    @Data
    @AllArgsConstructor
    private static class Entry {
        Comparable key;
        Object val;
        Node next;

    }

    public BPlusTree() {
        this.root = new Node(0);
    }

    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) {
                    s.append(indent + "(" + children[j].key + ")\n");
                }
                s.append(toString(children[j].next, ht - 1, indent + "   "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return search(root, key, height);
    }

    private Value search(Node h, Key key, int ht) {
        Entry[] children = h.children;

        if (ht == 0) {
            for (int i = 0; i < h.m; i++) {
                if (eq(key, children[i].key)) {
                    return (Value) children[i].val;
                }
            }
        } else {
            for (int j = 0; j < h.m; j++) {
                if (j + 1 == h.m || less(key, children[j + 1].key)) {
                    return search(children[j].next, key, ht - 1);
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {

        BPlusTree<Integer, Integer> tree = new BPlusTree<>();
        for (int i = 0; i < 10; i++) {
            tree.put(i, i);
            System.out.println(tree);
        }

    }

    private void put(Key key, Value value) {
        if (key == null) {
            return;
        }
        Node u = insert(root, key, value, height);
        n++;
        if (u == null) {
            return;
        }
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node h, Key key, Value value, int ht) {
        int j;
        Entry t = new Entry(key, value, null);
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) {
                    break;
                }
            }
        } else {
            for (j = 0; j < h.m; j++) {
                if ((j + 1 == h.m) || less(key, h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next, key, value, ht - 1);
                    if (u == null) {
                        return null;
                    }
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }
        for (int i = h.m; i > j; i--) {
            h.children[i] = h.children[i - 1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) {
            return null;
        } else {
            return split(h);
        }


    }

    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int i = 0; i < M / 2; i++) {
            t.children[i] = h.children[M / 2 + i];
        }
        return t;
    }
}
