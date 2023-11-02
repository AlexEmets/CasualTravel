public class RouteBuilderTest
{
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");

        nodeA.addDestination(nodeB, 10);
        nodeA.addDestination(nodeC, 15);

        nodeB.addDestination(nodeD, 12);
        nodeB.addDestination(nodeF, 13);

        nodeC.addDestination(nodeE, 10);

        nodeD.addDestination(nodeE, 20);
        nodeD.addDestination(nodeF, 1);

        nodeF.addDestination(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        var nodes = Graph.calculateShortestPathFromSource(graph, nodeA);

        for (Node node : nodes.getNodes()) {
            System.out.print(node.getName());
            System.out.print(":");
            for(Node path_node : node.getShortestPath()) {
                System.out.print(path_node.getName());
            }
            System.out.print('\n');
        }
    }
}
