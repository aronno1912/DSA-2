//import java.util.ArrayList;
//import java.util.List;
//
//public class Fib<T> {
//
//    static class heapElements<T>  {
//        int     degree = 0;
//        boolean isMarked = false;
//
//         heapElements<T> next;
//        heapElements<T> prev;
//        heapElements<T> parent; // Parent in the tree, if any.
//        heapElements<T> child;  // Child node, if any.
//          T     element;     // Element being stored here
//        double priority; // I
//
//        public heapElements(T elem, Double priority) {
//
//            next = prev = this;
//            element = elem;
//            this.priority = priority;
//        }
//
//        public void setValue(T value) {
//            element = value;
//        }
//
//    }
//    public heapElements<T> minE=null;
//    public int size=0;
//
//
//    //insert function
//
//    public heapElements<T> add(T value,double priority)
//    {
//        heapElements<T> result = new heapElements<T>(value, priority);
//        minE= mergeLists(minE,result);
//        ++size;
//        return result;
//    }
//
//    public boolean isEmpty() {
//        return minE == null;
//    }
//
//    public heapElements<T> min()
//    {    if(size==0) System.out.println("empty heap");
//        return minE;
//    }
//    public int heapSize()
//    {
//        return size;
//    }
//   //Union function
//
//    public static <T> Fib<T> union(Fib<T> one,Fib<T> two)
//    {
//        Fib<T> result=new Fib<T>();
//        result.minE=mergeLists(one.minE,two.minE);
//        result.size= one.size+ two.size;
//        one.size= two.size=0;
//        one.minE=null;
//        two.minE=null;
//        return result;
//    }
//
//     //remove Min function
//
//
//    public T extractMin()
//    {
//        if(size==0) System.out.println("empty heap");
//       // else {
//
//            heapElements<T> minElement = minE;
//            if (minE.next == minE) minE = null;
//
//            else {
//                // Case two
//                minE.prev.next = minE.next;
//                minE.next.prev = minE.prev;
//                minE = minE.next; // arbitrary element of the root list.
//            }
//            if(minElement.child!=null)
//            {
//            //clear the parent field of min element's children,now they will become root
//            heapElements<?> curr = minElement.child;
//            do {
//                curr.parent = null;
//                curr = curr.next;
//                } while (curr != minElement.child);//from where we start.
//
//            }
//
//            //set new minE
//
//            minE=mergeLists(minE,minE.child);
//            if (minE == null) return minElement.element;//done
//
//            List <heapElements<T>> treeTable=new ArrayList <heapElements<T>>();
//            List<heapElements<T>> toVisit = new ArrayList<heapElements<T>>();
//            for (heapElements<T> curr = minE; toVisit.isEmpty() || toVisit.get(0) != curr; curr = curr.next)
//            {
//                toVisit.add(curr);
//            }
//            for(heapElements<T> curr:toVisit)
//            {
//
//                while(true)
//                {
//
//                    while (curr.degree >= treeTable.size())
//                        treeTable.add(null);
//                    if (treeTable.get(curr.degree) == null) {
//                        treeTable.set(curr.degree, curr);
//                        break;
//                    }
//
//                    heapElements<T> t = treeTable.get(curr.degree);
//                    treeTable.set(curr.degree, null); // Clear the slot
//
//
//                    heapElements<T> min = (t.priority < curr.priority)? t : curr;
//                    heapElements<T> max = (t.priority < curr.priority)?  curr:t;
//
//                    //Take out the max from the list
//                    max.next.prev = max.prev;
//                    max.prev.next = max.next;
//
//                    //make max a single tree and add it to the min node's child
//                    max.next = max.prev = max;
//                    min.child = mergeLists(min.child, max);
//                    max.parent=min;
//                    max.isMarked=false;
//                    min.degree+=1;
//                    curr=min;
//
//
//                }
//                if (curr.priority <= minE.priority) minE = curr;
//
//
//            }
//              return minElement.element;
//        //}
//    }
//
//    //decrease key
//
//    public void decreaseKey(heapElements<T>entry, double newPriority) {
//
//            if (newPriority > entry.priority)
//            throw new IllegalArgumentException("New priority is not less than the old");
//            decreaseKeyHelper(entry, newPriority);
//    }
//
//    private void decreaseKeyHelper(heapElements<T> entry, double pr) {
//
//        entry.priority=pr;
//        if(entry.parent!=null&&entry.priority<=entry.parent.priority)
//            cut(entry);
//        if(entry.priority<=minE.priority)
//            minE=entry;
//
//    }
//
//    private void cut(heapElements<T> entry) {
//        entry.isMarked=false;
//        // Base case: If the node has no parent, we're done
//
//        if (entry.parent == null) return;
//
//        if (entry.next != entry) { // Has siblings
//            entry.next.prev = entry.prev;
//            entry.prev.next = entry.next;
//
//            //if parent's child pointer is that child,we need to point it to parent's any other child
//
//            if (entry.parent.child == entry) {
//                if (entry.next != entry) {
//                    entry.parent.child = entry.next; //any one
//                }
//                //else no child left
//                else {
//                    entry.parent.child = null;
//                }
//            }
//            (entry.parent.degree)--;
//            //now make entry a single node tree and add to rootlist
//            entry.prev=entry.next=entry;
//            minE=mergeLists(minE,entry);
//            //if parent is marked recursively cut
//            if(entry.parent.isMarked==true)
//            {
//                cut(entry.parent);
//            }
//            else
//            {
//                entry.parent.isMarked=true;
//            }
//            entry.parent=null;//as it is now at root list
//
//        }
//    }
//
//    public void delete(heapElements<T> e)
//    {
//        decreaseKeyHelper(e,Double.NEGATIVE_INFINITY);
//        extractMin();
//
//    }
//    public T top()
//    {
//        return minE.priority;
//    }
//
//    private static <T>heapElements<T> mergeLists(heapElements<T> e1, heapElements<T> e2) {
//        if (e1 == null && e2 == null) {
//            return null;
//        }
//        else if (e1!= null && e2 == null) {
//            return e1;
//        }
//        else if (e1 == null && e2 != null) {
//            return e2;
//        }
//        else
//        {
//             heapElements<T> oneNext=e1.next;
//            e1.next = e2.next;
//            e1.next.prev = e1;
//            e2.next = oneNext;
//            e2.next.prev = e2;
//            return e1.priority < e2.priority? e1 : e2;
//        }
//
//
//
//    }
//
//
//}
