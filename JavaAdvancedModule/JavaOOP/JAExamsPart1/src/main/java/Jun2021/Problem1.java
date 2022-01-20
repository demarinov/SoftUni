package Jun2021;

import java.util.*;

// stack & queue - OS Planning
public class Problem1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] tasks = Arrays.stream(sc.nextLine().split(",\\s"))
                .map(s -> Integer.parseInt(s)).toArray(Integer[]::new);

        Integer[] threads = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s)).toArray(Integer[]::new);

        int taskToKill = Integer.parseInt(sc.nextLine());

        Deque<Integer> taskCollection = new ArrayDeque<>();
        Deque<Integer> threadCollection = new ArrayDeque<>();

        taskCollection.addAll(Arrays.asList(tasks));
        threadCollection.addAll(Arrays.asList(threads));

        int threadKillingTask = -1;
        while(true) {

            Integer lastTask = taskCollection.getLast();
            Integer firstThread = threadCollection.getFirst();

            if (lastTask.intValue() == taskToKill) {
                threadKillingTask = firstThread;
                break;
            }

            if (firstThread.intValue() >= lastTask.intValue()) {
                taskCollection.removeLast();
                threadCollection.removeFirst();
            } else {
                threadCollection.removeFirst();
            }


        }


        // print collections and stuff
        System.out.printf("Thread with value %d killed task %d%n",threadKillingTask, taskToKill);
        printThreads(threadCollection);
    }

    public static void printThreads(Deque<Integer> collection) {

        String output = collection.stream().
                map(d -> String.format("%d ",d)).reduce("",String::concat);

        System.out.printf("%s",output.substring(0, output.length()-1));

    }
}
