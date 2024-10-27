// 1. Example of Queue
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        // Khởi tạo hàng đợi
        Queue<String> queue = new LinkedList<>();

        // Thêm phần tử vào hàng đợi
        queue.offer("A");
        queue.offer("B");
        queue.offer("C");

        // Xuất các phần tử trong hàng đợi
        System.out.println("Hàng đợi: " + queue);

        // Lấy và loại bỏ phần tử đầu tiên
        String firstElement = queue.poll();
        System.out.println("Phần tử đầu tiên: " + firstElement);

        // Xuất hàng đợi sau khi loại bỏ phần tử
        System.out.println("Hàng đợi sau khi loại bỏ: " + queue);
    }
}

// 2. Example of Stack
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        // Khởi tạo ngăn xếp
        Stack<String> stack = new Stack<>();

        // Thêm phần tử vào ngăn xếp
        stack.push("X");
        stack.push("Y");
        stack.push("Z");

        // Xuất ngăn xếp
        System.out.println("Ngăn xếp: " + stack);

        // Lấy và loại bỏ phần tử ở đỉnh ngăn xếp
        String topElement = stack.pop();
        System.out.println("Phần tử ở đỉnh ngăn xếp: " + topElement);

        // Xuất ngăn xếp sau khi loại bỏ phần tử
        System.out.println("Ngăn xếp sau khi loại bỏ: " + stack);
    }
}

// 3. Algorithm for finding the shortest path
//Dijkstra's Algorithm:
import java.util.*;

public class DijkstraExample {
    static class Node implements Comparable<Node> {
        int vertex, distance;

        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void dijkstra(List<List<Node>> graph, int source) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            for (Node neighbor : graph.get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.distance;

                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        System.out.println("Khoảng cách từ đỉnh " + source + ": " + Arrays.toString(distances));
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<List<Node>> graph = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new Node(1, 10));
        graph.get(0).add(new Node(4, 5));
        graph.get(1).add(new Node(2, 1));
        graph.get(2).add(new Node(3, 4));
        graph.get(3).add(new Node(0, 7));
        graph.get(4).add(new Node(1, 3));
        graph.get(4).add(new Node(2, 9));
        graph.get(4).add(new Node(3, 2));

        dijkstra(graph, 0);
    }
}
// Bellman-Ford Algorithm:
//import java.util.*;

public class BellmanFordExample {
    static class Edge {
        int source, destination, weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static void bellmanFord(List<Edge> edges, int vertices, int source) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                if (distances[edge.source] != Integer.MAX_VALUE &&
                        distances[edge.source] + edge.weight < distances[edge.destination]) {
                    distances[edge.destination] = distances[edge.source] + edge.weight;
                }
            }
        }

        // Kiểm tra chu trình âm
        for (Edge edge : edges) {
            if (distances[edge.source] != Integer.MAX_VALUE &&
                    distances[edge.source] + edge.weight < distances[edge.destination]) {
                System.out.println("Đồ thị có chu trình âm.");
                return;
            }
        }

        System.out.println("Khoảng cách từ đỉnh " + source + ": " + Arrays.toString(distances));
    }

    public static void main(String[] args) {
        int vertices = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        bellmanFord(edges, vertices, 0);
    }
}
// 4. Bubble sort ;
public class BubbleSortExample {
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Hoán đổi arr[j] và arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        bubbleSort(arr);
        System.out.println("Mảng sau khi sắp xếp: " + Arrays.toString(arr));
    }
}
// Quick Sort
public class QuickSortExample {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Hoán đổi arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Hoán đổi arr[i+1] và arr[high] (hoán đổi với pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;
        quickSort(arr, 0, n - 1);
        System.out.println("Mảng sau khi sắp xếp: " + Arrays.toString(arr));
    }
}
