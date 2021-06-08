import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class CopyGC {
    ArrayList<entry>newHeap=new ArrayList<>();
    HashSet<Integer>moved=new HashSet<>();
    public void Copy(){
        int start=0,end;
        moved.addAll(MainCopy.roots);
        while(!MainCopy.roots.isEmpty()){
            int u= MainCopy.roots.get(0);
            end=start+ MainCopy.heap.get(u);
            newHeap.add(new entry(u,start,end));
            start=end+1;
            MainCopy.roots.remove(0);
            if(!MainCopy.pointers.containsKey(u))
                continue;
            for(int i = 0; i< MainCopy.pointers.get(u).size(); ++i){
                if(!moved.contains(MainCopy.pointers.get(u).get(i))){
                    MainCopy.roots.add(MainCopy.pointers.get(u).get(i));
                    moved.add(MainCopy.pointers.get(u).get(i));
                }
            }
        }
    }
    public void write(String path){
        try {
            FileWriter myWriter = new FileWriter(path+"\\Copy_new-heap.csv");

            for (entry entry : newHeap) {
                myWriter.write(entry.name + "," + entry.start + "," + entry.end + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file.");
        }
    }

}
