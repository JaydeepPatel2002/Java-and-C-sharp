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
                RecInsert2(index--, current.next, data);
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
            throw new NotImplementedException();
        }

        public override T ReplaceAt(int index, T data)
        {
            throw new NotImplementedException();
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
