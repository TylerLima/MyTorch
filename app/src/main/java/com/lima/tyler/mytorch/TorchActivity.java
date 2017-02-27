package com.lima.tyler.mytorch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import static com.lima.tyler.mytorch.R.id.button;
import static com.lima.tyler.mytorch.R.id.editText2;
import static com.lima.tyler.mytorch.R.id.toggleButton;
import static com.lima.tyler.mytorch.R.string.morse_code;
import static java.lang.reflect.Array.getLength;


public class TorchActivity extends AppCompatActivity {


    private CameraManager CamMan;
    private String CamManId;
    private ToggleButton OnOffButton;
    private Boolean isTorchOn;
    private String inputString;
    private Button sendButton;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TorchActivity", "onCreate()");
        setContentView(R.layout.activity_torch);
        OnOffButton = (ToggleButton) findViewById(toggleButton);
        sendButton = (Button) findViewById(button);
        isTorchOn = false;


        /*
        * Boolean to check if the device being used has a Camera flash feature*/
        Boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature
                (PackageManager.FEATURE_CAMERA_FLASH);


        /*
        * Main function for determining the availability of the device's camera flash. If the device
        * lacks the capability to do so, inform the user with a message, then close the application
        */
        if (!isFlashAvailable) {
            AlertDialog alert = new AlertDialog.Builder(TorchActivity.this).create();
            alert.setTitle("Whoops");
            alert.setMessage("Your device doesn't support flash light");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                    System.exit(0);
                }
            });
            alert.show();
        }


        /*
        * Instantiating the Camera Manager and assigning that name for the String id variable
        * CamManId*/
        CamMan = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            CamManId = CamMan.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        /*
        * Listener for toggle button with check to ensure the click is changing the boolean
        * isTorchOn is changing the boolean value in line with the action of calling methods:
        * turnOffFlashlight and turnOnFlashlight*/
        OnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (isTorchOn) {
                        turnOffFlashlight();
                        isTorchOn = false;

                    } else {
                        turnOnFlashlight();
                        isTorchOn = true;
                    }

                } catch (CameraAccessException e1) {
                    e1.printStackTrace();
                }
            }
        });



        /*Function reads in input from the text field letter by letter, calls function convertToMorse,
        * then calls function flashMorseCode */
        sendButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(editText2);
                inputString = editText.toString();
                System.out.println(inputString);
                if (inputString.length() > 0 & inputString.length() < 15) {
                    //char[] stringChar = inputString.toCharArray();
                    for (int i = 0; i < getLength(inputString); i++) {
                        try {
                            convertToMorse(inputString.charAt(i));
                            System.out.println(i);
                        } catch (CameraAccessException e) {
                            System.out.println("error");
                            e.printStackTrace();
                        }
                    }
                }
            }

        });

        }



        /*turnOffFlashLight checks the current status of the mCameraID's camera flash is false before
        *setting the torchmode of the device to true*/
        public void turnOnFlashlight() throws CameraAccessException {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                CamMan.setTorchMode(CamManId, true);
                OnOffButton.setButtonDrawable(R.drawable.on);
            }
        }
    /*turnOffFlashLight checks the current status of the mCameraID's camera flash is false before
    *setting the torchmode of the device to false*/
    public void turnOffFlashlight() throws CameraAccessException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            CamMan.setTorchMode(CamManId, false);
            OnOffButton.setButtonDrawable(R.drawable.off);
        }
    }

    public void longFlash() throws CameraAccessException {
        turnOnFlashlight();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 800);
        turnOffFlashlight();
    }

    public void quickFlash() throws CameraAccessException {
        turnOnFlashlight();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 50ms
            }
        }, 300);
        turnOffFlashlight();
    }


    public void seizure() throws CameraAccessException{

    }

    public void convertToMorse(char letter) throws CameraAccessException {
        System.out.println(letter);
        if (letter == 'a'){
            quickFlash();
            longFlash();

        }else if(letter =='b'){
            longFlash();
            quickFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='c'){
            longFlash();
            quickFlash();
            longFlash();
            quickFlash();

        }else if(letter =='d'){
            longFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='e'){
            quickFlash();

        }else if(letter =='f'){
            quickFlash();
            quickFlash();
            longFlash();
            quickFlash();

        }else if(letter =='g'){
            longFlash();
            longFlash();
            quickFlash();

        }else if(letter =='h'){
            quickFlash();
            quickFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='i'){
            quickFlash();

        }else if(letter =='j'){
            quickFlash();
            longFlash();
            longFlash();
            longFlash();


        }else if(letter =='k'){
            longFlash();
            quickFlash();
            longFlash();

        }else if(letter =='l'){
            quickFlash();
            longFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='m'){
            longFlash();
            longFlash();

        }else if(letter =='n'){
            longFlash();
            quickFlash();

        }else if(letter =='o'){
            longFlash();
            longFlash();
            longFlash();

        }else if(letter =='p'){
            quickFlash();
            longFlash();
            longFlash();
            quickFlash();

        }else if(letter =='q'){
            longFlash();
            longFlash();
            quickFlash();
            longFlash();

        }else if(letter =='r'){
            quickFlash();
            longFlash();
            quickFlash();

        }else if(letter =='s'){
            quickFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='t'){
            longFlash();

        }else if(letter =='u'){
            quickFlash();
            quickFlash();
            longFlash();

        }else if(letter =='v'){
            quickFlash();
            quickFlash();
            quickFlash();
            longFlash();

        }else if(letter =='w'){
            quickFlash();
            longFlash();
            longFlash();

        }else if(letter =='x'){
            longFlash();
            quickFlash();
            quickFlash();
            longFlash();

        }else if(letter =='y'){
            longFlash();
            quickFlash();
            longFlash();
            longFlash();

        }else if(letter =='z'){
            longFlash();
            longFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='1'){
            quickFlash();
            longFlash();
            longFlash();
            longFlash();
            longFlash();

        }else if(letter =='2'){
            quickFlash();
            quickFlash();
            longFlash();
            longFlash();
            longFlash();

        }else if(letter =='3'){
            quickFlash();
            quickFlash();
            quickFlash();
            longFlash();
            longFlash();

        }else if(letter =='4'){
            quickFlash();
            quickFlash();
            quickFlash();
            quickFlash();
            longFlash();

        }else if(letter =='5'){
            longFlash();
            longFlash();
            longFlash();
            longFlash();
            longFlash();

        }else if(letter =='6'){
            longFlash();
            quickFlash();
            quickFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='7'){
            longFlash();
            longFlash();
            quickFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='8'){
            longFlash();
            longFlash();
            longFlash();
            quickFlash();
            quickFlash();

        }else if(letter =='9'){
            longFlash();
            longFlash();
            longFlash();
            longFlash();
            quickFlash();

        }else if(letter =='0'){
            longFlash();
            longFlash();
            longFlash();
            longFlash();
            longFlash();

        }else{
            System.out.println("Something went wrong, check for invalid input more strictly");
        }

    }

}

