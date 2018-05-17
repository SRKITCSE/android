package osc;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.util.*;
class DataObject {
	private int data1 = 100;
	private String data2 = "hello";
	private List<String> list = new ArrayList<String>() {
	  {
		add("String 1");
		add("String 2");
		add("String 3");
	  }
	}; 
	//getter and setter methods 
	@Override
	public String toString() {
	   return "DataObject [data1=" + data1 + ", data2=" + data2 + ", list="
		+ list + "]";
	}
 
} 
public class GsonExample 
{
    public static void main(String[] args) 
    {
	DataObject obj = new DataObject();
	Gson gson = new Gson();
	String json = gson.toJson(obj); 
	System.out.println(json); 
    }
}