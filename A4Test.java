//import java.util.*;
//
//enum NodeType{
//  VARIABLE,
//  OPERAND,
//  OPERATOR
//}


public class A4Test{
  public static void main(String[] args){
    Node n1 = new Node("speed of light");
    Node n2 = new Node("5");
    Node n3 = new Node("*");
    
    System.out.println(n1 + ": " + n2 + " " + n3);
    
    Queue q1 = new Queue();
    q1.enqueue(n1);
    System.out.println("peek: " + q1.peek() + " peek2: " + q1.peek2());
    q1.enqueue(n2);
    System.out.println(q1.peek() + " " + q1.peek2());
    q1.enqueue(n3);
    System.out.println(q1.peek() + " " + q1.peek2());
    System.out.println(q1);
    System.out.println("peek: " + q1.peek() + " dequeue: " + q1.dequeue() );
    System.out.println("peek: " + q1.peek() + " peek2: " + q1.peek2());
    System.out.println("peek: " + q1.peek() + " dequeue: " + q1.dequeue()  );
    System.out.println("peek: " + q1.peek() + " peek2: " + q1.peek2());
    System.out.println("peek: " + q1.peek() + " dequeue: " + q1.dequeue()  );
    System.out.println("peek: " + q1.peek() + " peek2: " + q1.peek2());
    System.out.println("peek: " + q1.peek() + " dequeue: " + q1.dequeue()  );
    System.out.println("peek: " + q1.peek() + " peek2: " + q1.peek2());
    System.out.println(q1);
    
    Stack s1 = new Stack();
    s1.push(n1);
    s1.push(n2);
    s1.push(n3);
    System.out.println(s1);
    System.out.println("peek: " + s1.peek() + " pop: " + s1.pop());
    System.out.println("peek: " + s1.peek() + " pop: " + s1.pop());
    System.out.println("peek: " + s1.peek() + " pop: " + s1.pop());
    System.out.println("peek: " + s1.peek() + " pop: " + s1.pop());
    
    System.out.println("----------------------------------------------------------");
    String a = "^ * + C 3 - 5 4 - 3 2";
    
    Tree t1 = new Tree(a.split(" "));
    t1.printPostfix();
    System.out.print("\n");
    t1.printInfix();
    System.out.print("\n");
    t1.printPrefix();
    System.out.print("\n");
    t1.simplify();
    t1.printPrefix();
  }
}