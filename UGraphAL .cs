using Graphs_A;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace GraphMatrix
{
    public class UGraphAL<T> : AAdjList<T>
        where T : IComparable<T>
    {

        public UGraphAL()
        {
            isDirected = false;
        }

        public override int NumEdges
        {
            get
            {
                return base.numEdges / 2; // because for each edge, we add 2 copies (in AddEdge), but the actual number of edges is half of them
            }
        }
        public override void AddEdge(T from, T to)
        {
            base.AddEdge(from, to);
            base.AddEdge(to, from);
        }

        public override void AddEdge(T from, T to, double weight)
        {
            base.AddEdge(from, to, weight);
            base.AddEdge(to, from, weight);
        }
        protected override Edge<T>[] GetAllEdges()
        {

            // Create a new empty list of edges
            List<Edge<T>> edges = new List<Edge<T>>();

            for (int r = 0; r < AdjList.Count; r++)
            {
                for (int c = 0; c < AdjList.ElementAt(r).Count; c++)
                {



                    // If an edge exists at the current location
                    if (AdjList.ElementAt(r).ElementAt(c) != null)
                    {

                        edges.Add(AdjList.ElementAt(r).ElementAt(c));


                    }

                }


            }

            return edges.ToArray();








        }


    }
}
