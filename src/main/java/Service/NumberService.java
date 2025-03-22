package Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Config.RestTemplate;
import Model.NumberResponse;

@Service
public class NumberService {

    public static final String BASE_URL="http://20.244.56.144/test/";
    public static final int WINDOW_SIZE=10;

   private RestTemplate restTemplate;

   private Map<String,LinkedList<Integer>> res=new HashMap<>();

   @Autowired
   public NumberService(RestTemplate restTemplate){
    this.restTemplate=restTemplate;
   }

   public NumberResponse fetchNumber(String numberId){
    String url=BASE_URL+getEndPoint(numberId);
    List<Integer> fetchNum=fetchfromAPI(url);
    
    
        LinkedList<Integer> window = res.getOrDefault(numberId, new LinkedList<>());
       List<Integer> prevWindow = new ArrayList<>(window);

     for (int num : fetchNum) {
         if (!window.contains(num)) {
                if (window.size() >= WINDOW_SIZE) {
                   window.pollFirst(); 
                }
                window.add(num);
             }
        }

        res.put(numberId, window);
        double avg = window.stream().mapToDouble(a -> a).average().orElse(0.0);

      return new NumberResponse(fetchNum, prevWindow, new ArrayList<>(window), avg);
   }


   private List<Integer> fetchfromAPI(String url) {
           try {
               Integer[] response = restTemplate.getForObject(url, Integer[].class);
                return response != null ? Arrays.asList(response) : Collections.emptyList();
            } catch (Exception e) {
               return Collections.emptyList(); 
            }
        }
    private String getEndPoint(String numberId){
        switch(numberId){
            case "p":
               return "primes";
            case "f":
               return "fibo";
            case "e":
             return "even";
            case "r":
              return "rand";
            default:
               throw new IllegalArgumentException("Invalid ID");
        }
    }
}


