import java.util.*;
import java.io.*;

enum NodeType{
  VARIABLE,
  OPERAND,
  OPERATOR
}

public class AdeniregunAdedipupoA4Q1{
  /***************************************************************************
  * METHOD: Pass the input file containing the expression tree commands to a 
  *         scanner which is used to process each command either by calling the 
  *         required Tree.java method or by printing to the console
  * ************************************************************************/
  public static void main(String[] args){
    try{
      File in = new File("ExpressionTree.txt");
      Scanner sc = new Scanner(in);
      String next;
      Tree t1 = null;
      
      do{
        next = sc.next();
        switch (next){
          //have to use a substring ignoring 1st character because its an empty space
          //strictly for formatting purposes, works without the substring
          case "COMMENT":{
            System.out.println(sc.nextLine().substring(1));
          }break;
          case "NEW":{
            //have to use substring to remove the leading whitespace
            //without the substring, the constructor fails due to a 
            //leading null character that isn't processed in our constructor method
            String s = sc.nextLine();
            t1 = new Tree(s.substring(1).split(" "));
            System.out.println("New tree constrcuted");
          }break;
          case "PRINTINFIX":{
            if(t1 != null)
              t1.printInfix();
          }break;
          case "PRINTPREFIX":{
            if(t1 != null)
              t1.printPrefix();
          }break;
          case "PRINPOSTFIX":{
            if(t1 != null)
              t1.printPostfix();
          }break;
          case "SIMPLIFY":{
            if(t1 != null){
              t1.simplify();
              System.out.println("Tree simplified");
            }
          }break;
        }
      }
      while(sc.hasNext());
    }
    catch(IOException e){
      System.out.println("Error opening file: " +e.getMessage());
    }
  }
  
}

/*****************************CLASS: TREE*****************************************************************/
class Tree{
  public Node root;
  
  /***************************************************************************
    * INPUT: 
    * METHOD: 
    * OUTPUT: 
    * ************************************************************************/
  public Tree(String[] in){
    Node[] nodeIn = new Node[in.length];
    for(int i = 0; i < in.length; i++){
      nodeIn[i] = new Node(in[i]);
      //System.out.println("character: " + in[i] + " to node: " + nodeIn[i]);
    }
    
    if(nodeIn[0].type == NodeType.OPERATOR)
      
      root = constructPrefix(nodeIn);
    else
      root = constructPostfix(nodeIn);
  }
 
  
  /***************************************************************************
    * METHOD: Prints the expression tree as an infix expression using inorder
    *         traversal to access the nodes in the right order.
    * ************************************************************************/
  public void printInfix(){
    if(root != null){
      root.inorderTraversal();
      System.out.println("");
    }
  }
  
  /***************************************************************************
    * METHOD: Prints the expression tree as a postfix expression using postorder
    *         traversal to access the nodes in the right order.
    * ************************************************************************/
  public void printPostfix(){
    if(root != null){
      root.postorderTraversal();
      System.out.println("");
    }
  }
  
 /***************************************************************************
    * METHOD: Prints the expression tree as a prefix expression using preorder
    *         traversal to access the nodes in the right order.
    * ************************************************************************/
  public void printPrefix(){
    if(root != null){
      root.preorderTraversal();
      System.out.println("");
    }
  }
  
  /***************************************************************************
    * METHOD: call a recursive node function on the root to simplify the expression tree
    * ************************************************************************/
  public void simplify(){
    root.simplify();
  }
  
