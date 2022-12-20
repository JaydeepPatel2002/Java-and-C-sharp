using BinarySearchTree;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Hashtable_A
{
    internal class ChainingBST<K, V> : A_Hashtable<K, V>
        where K : IComparable<K>
        where V : IComparable<V>
    {
        private int iBucketCount = 0;
        private const int iInitialSize = 5;
        public ChainingBST()
        {
            oDataArray = new object[iInitialSize];
        }

        #region AddBST
        //===========================================================================================

        public override void Add(K key, V vValue)
        {
            //Get Initial hash
            int iInitialHash = HashFunction(key);
            KeyValue<K, V> kv = new KeyValue<K, V>(key, vValue);
            BST<KeyValue<K, V>> alBSTcurrent = null;

            if (oDataArray[iInitialHash] == null)
            {
                alBSTcurrent = new BST<KeyValue<K, V>>();

                oDataArray[iInitialHash] = alBSTcurrent;
                iBucketCount++;
            }
            else
            {
                alBSTcurrent = (BST<KeyValue<K, V>>)oDataArray[iInitialHash];

                if (alBSTcurrent.Find(kv) != null)
                {
                    throw new ApplicationException("Key already exists in the hash table!");
                }
                iNumCollission++;
            }
            alBSTcurrent.Add(kv);
            //alCurrent.Add(kv);
            iCount++;
            if (IsOverloaded())
                ExpandHashtable();
        }

        void dosomething(KeyValue<K, V> kv)
        {

            this.Add(kv.Key, kv.Value);

        }



        private bool IsOverloaded()
        {
            return (iCount / (double)HTSize) > dLoadFactor;
        }
        private void ExpandHashtable()
        {
            object[] oOldArray = oDataArray;
            oDataArray = new object[HTSize * 2];
            iCount = 0;
            iBucketCount = 0;
            iNumCollission = 0;
            for (int i = 0; i < oOldArray.Length; i++)
            {
                if (oOldArray[i] != null)
                {
                    BST<KeyValue<K, V>> alBSTcurrent2 = (BST<KeyValue<K, V>>)oOldArray[i];

                    alBSTcurrent2.Iterate(dosomething, TRAVERSALORDER.IN_ORDER);

                    iBucketCount++;
                }
            }
        }


        #endregion

        //===========================================================================================


        #region clearBST
        public override void Clear()
        {
            oDataArray = new object[iInitialSize];
            iCount = 0;
            iBucketCount = 0;
            iNumCollission = 0;
        }


        #endregion


        //===========================================================================================

        #region RemoveBST


        public override void Remove(K key)
        {
            // Get initialHash
            int initialHash = HashFunction(key);
            int iIndexValue = -1;
            // If the bucket is not null
            if (oDataArray[initialHash] != null)
            {
                // Cast the target object to an ArrayList
                KeyValue<K, V> kv = new KeyValue<K, V>(key, default(V));

                BST<KeyValue<K, V>> alBSTcurrent2 = (BST<KeyValue<K, V>>)oDataArray[initialHash];


                if (alBSTcurrent2.Find(kv) != null)
                {
                    alBSTcurrent2.Remove(kv);

                    iCount--;
                    if (alBSTcurrent2.Count == 0)
                    {
                        oDataArray[initialHash] = null;

                        iBucketCount--;
                    }
                }
                else
                {
                    throw new ApplicationException("The target is not found in the hash table!");

                }
            }
        }

        #endregion

        //===========================================================================================

        #region tostringBST

        StringBuilder sb = new StringBuilder();


        void dosomething2(KeyValue<K, V> kv)
        {
            //this.Add(kv.Key, kv.Value);
            sb.Append(kv.Value.ToString() + " --> ");
            //Console.Write(kv + " ");

        }

        public override string ToString()
        {
            sb = new StringBuilder();

            for (int i = 0; i < oDataArray.Length; i++)
            {
                sb.Append("Bucket# " + i + ": ");
                if (oDataArray[i] != null)
                {

                    BST<KeyValue<K, V>> alBSTcurrent2 = (BST<KeyValue<K, V>>)oDataArray[i];

                    alBSTcurrent2.Iterate(dosomething2, TRAVERSALORDER.IN_ORDER);

                    sb.Remove(sb.Length - 5, 5);
                }
                sb.Append("\n");
            }
            return sb.ToString();
        }

        #endregion

        //===========================================================================================

        #region getBST


        public override V Get(K key)
        {
            //throw new NotImplementedException();
            // The vReturn is the value to return
            V vReturn = default;
            // Get initialHash
            int initialHash = HashFunction(key);
            int iIndexValue = -1;
            // If the bucket is not null
            if (oDataArray[initialHash] != null)
            {
                // Cast the target object to an ArrayList
                BST<KeyValue<K, V>> alBSTcurrent2 = (BST<KeyValue<K, V>>)oDataArray[initialHash];

                KeyValue<K, V> kv = new KeyValue<K, V>(key, default(V));

                if (alBSTcurrent2.Find(kv) != null)
                {
                    vReturn = ((KeyValue<K, V>)alBSTcurrent2.Find(kv)).Value;
                }
                else
                {
                    throw new ApplicationException("The target is not found in the hash table!");

                }

            }

            return vReturn;
        }

        #endregion

        //==========================================================================================

        //===========================================================================================

        #region IEnumeratorBST

        public override IEnumerator<V> GetEnumerator()
        {
            return new ChainEnumerator(this);
        }
        private class ChainEnumerator : IEnumerator<V>
        {
            private ChainingBST<K, V> parent;


            private int iCurrentBucket = -1;
            private int iCurrentKv = -1;

            public V Current
            {
                get
                {


                    BST<KeyValue<K, V>> bst = (BST<KeyValue<K, V>>)parent.oDataArray[iCurrentBucket];

                    IEnumerator<KeyValue<K, V>> enumerator = bst.GetEnumerator();

                    for (int i = 0; i <= iCurrentKv; i++)
                    {
                        enumerator.MoveNext();

                    }

                    return enumerator.Current.Value;

                }
            }

            object IEnumerator.Current => this.Current;

            public ChainEnumerator(ChainingBST<K, V> chainingBST)
            {
                this.parent = chainingBST;
                this.iCurrentBucket = -1;
                this.iCurrentKv = -1;

            }
            public void Dispose()
            {
                this.parent = null;
                iCurrentBucket = -1;
                iCurrentKv = -1;

            }
            public void Reset()
            {


                iCurrentBucket = -1;
                iCurrentKv = -1;
            }
            public bool MoveNext()
            {
                bool bMoved = false;
                //Try to move to the next ArrayList, if all buckets are empty, return false
                if (iCurrentBucket == -1)
                {
                    if (FindNextBST() == false)
                    {
                        return false;
                    }
                }
                //Move to the next elemnet in the ArrayList

                ++iCurrentKv;
                //Get a reference to the current ArrayList
                BST<KeyValue<K, V>> alCurrent = (BST<KeyValue<K, V>>)(parent.oDataArray[iCurrentBucket]);
                //If at the end of the arraylist
                if (iCurrentKv == alCurrent.Count)
                {
                    //Attempt to find the next arraylist
                    bMoved = FindNextBST();
                    if (bMoved)
                    {
                        //Move to next arraylist, therefore reinitialize the row count
                        iCurrentKv = 0;
                    }
                    else
                    {
                        //Could not move, so point at the previous item
                        iCurrentKv--;
                    }
                }
                //Not at the end of arraylist
                else
                {
                    bMoved = true;
                }
                return bMoved;
            }
            private bool FindNextBST()
            {
                bool bMoved = false;
                int iTempt = iCurrentBucket;
                while (!bMoved && iTempt < parent.oDataArray.Length - 1)
                {
                    iTempt++;
                    if (parent.oDataArray[iTempt] != null)
                    {
                        bMoved = true;
                        iCurrentBucket = iTempt;
                    }
                }
                return bMoved;
            }
        }

        #endregion
    }
}
