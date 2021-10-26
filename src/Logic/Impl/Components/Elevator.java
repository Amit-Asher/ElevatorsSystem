package Logic.Impl.Components;
import Logic.Impl.Logger.Log;

import java.util.Iterator;
import java.util.LinkedList;

public class Elevator {
    private int id;
    private int currentFloor = 0;
    private LinkedList<FloorNode> floorsQueue = new LinkedList<>();

    public Elevator(int id) {
        this.id = id;
        ElevatorThread thread = new ElevatorThread();
        thread.setDaemon(true);
        thread.start();
    }

    private class FloorNode {
        public int floor;
        public boolean stop;

        public FloorNode(int floor, boolean stop) {
            this.floor = floor;
            this.stop = stop;
        }
    }

    public int getId()
    {
        return id;
    }

    private FloorNode findFloorInQueue(int targetFloor) {
        FloorNode result = null;

        for(FloorNode node : floorsQueue) {
            if(node.floor == targetFloor) {
                result = node;
                break;
            }
        }

        return result;
    }


    private void addPathToQueue(int dstFloor) {
        int lastFloor = floorsQueue.size() == 0 ? currentFloor : floorsQueue.getLast().floor;
        int direction = dstFloor < lastFloor ? -1 : 1;
        if(lastFloor != dstFloor) {
            for (int curFloor = lastFloor + direction; curFloor != dstFloor; ) {
                floorsQueue.add(new FloorNode(curFloor, false));
                curFloor += direction;
            }
            floorsQueue.add(new FloorNode(dstFloor, true));
        }
    }

    private FloorNode findFloorInQueueFromNode(FloorNode start, int dstFloor) {

        Iterator<FloorNode> it = floorsQueue.iterator();
        while(it.hasNext()) {
            if(it.next() == start) {
                break;
            }
        }

        FloorNode result = null;
        while(it.hasNext()) {
            FloorNode curNode = it.next();
            if(curNode.floor == dstFloor) {
                result = curNode;
                break;
            }
        }

        return result;
    }

    public void addStop(int srcFloor, int dstFloor) {

        // add/update src floor in queue
        FloorNode srcNode = findFloorInQueue(srcFloor);
        if (srcNode == null) {
            addPathToQueue(srcFloor);
            if(floorsQueue.size() != 0) {
                srcNode = floorsQueue.getLast();
            }
        } else {
            srcNode.stop = true;
        }

        // add/update dst floor in queue
        FloorNode dstNode = findFloorInQueueFromNode(srcNode, dstFloor);
        if (dstNode == null) {
            addPathToQueue(dstFloor);
        } else {
            dstNode.stop = true;
        }
    }

    public int getDistance(int targetFloor) {
        int distance = 0;
        boolean targetFloorWasFoundInQueue = false;

        for(FloorNode floorNode : floorsQueue) {
            distance++;
            if(floorNode.floor == targetFloor) {
                targetFloorWasFoundInQueue = true;
                break;
            }
        }

        if(!targetFloorWasFoundInQueue) {
            int lastFloor = floorsQueue.size() == 0 ? currentFloor : floorsQueue.getLast().floor;
            distance += Math.abs(targetFloor - lastFloor);
        }

        return distance;
    }

    private void delay(long sec) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class ElevatorThread extends Thread {
        @Override
        public void run() {
            while(true) {
                if(floorsQueue.size() != 0) {
                    FloorNode floorNode = floorsQueue.remove();
                    currentFloor = floorNode.floor;
                    if (floorNode.stop) {
                        String msg = "Elevator " + id + " stopped at floor " +
                                floorNode.floor + " | Elevator Queue (stop=*): " + getQueueAsStr();
                        Log.getInstance().PrintAndWriteToLog(msg);
                    }
                    else {
                        String msg = "Elevator " + id + " at floor " + floorNode.floor +
                                " | Elevator Queue (stop=*): " + getQueueAsStr();
                        Log.getInstance().PrintAndWriteToLog(msg);
                    }
                }

                delay(1000); // time period to switch floors
            }
        }
    }

    public String getQueueAsStr() {
        String res = "[";
        for(FloorNode floorNode : floorsQueue) {
            res += Integer.toString(floorNode.floor);
            res += floorNode.stop ? "*, " : ", ";
        }
        res += "]";
        return res;
    }
}