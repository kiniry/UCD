using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.Xna.Framework;

namespace Drought.Entity
{
    class AStar
    {
        /** Offsets of the nodes surrounding a node */
        private static Vector2[] BORDER_NODES = new Vector2[] { 
            new Vector2(-1, -1), //bottom left
            new Vector2(0, -1), //bottom centre
            new Vector2(1, -1), //bottom right
            new Vector2(-1, 0), //left
            new Vector2(1, 0), //right
            new Vector2(-1, 1), //top left
            new Vector2(0, 1), //top center
            new Vector2(1, 1), }; //top right

        private NormalMap normalMap;

        private Node[,] nodeMap;

        /** Width of the map. */
        private int width;

        /** Height of the map. */
        private int height;

        public AStar()
        {

        }

        public void initialise(NormalMap normalMap)
        {
            width = 0; //TODO map width
            height = 0; //TODO map height
            nodeMap = new Node[width, height];

            //create nodes
            for (int x = 0; x < width; x++)
                for (int y = 0; y < height; y++)
                {
                    //if traversable add node
                }
        }

        public Path computePath(float startX, float startY, float endX, float endY)
        {
            List<Node> open = new List<Node>();
            List<Node> closed = new List<Node>();
            Node goal = nodeMap[(int)endX, (int)endY];
            Node start = nodeMap[(int)startX, (int)startY];

            if(goal != null && start != null)
                open.Add(start);
            //else a path can't be found so skip to the end and return default path.

            while (open.Count > 0)
            {
                //get node with lowest f value from open list
                Node n = open[0];
                closed.Add(n);

                if (n.getPosition().Equals(goal.getPosition())) //TODO do data equals //found a path
                {
                    Node curr = n;
                    Node parent = n.getParent();
                    List<Vector3> pathNodes = new List<Vector3>();
                    Vector2 pos = n.getPosition();
                    pathNodes.Add(new Vector3(pos.X, pos.Y, heightMap.getHeight(pos.X, pos.Y)));

                    while (parent != null)
                    {
                        pos = parent.getPosition();
                        pathNodes.Add(new Vector3(pos.X, pos.Y, heightMap.getHeight(pos.X, pos.Y)));
                    }
                    
                    return new Path(pathNodes, normalMap);
                }

                //generate successors for n
                List<Node> successors = new List<Node>();

                for (int i = 0, m = successors.Count; i < m; i++)
                {
                    Node s = successors[i];

                    //s.h is estimate distance to goal
                    //s.g is n.g + cost from n to s
                    //s.f is s.g + s.h

                    //if s is on the OPEN list and the existing one is as good or better then discard s and continue
                    //if s is on the CLOSED list and the existing one is as good or better then discard s and continue
                    //Remove occurrences of s from OPEN and CLOSED
                    //Add s to the OPEN list
                }
            }


            //couldn't find a path so return a path containing just the start node
            List<Vector3> nodes = new List<Vector3>();
            nodes.Add(new Vector3(startX, startY, heightMap.getHeight(startX, startY)));
            return new Path(nodes, normalMap);
        }

        /**
         * Gets the node at the specified location. If the location is
         * invalid then null is returned.
         * 
         * @param x The x coordinate of the location.
         * @param y The y coordinate of the location.
         * @return Node The node at the location or null.
         */
        private Node getNode(int x, int y)
        {
            if(x >= 0 && x < width && y >= 0 && y < height)
                return nodeMap[x,y];
            return null;
        }

        /**
         * Gets a list of successor nodes for a specified node.
         * Each successor node's parent is set to the speified node.
         * 
         * @param node The node to get successors for.
         */
        private List<Node> getSuccessors(Node node)
        {
            List<Node> successors = new List<Node>();

            for(int i = 0; i < BORDER_NODES.Length; i++)
            {
                Vector2 pos = node.getPosition() + BORDER_NODES[i];
                Node n = getNode((int)pos.X, (int)pos.Y);

                if (n != null)
                {
                    Node s = new Node((int)pos.X, (int)pos.Y);
                    s.setParent(n);
                    successors.Add(s);
                }
            }

            return successors;
        }
    }

    class Node
    {
        /** Node's parent or null if no parent exists. */
        private Node parent;

        /** Node's position. */
        private Vector2 pos;

        /** The estimated cost to move from the start node to the goal node going through this node. */
        private float f;

        /** The cost to get to this node from the start node. */
        private float g;

        /** Heuristic value to move from this node to the goal node. */
        private float h;

        /** The distance to the node's parent or 0 if no parent exists. */
        private float distToParent;


        public Node()
        {
            pos = new Vector2();
        }

        public Node(int x, int y)
        {
            pos = new Vector2(x, y);
        }

        public float h
        {
            set { h = value; }
            get { return h; }
        }

        public float g
        {
            set { g = value; }
            get { return g; }
        }

        public float f
        {
            set { f = value; }
            get { return f; }
        }

        /**
         * Gets the distance from this node to its parent
         * node. If no parent exists then 0.0f is returned.
         * 
         * @return The distance to the parent node.
         */
        public float getDistanceToParent()
        {
            return distToParent;
        }

        /**
         * Gets the node's parent. Returns null if no
         * parent node exists.
         * 
         * @return Node's parent.
         */
        public Node getParent()
        {
            return parent;
        }

        /**
         * Sets the parent of this node and calculates the
         * distance to it. If a null parameter is provided
         * then this node has no parent.
         * 
         * @param parent The parent node to set or null if the node is to have no parent.
         */
        public void setParent(Node parent)
        {
            this.parent = parent;

            if (parent != null)
                distToParent = Vector2.Distance(pos, parent.pos);
            else
                distToParent = 0.0f;
        }

        /**
         * Gets the node's position.
         * 
         * @return node's position.
         */
        public Vector2 getPosition()
        {
            return pos;
        }
    }
}
