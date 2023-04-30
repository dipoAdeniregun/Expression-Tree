//public class Node{
//  //variables
//  public NodeType type;
//  public char operator;
//  public int operand;
//  public String variableName;
//  public Node left;
//  public Node right;
//  
//  //constructors
//  /***************************************************************************
//    * INPUT: 
//    * METHOD: 
//    * OUTPUT: 
//    * ************************************************************************/
//  public Node(String nodeItem){
//    if((nodeItem.charAt(0) == '*') || (nodeItem.charAt(0) == '+') || 
//       (nodeItem.charAt(0) == '-') || (nodeItem.charAt(0) == '^'))
//      newNode(NodeType.OPERATOR, nodeItem);
//    else if(nodeItem.matches("[0-9]+"))
//      newNode(NodeType.OPERAND, nodeItem);
//    else
//      newNode(NodeType.VARIABLE, nodeItem);
//  }
//  /***************************************************************************
//    * INPUT: type (NodeType) - an ENUM type to specify if the Node contains an
//    *                          operator, operan or variable name
//    *        nodeItem (String) - a string containing the operator, operand or string to
//    *                            be stored in the Node
//    * METHOD: depending on the type, an int, char or String is created to store the operand,
//    *         operator or Variable Name using a switch statement.
//    * ************************************************************************/
//  private void newNode(NodeType type, String nodeItem){
//    this.type = type;
//    switch(type){
//      case OPERAND:{
//        operand = Integer.parseInt(nodeItem);
//      }
//      break;
//      case OPERATOR:{
//        operator = nodeItem.charAt(0);
//      }
//      break;
//      case VARIABLE:{
//        variableName = nodeItem;
//      }
//      break;
//    }
//    left = null;
//    right = null;
//  }//Node
//  
//  /***************************************************************************
//    * METHOD: return a string containing the item stored in the node
//    * OUTPUT: (String) s
//    * ************************************************************************/
//  public String toString(){
//    String s = "";
//    switch(type){
//      case OPERAND:
//        s += operand;
//        break;
//      case OPERATOR:
//        s += operator;
//        break;
//      case VARIABLE:
//        s = variableName;
//        break;
//    }
//    return s;
//  }//toString
//  
//  /***************************************************************************
//    * METHOD: A recursive postorder traversal that prints the contents of the node
//    * ************************************************************************/
//  public void postorderTraversal(){
//    if(left != null)
//      left.postorderTraversal();
//    if(right != null)
//      right.postorderTraversal();
//    System.out.print(toString() + " ");
//  }
//  
//  /***************************************************************************
//    * METHOD: A recursive preorder traversal that prints the contents of the node
//    * ************************************************************************/
//  public void preorderTraversal(){
//    System.out.print(toString() + " ");
//    if(left != null)
//      left.preorderTraversal();
//    if(right != null)
//      right.preorderTraversal();
//  }
//  
//  /***************************************************************************
//    * METHOD: A recursive inorder traversal that prints the contents of the node
//    * ************************************************************************/
//  public void inorderTraversal(){
//    if (type == NodeType.OPERATOR)
//      System.out.print("(");
//    if(left != null)
//      left.inorderTraversal();
//    System.out.print(toString() + " ");
//    if(right != null)
//      right.inorderTraversal();
//    if (type == NodeType.OPERATOR)
//      System.out.print(")");
//  }
//  
//  /***************************************************************************
//  * METHOD: checks if the Node has two children that aren't null
//  * ************************************************************************/
//  public boolean hasTwoChildren(){
//    return(left != null) && (right != null);
//  }
//  
//  /***************************************************************************
//  * METHOD: checks if the Node is either a varible name or an operand
//  * ************************************************************************/
//  public boolean isVariable(){
//    return (type == NodeType.OPERAND) || (type == NodeType.VARIABLE);
//  }
//  
//  /***************************************************************************
//  * METHOD: Recursively simplifies an expression tree. Does this by using a switch statement
//  *         to perform the right operation between two operands. if one of the children 
//  *         isn't an operand but a variable or another operator with two children, 
//  *         simplifies the expression based on the trivial cases for addition, subtraction 
//  *         multiplication and exponents
//  * ************************************************************************/
//  public void simplify(){
//    if(left != null && left.hasTwoChildren())
//      left.simplify();
//    if(right != null && right.hasTwoChildren())
//      right.simplify();
//    if(left != null && right != null){
//      //if both children are operands, simply perform the required math opration
//      if(left.type == NodeType.OPERAND && right.type == NodeType.OPERAND && type == NodeType.OPERATOR){
//        switch (operator){
//          case '+':{
//            type = NodeType.OPERAND;
//            operand = left.operand + right.operand;
//          }
//          break;
//          case '-':{
//            type = NodeType.OPERAND;
//            operand = left.operand - right.operand;
//          }
//          break;
//          case '*':{
//            type = NodeType.OPERAND;
//            operand = left.operand * right.operand;
//          }
//          break;
//          case '^':{
//            type = NodeType.OPERAND;
//            //casting necessary to avoid lossy conversion error
//            operand = (int) Math.pow(left.operand, right.operand);
//          }
//          break;
//        }
//        operator = '\0';
//        left = null;
//        right = null;
//      }
//      //if one child is an operand and the other is a variable or subtree with an operator root
//      //check for special cases starting with operand as right child
//      else if(type == NodeType.OPERATOR && left.type != NodeType.OPERAND && right.type == NodeType.OPERAND){
//        //add or subtract 0 special case
//        if((operator == '+' || operator == '-') && right.operand == 0){
//          variableName = left.variableName;
//          operator = left.operator;
//          type = left.type;
//          right = left.right;
//          left = left.left;
//          
//        }
//        else if(operator == '*' || operator == '^'){
//          //multiplying by or raising to power 1 special case
//          if(right.operand == 1){
//            variableName = left.variableName;
//            operator = left.operator;
//            type = left.type;
//            right = left.right;
//            left = left.left;
//            
//          }
//          //multiplying by or raising to power 0 special case
//          else if(right.operand == 0){
//            operand = (operator == '*') ? 0 : 1;
//            type = NodeType.OPERAND;
//            operator = '\0';
//            left = null;
//            right = null;
//          }
//        }
//        
//      }
//      //if one child is an operand and the other is a variable or subtree with an operator root
//      //check for special cases ending with operand as right child
//      else if(type == NodeType.OPERATOR && left.type == NodeType.OPERAND && right.type != NodeType.OPERAND){
//        if((operator == '+' || operator == '-') && left.operand == 0){
//          variableName = right.variableName;
//          operator = right.operator;
//          type = right.type;
//          left = right.left;
//          right = right.right;
//        }
//        else if(operator == '*' || operator == '^'){
//          //multiplying by or rasing to 1 special case
//          if(left.operand == 1){
//            variableName = right.variableName;
//            operator = right.operator;
//            type = right.type;
//            left = right.left;
//            right = right.right;
//          }
//          //multiplying by or raising to 0 special case
//          else if(left.operator == 0){
//            operand = (operator == '*') ? 0 : 1;
//            type = NodeType.OPERAND;
//            operator = '\0';
//            left = null;
//            right = null;
//          }
//        }
//      }
//    }
//  }
//  
//  
//  /***********************************************************************************************/
//  
//}//Node class end
//
///***************************************************************************
//  * INPUT: 
//  * METHOD: 
//  * OUTPUT: 
//  * ************************************************************************/