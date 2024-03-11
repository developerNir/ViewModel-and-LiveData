package com.example.viewmodellivedata;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MainActivityViewModel extends ViewModel {

    // get class name in tag value set ----------------------------
//    private String TAG = this.getClass().getSimpleName();

    // mutable Live data in String in set ---------------------------
    private MutableLiveData<String> randomText;

    public MutableLiveData<String> getRandomText(){
        if (randomText == null){
            // Live data Object data create ------------------
            randomText = new MutableLiveData<>();
            createRandomNumber();
            Log.d("log", "getRandomText: get random number");
        }
        return randomText;
    }

    public void createRandomNumber(){
        Random random = new Random();
        // post value in Mutable live data into ui ------------------
        randomText.postValue("Random Number: "+random.nextInt(100-1));
        Log.d("log", "createRandomNumber: this is Create random number");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("log", "View Model Cleared");
    }
}