  /***************************************************************************
    * INPUT: in(Node[]) - An array of Nodes containing all characters in the 
    *                     prefix expression
    * METHOD: If the current Node in the expression is an operand, push it unto the stack
    *         but if it is an operator, pop two items from the stack and set them as
    *         the operands children ten push operant unto stack
    * OUTPUT: Node - The final Node popped from the stack. Should be the root for the
    *                expression tree.
    * ************************************************************************/
  private Node constructPrefix(Node[] in){
    Queue q1 = new Queue();
    for(Node n: in){
      //System.out.println("adding node to queue: " + n);
      q1.enqueue(n);
    }
    
    while(q1.peek2() != null){
      Node curr = q1.dequeue();
      //System.out.println("Node being processed: " + curr);
      if(curr.isVariable() || (!curr.isVariable() && curr.hasTwoChildren())){
        //System.out.println("Node is a variable, adding back to queue");
        q1.enqueue(curr);
      }
      else{
        Node first = q1.peek();
        Node second = q1.peek2();
        if((first.isVariable() || (!first.isVariable() && first.hasTwoChildren()))
             && (second.isVariable() || (!second.isVariable() && second.hasTwoChildren()))){
          //System.out.println("Node is an operator and queue has two operands or operators with two children");
          curr.left = q1.dequeue();
          curr.right = q1.dequeue();
          q1.enqueue(curr);
        }
        else
          q1.enqueue(curr);
      }
    }
    return q1.dequeue();
  }
  
  /***************************************************************************
    * INPUT: in(Node[]) - An array of Nodes containing all characters in the 
    *                     postfix expression
    * METHOD: If the current Node in the expression is an operand, push it unto the stack
    *         but if it is an operator, pop two items from the stack and set them as
    *         the operands children ten push operant unto stack
    * OUTPUT: Node - The final Node popped from the stack. Should be the root for the
    *                expression tree.
    * ************************************************************************/
  private Node constructPostfix(Node[] in){
    Stack s1 = new Stack();
    for(Node curr: in){
      if(curr.type == NodeType.OPERATOR){
        curr.right = s1.pop();
        curr.left = s1.pop();
        s1.push(curr);
      }
      else{
        s1.push(curr);
      }
    }
    return s1.pop();
  }
}



/**************************************************CLASS: NODE********************************************/
//expression tree Node. Not to be confused with LLNode, a linked list node
//for the stack and queue
class Node{
  //variables
  public NodeType type;
  public char operator;
  public int operand;
  public String variableName;
  public Node left;
  public Node right;
  
  //constructors
  /***************************************************************************
    * METHOD: uses the newNode helper methode to initialize the right variables
    *         depending on what NodeType is being stored in the node
    * ************************************************************************/
  public Node(String nodeItem){
    if((nodeItem.charAt(0) == '*') || (nodeItem.charAt(0) == '+') || 
       (nodeItem.charAt(0) == '-') || (nodeItem.charAt(0) == '^'))
      newNode(NodeType.OPERATOR, nodeItem);
    else if(nodeItem.matches("[0-9]+"))
      newNode(NodeType.OPERAND, nodeItem);
    else
      newNode(NodeType.VARIABLE, nodeItem);
  }
  
  /***************************************************************************
    * INPUT: type (NodeType) - an ENUM type to specify if the Node contains an
    *                          operator, operan or variable name
    *        nodeItem (String) - a string containing the operator, operand or string to
    *                            be stored in the Node
    * METHOD: depending on the type, an int, char or String is created to store the operand,
    *         operator or Variable Name using a switch statement.
    * ************************************************************************/
  private void newNode(NodeType type, String nodeItem){
    this.type = type;
    switch(type){
      case OPERAND:{
        operand = Integer.parseInt(nodeItem);
      }
      break;
      case OPERATOR:{
        operator = nodeItem.charAt(0);
      }
      break;
      case VARIABLE:{
        variableName = nodeItem;
      }
      break;
    }
    left = null;
    right = null;
  }//Node
  
  /***************************************************************************
    * METHOD: return a string containing the item stored in the node
    * OUTPUT: (String) s
    * ************************************************************************/
  public String toString(){
    String s = "";
    switch(type){
      case OPERAND:
        s += operand;
        break;
      case OPERATOR:
        s += operator;
        break;
      case VARIABLE:
        s = variableName;
        break;
    }
    return s;
  }//toString
  
