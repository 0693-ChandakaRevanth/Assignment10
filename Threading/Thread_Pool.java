package Threading;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class Thread_Pool{
    public static void main(String[] args) {
        int corePoolSize = 3;
        int maximumPoolSize = 5;
        long keepAliveTime = 10;
        TimeUnit unit = TimeUnit.SECONDS;
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        // Create ThreadPoolExecutor
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit, workQueue);
        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask("Task" + i);
            System.out.println("Submitting " + task.getTaskName());
            executor.execute(task);
        }
        executor.shutdown();
    }
}
class MyTask implements Runnable {
    private final String taskName;
    public MyTask(String taskName) {
        this.taskName = taskName;
    }
    public String getTaskName() {
        return taskName;
    }
    @Override
    public void run() {
        System.out.println("Executing task: " + taskName + " by " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Task interrupted: " + taskName);
        }
        System.out.println("Completed task: " + taskName + " by " + Thread.currentThread().getName());
    }
}