//public class Tree{
//  public Node root;
//  
//  /***************************************************************************
//    * INPUT: 
//    * METHOD: 
//    * OUTPUT: 
//    * ************************************************************************/
//  public Tree(String[] in){
//    Node[] nodeIn = new Node[in.length];
//    for(int i = 0; i < in.length; i++){
//      nodeIn[i] = new Node(in[i]);
//      //System.out.println("character: " + in[i] + " to node: " + nodeIn[i]);
//    }
//    
//    if(nodeIn[0].type == NodeType.OPERATOR)
//      
//      root = constructPrefix(nodeIn);
//    else
//      root = constructPostfix(nodeIn);
//  }
// 
//  
//  /***************************************************************************
//    * METHOD: Prints the expression tree as an infix expression using inorder
//    *         traversal to access the nodes in the right order.
//    * ************************************************************************/
//  public void printInfix(){
//    if(root != null){
//      root.inorderTraversal();
//      System.out.println("");
//    }
//  }
//  
//  /***************************************************************************
//    * METHOD: Prints the expression tree as a postfix expression using postorder
//    *         traversal to access the nodes in the right order.
//    * ************************************************************************/
//  public void printPostfix(){
//    if(root != null){
//      root.postorderTraversal();
//      System.out.println("");
//    }
//  }
//  
// /***************************************************************************
//    * METHOD: Prints the expression tree as a prefix expression using preorder
//    *         traversal to access the nodes in the right order.
//    * ************************************************************************/
//  public void printPrefix(){
//    if(root != null){
//      root.preorderTraversal();
//      System.out.println("");
//    }
//  }
//  
//  /***************************************************************************
//    * METHOD: call a recursive node function on the root to simplify the expression tree
//    * ************************************************************************/
//  public void simplify(){
//    root.simplify();
//  }
//  
//  /***************************************************************************
//    * INPUT: in(Node[]) - An array of Nodes containing all characters in the 
//    *                     prefix expression
//    * METHOD: If the current Node in the expression is an operand, push it unto the stack
//    *         but if it is an operator, pop two items from the stack and set them as
//    *         the operands children ten push operant unto stack
//    * OUTPUT: Node - The final Node popped from the stack. Should be the root for the
//    *                expression tree.
//    * ************************************************************************/
//  private Node constructPrefix(Node[] in){
//    Queue q1 = new Queue();
//    for(Node n: in){
//      //System.out.println("adding node to queue: " + n);
//      q1.enqueue(n);
//    }
//    
//    while(q1.peek2() != null){
//      Node curr = q1.dequeue();
//      //System.out.println("Node being processed: " + curr);
//      if(curr.isVariable() || (!curr.isVariable() && curr.hasTwoChildren())){
//        //System.out.println("Node is a variable, adding back to queue");
//        q1.enqueue(curr);
//      }
//      else{
//        Node first = q1.peek();
//        Node second = q1.peek2();
//        if((first.isVariable() || (!first.isVariable() && first.hasTwoChildren()))
//             && (second.isVariable() || (!second.isVariable() && second.hasTwoChildren()))){
//          //System.out.println("Node is an operator and queue has two operands or operators with two children");
//          curr.left = q1.dequeue();
//          curr.right = q1.dequeue();
//          q1.enqueue(curr);
//        }
//        else
//          q1.enqueue(curr);
//      }
//    }
//    return q1.dequeue();
//  }
//  
//  /***************************************************************************
//    * INPUT: in(Node[]) - An array of Nodes containing all characters in the 
//    *                     postfix expression
//    * METHOD: If the current Node in the expression is an operand, push it unto the stack
//    *         but if it is an operator, pop two items from the stack and set them as
//    *         the operands children ten push operant unto stack
//    * OUTPUT: Node - The final Node popped from the stack. Should be the root for the
//    *                expression tree.
//    * ************************************************************************/
//  private Node constructPostfix(Node[] in){
//    Stack s1 = new Stack();
//    for(Node curr: in){
//      if(curr.type == NodeType.OPERATOR){
//        curr.right = s1.pop();
//        curr.left = s1.pop();
//        s1.push(curr);
//      }
//      else{
//        s1.push(curr);
//      }
//    }
//    return s1.pop();
//  }
//  
//  
//}