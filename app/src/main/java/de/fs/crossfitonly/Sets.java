package de.fs.crossfitonly;

import android.util.Log;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Sets {

    private String sets;
    private String reps;
    private String weight;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sets( String id, String sets, String reps, String weight) {
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.id = id;
        Log.d(TAG,"IDD:"+ id);
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
