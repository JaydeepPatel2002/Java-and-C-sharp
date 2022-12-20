using Graphs_A;
using System;
using System.Collections.Generic;
using System.Collections;

using System.Linq;
using System.Text;
using System.Threading.Tasks;


namespace GraphMatrix
{
    public class AAdjList<T> : AGraph<T>
        where T : IComparable<T>
    {

        protected List<List<Edge<T>>> AdjList;

        public AAdjList()
        {
            AdjList = new List<List<Edge<T>>>();

            for(int i = 0; i < NumVertices; i++)
            {
                AdjList.Add(new List<Edge<T>>());
            }




        }



        //done
        public override void AddVertexAdjustEdges(Vertex<T> v)
        {
            // Create a reference to the old List
            List<List<Edge<T>>> oldList = AdjList;

            // Create a new List with the current dimension
            AdjList = new List<List<Edge<T>>>();


          

            if (AdjList.Count == 0)
            {
                for (int i = 0; i < NumVertices; i++)
                {
                    AdjList.Add(new List<Edge<T>>());
                }
            }


            for (int r = 0; r < oldList.Count; r++)
            {
                for (int c = 0; c < oldList.ElementAt(r).Count; c++)
                {

                    AdjList[r].Add(oldList[r][c]);
                   



                }


            }

           

        }


        //done
        public override IEnumerable<Vertex<T>> EnumerateNeighbours(T data)
        {
            List<Vertex<T>> neighbours = new List<Vertex<T>>();

            // Do some stuff to load up the list with neighbours of the data item passed in
            Vertex<T> vFrom = GetVertex(data);

            for (int i = 0; i < AdjList.ElementAt(vFrom.Index).Count; i++)
            {
                
                if (AdjList.ElementAt(vFrom.Index).ElementAt(i) != null)
                {
                    // neighbours.Add(matrix[vFrom.Index, i].To);
                    neighbours.Add(vertices[i]);
                }

            }

            return neighbours;

        }
        //done
        public override Edge<T> GetEdge(T from, T to)
        {

            Edge<T> result = null;


            for (int i = 0; i < AdjList[GetVertex(from).Index].Count; i++)
            {
                if(AdjList[GetVertex(from).Index].ElementAt(i).To.Equals(GetVertex(to)))
                {
                    result = AdjList[GetVertex(from).Index].ElementAt(i);

                }
              
            }

            return result;


        }


        //done
        public override bool HasEdge(T from, T to)
        {

            Edge<T> result = null;


            for (int i = 0; i < AdjList[GetVertex(from).Index].Count; i++)
            {
                if (AdjList[GetVertex(from).Index].ElementAt(i).To.Equals(GetVertex(to)))
                {
                    result = AdjList[GetVertex(from).Index].ElementAt(i);

                }

            }

            if(result != null)
            {

                return true;

            }
            else
            {
                return false;
            }



            
        }
        //done
        public override void RemoveEdge(T from, T to)
        {
            // If edge does not exist, throw an exception
            if (!HasEdge(from, to))
            {
                throw new ApplicationException("Edge does not exist!");
            }


            AdjList[GetVertex(from).Index].Remove(GetEdge( from ,  to));
           


            // Decrement the edge count
            numEdges--;
        }

        //done
        public override void RemoveVertexAdjustEdges(Vertex<T> v)
        {



            // Reset the number of edges to 0
            numEdges = 0;
            
            // Create a reference to the old List
            List<List<Edge<T>>> oldList = AdjList;

            // Create a new List with the current dimension
            AdjList = new List<List<Edge<T>>>();

            if (AdjList.Count == 0)
            {
                for (int i = 0; i < NumVertices; i++)
                {
                    AdjList.Add(new List<Edge<T>>());
                }
            }



            int index = 0;

            // Add the Edges from oldList to new one
            for (int r = 0; r < oldList.Count; r++)
            {

                if(r!=v.Index)
                {
                    

                    for (int c = 0; c < oldList.ElementAt(r).Count; c++)
                    {

                        if(!oldList.ElementAt(r).ElementAt(c).To.Equals(v))
                        {
                            AdjList[index].Add(oldList.ElementAt(r).ElementAt(c));
                        }


                    }

                    index++;


                }

                
            }
        }
        //done
        protected override void AddEdge(Edge<T> e)
        {

            if(AdjList.Count == 0)
            {
                for (int i = 0; i < NumVertices; i++)
                {
                    AdjList.Add(new List<Edge<T>>());
                }
            }

            if (HasEdge(e.From.Data, e.To.Data))
            {
                throw new ApplicationException("Edge already exists!");
            }
            
            AdjList[e.From.Index].Add(e);
            numEdges++;

        }


        //kind of done
        public override string ToString()
        {

            Console.WriteLine("$$$$$$$");
            Console.WriteLine(base.ToString());

            for (int i = 0; i < AdjList.Count; i++)
            {


                //Console.WriteLine(AdjList[i].Count);

                Console.Write(vertices[i].ToString() + "---------->>\t");


                if (AdjList[i].Count != 0)
                {
                    //Console.Write(AdjList[i][0].From.ToString() + "----------\t");
                    for (int j = 0; j < AdjList[i].Count; j++)
                    {
                        Console.Write("  -->  " + AdjList[i][j].To.ToString());

                    }

                }



                Console.WriteLine();

                //Console.WriteLine(AdjList[i][0].ToString());
            }
            return "thanks";
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
