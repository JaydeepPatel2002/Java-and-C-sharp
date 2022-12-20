using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Xml.Linq;
using DataStructureCommon;
using LinkedList;

namespace BinarySearchTree
{
    public class BST<T> : A_BST<T>, ICloneable where T : IComparable<T>
    {
        /// <summary>
        /// Constructor - Not really neccessary, but increase readability
        /// </summary>
        public BST()
        {
            // Initial the root
            nRoot = null;
            // Set the count to 0
            iCount = 0;
        }

        #region Other functionalities
        public T FindSmallest()
        {
            if(nRoot == null)
            {
                throw new ApplicationException("Root is null");
            }
            else
            {
                return RecFindSmallest(nRoot);
            }
        }

        private T RecFindSmallest(Node<T> nCurrent)
        {
            if(nCurrent.Left == null)
            {
                return nCurrent.Data;
            }
            else
            {
                return RecFindSmallest(nCurrent.Left);
            }
        }

        public T FindLargest()
        {
            if(nRoot == null)
            {
                throw new ApplicationException("Root is null");
            }
            else
            {
                return RecFindLargest(nRoot);
            }
        }

        private T RecFindLargest(Node<T> nCurrent)
        {
            if(nCurrent.Right == null)
            {
                return nCurrent.Data;
            }
            else
            {
                return RecFindLargest(nCurrent.Right);
            }
        }
        #endregion

        public object Clone()
        {
            throw new NotImplementedException();
        }

        #region I_BST Implementation
        public override T Find(T data)
        {
            T tFound = default(T);

            tFound = RecFind(data, nRoot);

            return tFound;
        }


        

        private T RecFind(T data, Node<T> nCurrent)
        {
            if(nCurrent == null)
            {
                return default(T);
            }
            else
            {
                if(data.CompareTo(nCurrent.Data) == 0)
                {
                    return nCurrent.Data;
                }
                else if(nCurrent.Data.CompareTo(data) < 0)
                {
                    return RecFind(data, nCurrent.Right);
                }
                else
                {
                    return RecFind(data, nCurrent.Left);
                }
            }
        }

        public override int Height()
        {
            int iHeight = -1;
            
            if(nRoot != null)
            {
                iHeight = RecHeight(nRoot);
            }
            return iHeight;
        }

        protected int RecHeight(Node<T> nCurrent)
        {
            int iHeightRight = 0;
            int iHeightLeft = 0;

            if (!nCurrent.IsLeaf())// neu mo return, else thi bo "!"
            {
                //return 0;
                //}
                //else
                
                    if (nCurrent.Left != null)
                    {
                        iHeightLeft = 1 + RecHeight(nCurrent.Left);
                    }

                    if (nCurrent.Right != null)
                    {
                        iHeightRight = 1 + RecHeight(nCurrent.Right);
                    }
                
               
            }
            return iHeightLeft > iHeightRight ? iHeightLeft : iHeightRight;
        }

        public override void Iterate(ProcessData<T> pd, TRAVERSALORDER to)
        {

            if (nRoot != null)
            {
                RecIterate(nRoot, pd, to);
            }
        }

        

        private void RecIterate(Node<T> nCurrent, ProcessData<T> pd, TRAVERSALORDER to)
        {
            //if(to == TRAVERSALORDER.PRE_ORDER)
            //{
            //    pd(nCurrent.Data);
            //    if(nCurrent.Left != null)
            //    {
            //        RecIterate(nCurrent.Left, pd, to);
            //    }
            //    if(nCurrent.Right != null)
             //   {
              //      RecIterate(nCurrent.Right, pd, to);
                //}
            //}
            //else if(to == TRAVERSALORDER.IN_ORDER)
            //{
             //   if(nCurrent != null)
              //  {
                //    RecIterate(nCurrent, pd, to);
               // }
                //pd(nCurrent.Data);
             //   if(nCurrent.Right != null)
              //  {
               //     RecIterate(nCurrent.Right, pd, to);
                //}
            //}
            //else
            //{
              //  if(nCurrent.Left != null)
                //{
                 //   RecIterate(nCurrent.Left, pd, to);
                //}
                //if(nCurrent.Right != null)
                //{
                 //   RecIterate(nCurrent.Right, pd, to);
                //}
                //pd(nCurrent.Data);
            //}

            if(to==TRAVERSALORDER.PRE_ORDER)
            {
                pd(nCurrent.Data);
            }

            if(nCurrent.Left != null)
                RecIterate(nCurrent.Left, pd, to);

            if(to== TRAVERSALORDER.IN_ORDER)
            {
                pd(nCurrent.Data);
            }

            if(nCurrent.Right != null)
                RecIterate(nCurrent.Right, pd, to);

            if(to==TRAVERSALORDER.POST_ORDER)
            {
                pd(nCurrent.Data);
            }
        }
        #endregion

        //=====================================================================================================
        //=====================================================================================================
        

        public LinkedList.LinkedList<T> ToLinkedList()
        {

            LinkedList.LinkedList<T> list = new LinkedList.LinkedList<T>();


             void dosomething(T data)
            {
                list.Add(data);
            }


            this.Iterate(dosomething, TRAVERSALORDER.IN_ORDER);

            int start = 0;
            int end = list.Count - 1;
            while (start < end)
            {
                T temp = list.ElementAt(start);
                list.ReplaceAt(start, list.ElementAt(end));
                list.ReplaceAt(end, temp);
                start++;
                end--;
            }


            return list;
        }

       
        //=====================================================================================================
        //=====================================================================================================


        #region I_Collection implementation
        public override void Add(T data)
        {
            if(nRoot == null)
            {
                nRoot = new Node<T>(data);
            }
            else
            {
                RecAdd(data, nRoot);
                nRoot = Balance(nRoot);
            }

            iCount++;

        }

       


