using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Security.AccessControl;
using System.Runtime.CompilerServices;

namespace LinkedList
{
    public class LinkedList<T>:A_List<T> where T : IComparable<T>
    {
        #region Attributes
        private Node head;
        #endregion

        public override void Add(T data)
        {
            //throw new NotImplementedException();
            head = RecAdd(head, data);
        }
        //Recursive helper for Add method
        private Node RecAdd(Node current, T data)
        {
            if (current == null)
            {
                current = new Node(data);
            }
            else
            {
                current.next = RecAdd(current.next, data);
            }
            return current;
        }

        //version 2 of Add and RecAdd function
        //public override void Add(T data)
        //{
        //    if(head == null)
        //    {
        //        head = new Node(data);
        //    }
        //    else
        //    {
        //        RecAdd2(head, data);
        //    }
        //}

        //private void RecAdd2(Node current, T data)
        //{
        //    if (current.next == null)
        //        current.next = new Node(data);
        //    else
        //        RecAdd2(current.next, data);
        //}

        public override void Insert(int index, T data)
        {
            //Check if index is out of range, if yes, throw an exception
                if (index < 0 || index >= this.Count)
            {
                throw new IndexOutOfRangeException("Invalid index " + index);
            }

            if(index == 0)
            {
                Node newNode = new Node(data, head);
                head = newNode;
            }
            else
            {
                RecInsert2(index, head, data);
            }
        }

        private void RecInsert2(int index, Node current, T data)
        {
            if(index == 1)
            {
                Node newNode = new Node(data, current.next);
                current.next = newNode;
            }
            else
            {
                RecInsert2(--index, current.next, data);
            }

        }

        //public override void Insert(int index, T data)
        //{
        //    //throw new NotImplementedException();
        //    //Check if index is out of range, if yes, throw an exception
        //    if(index < 0 || index >= this.Count)
        //    {
        //        throw new IndexOutOfRangeException("Invalid index " + index);
        //    }
        //    //Use recursion to insert your data at the index
        //    head = RecInsert(head, index, data);
        //}

        //private Node RecInsert(Node current, int index, T data)
        //{
        //    if(index == 0)
        //    {
        //        current = new Node(data, current);
        //    }
        //    else
        //    {
        //        current.next = RecInsert(current.next, index--, data);
        //    }
        //    return current;
        //}

        public override T RemoveAt(int index)
        {

            Node prev = this.head;
            Node current = this.head;
            int ind = 0;
            //throw new NotImplementedException();
            if(index < 0 || index >= this.Count)
            {
                throw new IndexOutOfRangeException("Invalid index " + index);
            }
            else 
            {
                while(ind < this.Count)
                {
                    if(index == 0)
                    {
                        this.head = this.head.next;
                        break;
                    }
                    else if(ind == index)
                    {
                        prev.next = current.next;
                        break;


                    }
                    prev = current;
                    current = current.next;
                    ind++;
                }
                return current.data;
            }

        }

        public new void ReverseList()
        {
            Node prev = null, current = this.head, next = null;
            while (current != null)
            {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }
            this.head = prev;
        }
        //========================================================================================

        int ind = 1;
        Node prev = null;

        public T RecRemoveAt(int index)
        {
            T olddata ;
            prev = this.head;

            if (index < 0 || index >= this.Count)
            {
                throw new IndexOutOfRangeException("Invalid index " + index);
            }
            else if (this.head == null)
            {
                throw new Exception("linked list is empty");
            }
            else if (index == 0)
            {
                olddata = this.head.data;
                this.head = this.head.next;
                return olddata;
               
            }
            else
            {
                olddata = RecRemoveHelp(this.head.next , index);

            }

            return olddata;
            
            
        }

        

        private T RecRemoveHelp(Node newNode, int index)
        {
            Console.WriteLine(ind +"==>>  "+newNode.data);

            if(ind == index)
            {
              
                prev.next = newNode.next;
                Console.WriteLine(ind + "===>1>  " + newNode.data);
               
                return newNode.data;
            }
            else
            {
                ind++;
                prev = newNode;
                Console.WriteLine(ind + "===>1>  " + newNode);

                return RecRemoveHelp(newNode.next, index);
            }


        }

    //==========================================================================================

        public override T ReplaceAt(int index, T data)
        {
            Node current = this.head;
            int ind = 0;
            
            if(index < 0 || index >= this.Count)
            {
                throw new IndexOutOfRangeException("Invalid index " + index);
            }
            else if(index == 0)
            {
                this.head.data = data;
            }
            else
            {
                while(ind < this.Count)
                {
                    current = current.next;
                    ind++;
                    if(index == ind)
                    {
                        current.data = data;
                        break;
                    }
                }


            }

            return data;


        }

        //==============================================================================

        int myIndex = 1;

        public  T RecReplaceAt(int index, T data)
        {
            Console.WriteLine(this.Count);
            Console.WriteLine(this.Count);
            T oldData;
            if (index < 0 || index >= this.Count)
            {
                throw new IndexOutOfRangeException("Invalid index ===>" + index);
            }
            else if (index == 0)
            {
                oldData = this.head.data;
                this.head.data = data;
                return oldData;
            }
            else
            {
                oldData = RecReplaceHelp(index,data, this.head.next);

            }



            return oldData;
        }

          T RecReplaceHelp(int index, T data, Node nNode)
        {
            T oldData;
            if (index == myIndex)
            {
                oldData = nNode.data;
                nNode.data = data;

            }
            else
            {
                myIndex++;
                oldData=RecReplaceHelp(index,data, nNode.next);
            }


            return oldData;
        }


        public override bool Remove(T data)
        {
            //throw new NotImplementedException();
            return RecRemove(ref head, data);
        }

        private bool RecRemove(ref Node current, T data)
        {
            bool found = false;

            if(current != null)
            {
                if(current.data.CompareTo(data) == 0)
                {
                    found = true;
                    current = current.next;
                }
                else
                {
                    found = RecRemove(ref current.next, data);
                }    
            }

            return found;
        }

        public override void Clear()
        {
            throw new NotImplementedException();
        }

        public override IEnumerator<T> GetEnumerator()
        {
            return new Enumerator(this);
        }

        #region Enumerator implementation
        private class Enumerator : IEnumerator<T>
        {
            //Reference to the linked list
            private LinkedList<T> parent;
            //A reference to current node being visited
            private Node lastVisited;
            //The next node that we want to visit
            private Node scout;

            public Enumerator (LinkedList<T> parent)
            {
                this.parent = parent;
                Reset();
            }

            public T Current
            {
                get
                {
                    return lastVisited.data;
                }
            }

            object IEnumerator.Current
            {
                get
                {
                    return lastVisited.data;
                }
            }

            //Clear up the resources used
            //Set references to null
            //Clean when you are done with the enumerator
            public void Dispose()
            {
                parent = null;
                scout = null;
                lastVisited = null;
            }

            public bool MoveNext()
            {
                bool result = false;

                if(scout != null)
                {
                    //We can definetely move
                    result = true;
                    //Move current node pointer to the next node
                    lastVisited = scout;
                    //Move scout to the next node
                    scout = scout.next;
                }

                return result;
            }

            public void Reset()
            {
                lastVisited = null;
                scout = parent.head;
            }
        }
        #endregion
        private class Node
        {
            #region Attributes
            public T data;
            public Node next;
            #endregion
            //Constructors
            public Node(T data) : this(data, null) { }
            public Node(T data, Node next)
            {
                this.data = data;
                this.next = next;
            }
        }
    }
}