  /***************************************************************************
    * METHOD: A recursive postorder traversal that prints the contents of the node
    * ************************************************************************/
  public void postorderTraversal(){
    if(left != null)
      left.postorderTraversal();
    if(right != null)
      right.postorderTraversal();
    System.out.print(toString() + " ");
  }
  
  /***************************************************************************
    * METHOD: A recursive preorder traversal that prints the contents of the node
    * ************************************************************************/
  public void preorderTraversal(){
    System.out.print(toString() + " ");
    if(left != null)
      left.preorderTraversal();
    if(right != null)
      right.preorderTraversal();
  }
  
  /***************************************************************************
    * METHOD: A recursive inorder traversal that prints the contents of the node
    * ************************************************************************/
  public void inorderTraversal(){
    if (type == NodeType.OPERATOR)
      System.out.print("(");
    if(left != null)
      left.inorderTraversal();
    System.out.print(toString() + " ");
    if(right != null)
      right.inorderTraversal();
    if (type == NodeType.OPERATOR)
      System.out.print(")");
  }
  
  /***************************************************************************
  * METHOD: checks if the Node has two children that aren't null
  * ************************************************************************/
  public boolean hasTwoChildren(){
    return(left != null) && (right != null);
  }
  
  /***************************************************************************
  * METHOD: checks if the Node is either a varible name or an operand
  * ************************************************************************/
  public boolean isVariable(){
    return (type == NodeType.OPERAND) || (type == NodeType.VARIABLE);
  }
  
  /***************************************************************************
  * METHOD: Recursively simplifies an expression tree. Does this by using a switch statement
  *         to perform the right operation between two operands. if one of the children 
  *         isn't an operand but a variable or another operator with two children, 
  *         simplifies the expression based on the trivial cases for addition, subtraction 
  *         multiplication and exponents
  * ************************************************************************/
  public void simplify(){
    if(left != null && left.hasTwoChildren())
      left.simplify();
    if(right != null && right.hasTwoChildren())
      right.simplify();
    if(left != null && right != null){
      //if both children are operands, simply perform the required math opration
      if(left.type == NodeType.OPERAND && right.type == NodeType.OPERAND && type == NodeType.OPERATOR){
        switch (operator){
          case '+':{
            type = NodeType.OPERAND;
            operand = left.operand + right.operand;
          }
          break;
          case '-':{
            type = NodeType.OPERAND;
            operand = left.operand - right.operand;
          }
          break;
          case '*':{
            type = NodeType.OPERAND;
            operand = left.operand * right.operand;
          }
          break;
          case '^':{
            type = NodeType.OPERAND;
            //casting necessary to avoid lossy conversion error
            operand = (int) Math.pow(left.operand, right.operand);
          }
          break;
        }
        operator = '\0';
        left = null;
        right = null;
      }
      //if one child is an operand and the other is a variable or subtree with an operator root
      //check for special cases starting with operand as right child
      else if(type == NodeType.OPERATOR && left.type != NodeType.OPERAND && right.type == NodeType.OPERAND){
        //add or subtract 0 special case
        if((operator == '+' || operator == '-') && right.operand == 0){
          variableName = left.variableName;
          operator = left.operator;
          type = left.type;
          right = left.right;
          left = left.left;
          
        }
        else if(operator == '*' || operator == '^'){
          //multiplying by or raising to power 1 special case
          if(right.operand == 1){
            variableName = left.variableName;
            operator = left.operator;
            type = left.type;
            right = left.right;
            left = left.left;
            
          }
          //multiplying by or raising to power 0 special case
          else if(right.operand == 0){
            operand = (operator == '*') ? 0 : 1;
            type = NodeType.OPERAND;
            operator = '\0';
            left = null;
            right = null;
          }
        }
        
      }
      //if one child is an operand and the other is a variable or subtree with an operator root
      //check for special cases ending with operand as right child
      else if(type == NodeType.OPERATOR && left.type == NodeType.OPERAND && right.type != NodeType.OPERAND){
        if((operator == '+' || operator == '-') && left.operand == 0){
          variableName = right.variableName;
          operator = right.operator;
          type = right.type;
          left = right.left;
          right = right.right;
        }
        else if(operator == '*' || operator == '^'){
          //multiplying by or rasing to 1 special case
          if(left.operand == 1){
            variableName = right.variableName;
            operator = right.operator;
            type = right.type;
            left = right.left;
            right = right.right;
          }
          //multiplying by or raising to 0 special case
          else if(left.operator == 0){
            operand = (operator == '*') ? 0 : 1;
            type = NodeType.OPERAND;
            operator = '\0';
            left = null;
            right = null;
          }
        }
      }
    }
  }  
}//Node class end



  /***************************************CLASS: QUEUE********************************************************/