        private void RecAdd(T data, Node<T> nCurrent)
        {
            //compare data we are adding with that stored in the current tree
            int iResult = data.CompareTo(nCurrent.Data);
            
            if(iResult < 0)
            {
                if(nCurrent.Left == null)
                {
                    nCurrent.Left = new Node<T>(data);
                }
                else
                {
                    

                    RecAdd(data, nCurrent.Left);
                    nCurrent.Left = Balance(nCurrent.Left);


                }
            }
            else
            {
                if(nCurrent.Right == null)
                {
                    nCurrent.Right = new Node<T>(data);
                }
                else
                {
                    RecAdd(data, nCurrent.Right);
                    nCurrent.Right = Balance(nCurrent.Right);

                }
            }
        }

        // A virtual method that might be override in a child class
        // Note for the BST class, this method does nothing. It is a placeholder for child that allows
        // All a balance method to be writen without rewriting Add and Removed methods
        // virtual key work allows a method could be origin by children
        internal virtual Node<T> Balance(Node<T> nCurrent)
        {
            return nCurrent;
        }
        public override void Clear()
        {
           // throw new NotImplementedException();
           nRoot = null;
           iCount = 0;
        }

        public override bool Remove(T data)
        {
            bool bRemoved = false;

            nRoot = RecRemove(nRoot, data, ref bRemoved);

            return bRemoved;
        }

        private Node<T> RecRemove(Node<T> nCurrent, T data, ref bool bRemoved)
        {
            T tSubstitute = default;
            int iCompare = 0;

            if(nCurrent != null)
            {
                iCompare = data.CompareTo(nCurrent.Data);

                if(iCompare < 0)
                {
                    nCurrent.Left = RecRemove(nCurrent.Left, data, ref bRemoved);
                }
                else if(iCompare > 0)
                {
                    nCurrent.Right = RecRemove(nCurrent.Right, data, ref bRemoved);
                }
                else
                {
                    if(nCurrent.IsLeaf())
                    {
                        nCurrent = null;
                        iCount--;
                    }
                    else if(nCurrent.Left != null && nCurrent.Right == null)
                    {
                        nCurrent = nCurrent.Left;
                        iCount--;
                    }
                    else if(nCurrent.Right != null && nCurrent.Left == null)
                    {
                        nCurrent = nCurrent.Right;
                        iCount--;
                    }
                    else
                    {
                        //tsubstitute = recfindsmallest(nurrent.right);
                        //ncurrent.data = tsubstitute;
                        //ncurrent.right = recremove(ncurrent.right, data, ref bremoved);


                        tSubstitute = RecFindLargest(nCurrent.Left);
                        nCurrent.Data = tSubstitute;
                        nCurrent.Left = RecRemove(nCurrent.Left, tSubstitute, ref bRemoved);
                    }
                }
            }
            return nCurrent;
        }

        public override IEnumerator<T> GetEnumerator()
        {
           // return new DepthFirstEnumerator(this);
           return new BreadthFirstEnumerator(this);
        }
        #endregion

        private class BreadthFirstEnumerator : IEnumerator<T>
        {
             private BST<T> parent = null;
            private Node<T> nCurrent = null;

            //Use a stack to track nodes that we need to go back to
            private Queue<Node<T>> sNodes;

            public BreadthFirstEnumerator(BST<T> parent)
            {
                this.parent = parent;
                Reset();
            }

            public T Current => nCurrent.Data;

            object IEnumerator.Current => nCurrent.Data;

            public void Dispose()
            {
                parent = null;
                nCurrent = null;
                sNodes = null;
            }

            public bool MoveNext()
            {
                //return false;
                bool bMoved = false;

                // If there are/is nodes/node in stack
                if(sNodes.Count > 0)
                {
                    bMoved = true;
                    nCurrent = sNodes.Dequeue();

                    if(nCurrent.Left != null)
                    {
                        sNodes.Enqueue(nCurrent.Left);
                    }

                    if(nCurrent.Right != null)
                    {
                        sNodes.Enqueue(nCurrent.Right);
                    }
                }
                return bMoved;
            }

            public void Reset()
            {
                sNodes = new Queue<Node<T>>();

                if(parent.nRoot != null)
                {
                    sNodes.Enqueue(parent.nRoot);
                }

                nCurrent = null;
            }
        }

        private class DepthFirstEnumerator : IEnumerator<T>
        {
            private BST<T> parent = null;
            private Node<T> nCurrent = null;

            //Use a stack to track nodes that we need to go back to
            private Stack<Node<T>> sNodes;

            public DepthFirstEnumerator(BST<T> parent)
            {
                this.parent = parent;
                Reset();
            }

            public T Current => nCurrent.Data;

            object IEnumerator.Current => nCurrent.Data;

            public void Dispose()
            {
                parent = null;
                nCurrent = null;
                sNodes = null;
            }

            public bool MoveNext()
            {
                //return false;
                bool bMoved = false;

                // If there are/is nodes/node in stack
                if(sNodes.Count > 0)
                {
                    bMoved = true;
                    nCurrent = sNodes.Pop();

                    if(nCurrent.Right != null)
                    {
                        sNodes.Push(nCurrent.Right);
                    }

                    if(nCurrent.Left != null)
                    {
                        sNodes.Push(nCurrent.Left);
                    }
                }
                return bMoved;
            }

            public void Reset()
            {
                sNodes = new Stack<Node<T>>();

                if(parent.nRoot != null)
                {
                    sNodes.Push(parent.nRoot);
                }

                nCurrent = null;
            }
        }
    }
}
