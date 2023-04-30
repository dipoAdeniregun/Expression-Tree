//public class Stack{
//  public LLNode top;
//  
//  //constructors
//  public Stack(){ top = null;}
//  
//  /***************************************************************************
//    * INPUT: (Node) in: the Node to be pushed unto the stack 
//    * METHOD: 
//    * ************************************************************************/
//  public void push(Node in){
//    top = new LLNode(in,top);
//  }
//  
//  /***************************************************************************
//    * METHOD: pop from stack
//    * OUTPUT: The Node that was popped from the stack
//    * ************************************************************************/
//  public Node pop(){
//    Node popped = null;
//    if(top != null){
//      popped = top.data;
//      top = top.next;
//    }
//    return popped;
//  }
//  
//    /***************************************************************************
//  * METHOD: peek at the first item in the stack without removing it 
//  * ************************************************************************/
//  public Node peek(){
//    Node peek = null;
//    if(top != null)
//      peek = top.data;
//    return peek;
//  }
//  
//    /***************************************************************************
//  * METHOD: check if the stack is empty 
//  * ************************************************************************/
//  public boolean isEmpty(){return top == null;}
//  
//  public String toString(){
//    String s = "<";
//    LLNode curr = top;
//    while(curr != null){
//      s += curr.data + " ";
//      curr = curr.next;
//    }
//    return s + "]";
//  }
//}