class Queue{
  public LLNode end;
  
  //constructor
  public Queue(){
    end = null;
  }
  
  /***************************************************************************
    * INPUT: (Node) in: the Node to be entered into the queue 
    * METHOD: 
    * ************************************************************************/
  public void enqueue(Node in){
    if(end == null){
      end = new LLNode(in, null);
      end.next = end;
      System.out.println(end.next);
    }
    else{
      
      LLNode newNode = new LLNode(in, end.next);
      
      end.next = newNode;
      end = newNode;
      
    }
  }
  
  /***************************************************************************
    * METHOD: Remove from queue
    * OUTPUT: The Node that was removed from the queue
    * ************************************************************************/
  public Node dequeue(){
    Node removed = null;
    if(end != null){
      removed = end.next.data;
    
      if(end.next == end)
        end = null;
      else
        end.next = end.next.next;
    }
    return removed;
  }
  
  public String toString(){
    String s = "< ";
    if(end != null){
      
      LLNode curr = end.next;
      
      s += curr.data + " " ;
      curr = curr.next;
      
      while(curr != end.next){
        s += curr.data + " ";
        curr = curr.next;
      }
    }
    return s + " <";
  }
  
  /***************************************************************************
  * METHOD: checks if the Queue is empty
  * ************************************************************************/
  public boolean isEmpty(){
    return end == null;
  }
  
  /***************************************************************************
  * METHOD: peek at the first item in the queue without removing it 
  * ************************************************************************/
  public Node peek(){
    Node peek = null;
    if(end != null)
      peek = end.next.data;
    return peek;
  }
  
    /***************************************************************************
  * METHOD: peek at the second item in the queue without removing it 
  * ************************************************************************/
  public Node peek2(){
    Node peek = null;
    if(end != null && end.next != end)
      peek = end.next.next.data;
    return peek;
  }
}



/****************************************CLASS: STACK*******************************************************/
class Stack{
  public LLNode top;
  
  //constructors
  public Stack(){ top = null;}
  
  /***************************************************************************
    * INPUT: (Node) in: the Node to be pushed unto the stack 
    * METHOD: 
    * ************************************************************************/
  public void push(Node in){
    top = new LLNode(in,top);
  }
  
  /***************************************************************************
    * METHOD: pop from stack
    * OUTPUT: The Node that was popped from the stack
    * ************************************************************************/
  public Node pop(){
    Node popped = null;
    if(top != null){
      popped = top.data;
      top = top.next;
    }
    return popped;
  }
  
    /***************************************************************************
  * METHOD: peek at the first item in the stack without removing it 
  * ************************************************************************/
  public Node peek(){
    Node peek = null;
    if(top != null)
      peek = top.data;
    return peek;
  }
  
    /***************************************************************************
  * METHOD: check if the stack is empty 
  * ************************************************************************/
  public boolean isEmpty(){return top == null;}
  
  public String toString(){
    String s = "<";
    LLNode curr = top;
    while(curr != null){
      s += curr.data + " ";
      curr = curr.next;
    }
    return s + "]";
  }
}



  /************************************CLASS: LLNODE****************************************************/
/*****************************************
  * A node for implementing stacks and queues using a linked list
  * ***************************************/
class LLNode{
  public Node data;
  public LLNode next;
  
  //constructors
  public LLNode(Node data, LLNode next){
    this.data = data;
    this.next = next;
  }
}