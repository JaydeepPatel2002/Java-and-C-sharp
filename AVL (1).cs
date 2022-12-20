using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BinarySearchTree
{
    internal class AVL<T> : BST<T> where T : IComparable<T>
    {

        //here is a homework assignment 1.
        //==========  ITERATE ================
        #region homework

        public override void Iterate(ProcessData<T> pd, TRAVERSALORDER to)
        {
            if (to == TRAVERSALORDER.PRE_ORDER)
            {
                preorderIterative(pd, nRoot);
            }
            else if (to == TRAVERSALORDER.IN_ORDER)
            {
                inorderIterative(pd, nRoot);
            }
            else if (to == TRAVERSALORDER.POST_ORDER)
            {
                PostorderIterative(pd, nRoot);
            }



        }

        public void PostorderIterative(ProcessData<T> pd, Node<T> root)
        {

            Stack<Node<T>> FirstStack = new Stack<Node<T>>();
            Stack<Node<T>> secondStack = new Stack<Node<T>>();

            if (root == null)
                return;


            FirstStack.Push(root);


            while (FirstStack.Count > 0)
            {

                Node<T> myNode = FirstStack.Pop();
                secondStack.Push(myNode);


                if (myNode.Left != null)
                    FirstStack.Push(myNode.Left);
                if (myNode.Right != null)
                    FirstStack.Push(myNode.Right);
            }


            while (secondStack.Count > 0)
            {
                Node<T> temp = secondStack.Pop();
                pd(temp.Data);
            }
        }


        public void preorderIterative(ProcessData<T> pd, Node<T> node)
        {
            if (node == null)
            {
                return;
            }

            Stack<Node<T>> tempstack = new Stack<Node<T>>();
            tempstack.Push(node);

            while (tempstack.Count > 0)
            {
                Node<T> mynode = tempstack.Peek();
                pd(mynode.Data);
                tempstack.Pop();

                if (mynode.Right != null)
                {
                    tempstack.Push(mynode.Right);
                }
                if (mynode.Left != null)
                {
                    tempstack.Push(mynode.Left);
                }

            }


        }
        public void inorderIterative(ProcessData<T> pd, Node<T> root)
        {

            Stack<Node<T>> JayStack = new Stack<Node<T>>();

            Node<T> MyNode = root;

            while (JayStack.Count > 0 || MyNode != null)
            {

                if (MyNode != null)
                {
                    JayStack.Push(MyNode);
                    MyNode = MyNode.Left;
                }
                else
                {

                    MyNode = JayStack.Pop();
                    pd(MyNode.Data);
                    MyNode = MyNode.Right;
                }
            }


        }



        public void newAdd(T data)
        {
            if (nRoot == null)
            {
                nRoot = new Node<T>(data);
            }
            else
            {
                IterativeAdd2(data);
                

                 //nRoot = Balance(nRoot);
            }

            iCount++;

        }
        


        private void IterativeAdd2( T data)
        {

            Stack<Node<T>> stack1 = new Stack<Node<T>>();
            Stack<Node<T>> stack2 = new Stack<Node<T>>();
            Node<T> root = nRoot;
            Node<T> newnode = new Node<T>(data);
            while (root!= null)
            {
                stack1.Push(root);
                if (data.CompareTo(root.Data) < 0)
                {
                    if(root.Left == null)
                    {                      
                        root.Left = newnode;
                        stack1.Push(newnode);
                        break;
                    }
                    else
                    {
                      root = root.Left;          
                    }
                }
                else
                {
                    if(root.Right == null)
                    {
                        root.Right = newnode;
                        stack1.Push(newnode);

                        break;
                    }
                    else
                    {                       
                        root = root.Right;              
                    }
                }
                
            }


            while (stack1.Count > 0)
            {
                Node<T> node2 = stack1.Pop();
                
                //nRoot = Balance(node2);

                if (stack1.Count == 1)
                {
                    if (nRoot.Left != null)
                    {                        
                        Console.WriteLine("-=before==peekleft=->>>" + stack1.Peek().Right.Data);
                        //nRoot.Right = node2;
                       
                    }

                }

                                            
            }                     

        }


        #endregion

        internal override Node<T> Balance(Node<T> nCurrent)
        {
            Node<T> newNode2 = nCurrent;

            if (nCurrent != null)
            {
                //Check the height difference between its two sub trees
                int iHeightDifference = GetHeightDifference(nCurrent);
                Console.WriteLine(nCurrent.Data + "-> height diff :" + iHeightDifference);

                //if it is left heavy
                if (iHeightDifference > 1)
                {
                    int iLeftChildHeightDiff = GetHeightDifference(nCurrent.Left);

                    //if the left child subtree is right heavy
                    if (iLeftChildHeightDiff < 0)
                    {
                        newNode2 = DoubleRight(nCurrent);
                    }
                    else
                    {
                        newNode2 = SingleRight(nCurrent);
                    }
                }

                if (iHeightDifference < -1)
                {
                    int iRightChildHeightDiff = GetHeightDifference(nCurrent.Right);

                    //if the right child subtree is left heavy
                    if (iRightChildHeightDiff > 0)
                    {
                        newNode2 = DoubleLeft(nCurrent);
                    }
                    else
                    {
                        newNode2 = SingleLeft(nCurrent);
                    }
                }
            }

            return newNode2;
            //Check if its left child node's subtree's height difference

            //if the left child subtree is right heavy 
            //Call double right
            //else call single right

            //if it is right heavy
            //check if its right child node's subtree's height difference
            //if the right child subtree is left heavy
            //call double left
            //else call single left

        }



        /*public override Node<T> Balance(Node<T> nCurrent)
        {
            Node<T> newNode = nCurrent;
            if(nCurrent != null)
            {
                int Hightdifference = GetHeightDifference(nCurrent);

                if(Hightdifference > 1 )
                {
                    int leftheightdifference = GetHeightDifference(nCurrent.Left);

                    if(leftheightdifference < -1)
                    {
                        
                        newNode = DoubleRight(nCurrent);

                    }
                    else
                    {
                        newNode = SingleRight(nCurrent);
                    }
                }

                if(Hightdifference < -1)
                {
                    int rightchildheightdifference = GetHeightDifference(nCurrent.Right);
                    if(rightchildheightdifference > 0)
                    {
                        newNode = DoubleLeft(nCurrent);

                    }
                    else
                    {
                        newNode = SingleLeft(nCurrent);
                    }
                }

                return newNode;


            }
        }*/

        protected int GetHeightDifference(Node<T> nCurrent)
        {
            int iHeightLeft = -1;
            int iHeightRight = -1;

            if (nCurrent.Left != null)
            {
                iHeightLeft = RecHeight(nCurrent.Left);
            }


            if(nCurrent.Right != null)
            {
                iHeightRight = RecHeight(nCurrent.Right);
            }

            return iHeightLeft - iHeightRight;

        }

        
          
            //height difference


            //left heavy

            //if the left child sbtree is right heavy
            //
            //call double right

            //call single right



            //right heavy

            //sab tree's height difference

            //if right subtree is left heavy

            // call double left

            // call single left

        


        //other  helper methods




        private Node<T> SingleLeft(Node<T> nOldRoot)
        {
            Node<T> nNewRoot = nOldRoot.Right;


            //Assign new root's left child to old root's right child
            nOldRoot.Right = nNewRoot.Left;

            nNewRoot.Left = nOldRoot;

            return nNewRoot;

        }

        private Node<T> SingleRight(Node<T> nOldRoot)
        {
            Node<T> nNewRoot = nOldRoot.Left;

            nOldRoot.Left = nNewRoot.Right;

            nNewRoot.Right = nOldRoot;

            return nNewRoot;
        }

        private Node<T> DoubleRight(Node<T> nOldRoot)
        {
            nOldRoot.Left = SingleLeft(nOldRoot.Left);

            return SingleRight(nOldRoot);
        }
        private Node<T> DoubleLeft(Node<T> nOldRoot)
        {
            nOldRoot.Right = SingleRight(nOldRoot.Right);

            return SingleLeft(nOldRoot);
        }

    }
}
