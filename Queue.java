//public class Queue{
//  public LLNode end;
//  
//  //constructor
//  public Queue(){
//    end = null;
//  }
//  
//  /***************************************************************************
//    * INPUT: (Node) in: the Node to be entered into the queue 
//    * METHOD: 
//    * ************************************************************************/
//  public void enqueue(Node in){
//    if(end == null){
//      end = new LLNode(in, null);
//      end.next = end;
//      System.out.println(end.next);
//    }
//    else{
//      
//      LLNode newNode = new LLNode(in, end.next);
//      
//      end.next = newNode;
//      end = newNode;
//      
//    }
//  }
//  
//  /***************************************************************************
//    * METHOD: Remove from queue
//    * OUTPUT: The Node that was removed from the queue
//    * ************************************************************************/
//  public Node dequeue(){
//    Node removed = null;
//    if(end != null){
//      removed = end.next.data;
//    
//      if(end.next == end)
//        end = null;
//      else
//        end.next = end.next.next;
//    }
//    return removed;
//  }
//  
//  public String toString(){
//    String s = "< ";
//    if(end != null){
//      
//      LLNode curr = end.next;
//      
//      s += curr.data + " " ;
//      curr = curr.next;
//      
//      while(curr != end.next){
//        s += curr.data + " ";
//        curr = curr.next;
//      }
//    }
//    return s + " <";
//  }
//  
//  /***************************************************************************
//  * METHOD: checks if the Queue is empty
//  * ************************************************************************/
//  public boolean isEmpty(){
//    return end == null;
//  }
//  
//  /***************************************************************************
//  * METHOD: peek at the first item in the queue without removing it 
//  * ************************************************************************/
//  public Node peek(){
//    Node peek = null;
//    if(end != null)
//      peek = end.next.data;
//    return peek;
//  }
//  
//    /***************************************************************************
//  * METHOD: peek at the second item in the queue without removing it 
//  * ************************************************************************/
//  public Node peek2(){
//    Node peek = null;
//    if(end != null && end.next != end)
//      peek = end.next.next.data;
//    return peek;
//  }
//}
//
//class LLNode{
//  public Node data;
//  public LLNode next;
//  
//  //constructors
//  
//  
//  public LLNode(Node data, LLNode next){
//    this.data = data;
//    this.next = next;
//  }
//}