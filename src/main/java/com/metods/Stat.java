package com.metods;

import java.util.HashMap;
import java.util.Map;

public class Stat {
    Integer amount_in;
    String max;
    String min;
    Map<String, Integer> popular = new HashMap<>();

    public Integer getAmount_in() {
        return amount_in;
    }

    public void setAmount_in(Integer amount_in) {
        this.amount_in = amount_in;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }


    public void setPopular(Map<String, Integer> popular) {
        this.popular = popular;
    }

    public void addAnswer(String answer){
        if(popular.containsKey(answer)){
            popular.replace(answer,popular.get(answer)+1);
        }
        else{
            popular.put(answer,1);
        }
        return;
    }

    public Map<String, Integer> getMostPopular() {
        Map<String, Integer> answer = new HashMap<>();
        Integer maxValue=0;//For max number of weekday
        for (String key:popular.keySet()
        ) {
            if(popular.get(key)>maxValue){
                answer.clear();
                maxValue=popular.get(key);
                answer.put(key,popular.get(key));
            }
            if(popular.get(key)==maxValue){
                answer.put(key,popular.get(key));
            }


        }
        return answer;
    }
}
