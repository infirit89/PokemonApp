package com.test.app.web;

import androidx.annotation.Nullable;

public class PKResponse {

    public PKResponse(int count, String next, String previous, PKResponseResult[] results)
    {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("Count: %d", count));
        builder.append(String.format("Next: %s", next));
        if(previous == null)
            builder.append(String.format("Previous: null"));
        else
            builder.append(String.format("Previous: %s", previous));

        for (PKResponseResult result : results) {
            builder.append(result);
        }

        return builder.toString();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Nullable
    public String getPrevious() {
        return previous;
    }

    public void setPrevious(@Nullable String previous) {
        this.previous = previous;
    }

    public PKResponseResult[] getResults() {
        return results;
    }

    public void setResults(PKResponseResult[] results) {
        this.results = results;
    }


    private int count;
    private String next;

    @Nullable
    private String previous;
    private PKResponseResult[] results;
}
