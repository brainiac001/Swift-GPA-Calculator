package com.dochtech.swiftgpacalculator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.DecimalFormat;

public class GPACalculator extends AppCompatActivity {
    private Spinner spinner_1_L;
    private Spinner spinner_1_R;
    private Spinner spinner_2_L;
    private Spinner spinner_2_R;
    private Spinner spinner_3_L;
    private Spinner spinner_3_R;
    private Spinner spinner_4_L;
    private Spinner spinner_4_R;
    private Spinner spinner_5_L;
    private Spinner spinner_5_R;
    private Spinner spinner_6_L;
    private Spinner spinner_6_R;
    private Spinner spinner_7_L;
    private Spinner spinner_7_R;
    private Spinner spinner_8_L;
    private Spinner spinner_8_R;
    private Spinner spinner_9_L;
    private Spinner spinner_9_R;
    private Spinner spinner_10_L;
    private Spinner spinner_10_R;
    private Spinner spinner_11_L;
    private Spinner spinner_11_R;
    private Spinner spinner_12_L;
    private Spinner spinner_12_R;
    private Spinner spinner_13_L;
    private Spinner spinner_13_R;
    private Spinner spinner_14_L;
    private Spinner spinner_14_R;
    private Spinner spinner_15_L;
    private Spinner spinner_15_R;
    private Spinner spinner_16_L;
    private Spinner spinner_16_R;
    private Spinner spinner_17_L;
    private Spinner spinner_17_R;
    private Spinner spinner_18_L;
    private Spinner spinner_18_R;
    private Spinner spinner_19_L;
    private Spinner spinner_19_R;
    private Spinner spinner_20_L;
    private Spinner spinner_20_R;
    private Spinner spinner_21_L;
    private Spinner spinner_21_R;
    private Spinner spinner_22_L;
    private Spinner spinner_22_R;
    private Spinner spinner_23_L;
    private Spinner spinner_23_R;
    private Spinner spinner_24_L;
    private Spinner spinner_24_R;
    private Spinner spinner_25_L;
    private Spinner spinner_25_R;
    private Spinner spinner_26_L;
    private Spinner spinner_26_R;
    private Spinner spinner_27_L;
    private Spinner spinner_27_R;
    private Spinner spinner_28_L;
    private Spinner spinner_28_R;
    private Spinner spinner_29_L;
    private Spinner spinner_29_R;
    private Spinner spinner_30_L;
    private Spinner spinner_30_R;
    private Bitmap bitmap;
    private TextView gpa;
    private Button next2;
    private LinearLayout gpa1;
    private final static String[] requestWritePermission =
            { Manifest.permission.WRITE_EXTERNAL_STORAGE };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_gpacalculator);
        setupspinners();

        final boolean hasWritePermission = RuntimePermissionUtil.checkPermissonGranted(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);


        Button capture_save =  findViewById(R.id.capture_save);
        capture_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bitmap = ScreenShott.getInstance().takeScreenShotOfRootView(view);
                if (bitmap != null) {
                    if (hasWritePermission) {
                        saveScreenshot();
                    }
                    else {
                        RuntimePermissionUtil.requestPermission(GPACalculator.this, requestWritePermission, 100);
                    }
                }
            }
        });

        ImageButton next = findViewById(R.id.fab1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GPACalculator.this, GPACalculator.class);
                startActivity(i);
                finish();

            }
        });




        next2 = findViewById(R.id.fab3);
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });


    }

    public void saveScreenshot() {
        // Save the screenshot

        try {
            File file = ScreenShott.getInstance()
                    .saveScreenshotToPicturesFolder(GPACalculator.this, bitmap, "my_gpa");
            // Display a toast
            Toast.makeText(GPACalculator.this, "GPA saved at " + file.getAbsolutePath(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull final String[] permissions,
                                           @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100: {

                RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                    @Override
                    public void onPermissionGranted() {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            saveScreenshot();
                        }
                    }

                    @Override
                    public void onPermissionDenied() {
                        Toast.makeText(GPACalculator.this, "Permission Denied! You cannot save image!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            }
        }
    }

    public void openDialog(){
        DialogFragment next2 = new helpdialog();
        next2.show(getSupportFragmentManager(), "Help dialog");
    }







    @Override
    protected void onStart() {
        super.onStart();
        setuplisteners();
    }

    private void calculateGPA() {
        double total = 0;
        double totalattempt = 0;
        double courses = 0;

        if (spinner_1_L.getSelectedItemPosition() > 0) {
            if (spinner_1_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_1_L.getSelectedItemPosition() * returnvalue(spinner_1_R.getSelectedItemPosition());
                totalattempt += (double) spinner_1_L.getSelectedItemPosition();
            }
        }
        if (spinner_2_L.getSelectedItemPosition() > 0) {
            if (spinner_2_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_2_L.getSelectedItemPosition() * returnvalue(spinner_2_R.getSelectedItemPosition());
                totalattempt += (double) spinner_2_L.getSelectedItemPosition();
            }
        }
        if (spinner_3_L.getSelectedItemPosition() > 0) {
            if (spinner_3_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_3_L.getSelectedItemPosition() * returnvalue(spinner_3_R.getSelectedItemPosition());
                totalattempt += (double) spinner_3_L.getSelectedItemPosition();
            }
        }
        if (spinner_4_L.getSelectedItemPosition() > 0) {
            if (spinner_4_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_4_L.getSelectedItemPosition() * returnvalue(spinner_4_R.getSelectedItemPosition());
                totalattempt += (double) spinner_4_L.getSelectedItemPosition();
            }
        }
        if (spinner_5_L.getSelectedItemPosition() > 0) {
            if (spinner_5_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_5_L.getSelectedItemPosition() * returnvalue(spinner_5_R.getSelectedItemPosition());
                totalattempt += (double) spinner_5_L.getSelectedItemPosition();
            }
        }

        if (spinner_6_L.getSelectedItemPosition() > 0) {
            if (spinner_6_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_6_L.getSelectedItemPosition() * returnvalue(spinner_6_R.getSelectedItemPosition());
                totalattempt += (double) spinner_6_L.getSelectedItemPosition();
            }
        }
        if (spinner_7_L.getSelectedItemPosition() > 0) {
            if (spinner_7_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_7_L.getSelectedItemPosition() * returnvalue(spinner_7_R.getSelectedItemPosition());
                totalattempt += (double) spinner_7_L.getSelectedItemPosition();
            }
        }
        if (spinner_8_L.getSelectedItemPosition() > 0) {
            if (spinner_8_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_8_L.getSelectedItemPosition() * returnvalue(spinner_8_R.getSelectedItemPosition());
                totalattempt += (double) spinner_8_L.getSelectedItemPosition();
            }
        }
        if (spinner_9_L.getSelectedItemPosition() > 0) {
            if (spinner_9_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_9_L.getSelectedItemPosition() * returnvalue(spinner_9_R.getSelectedItemPosition());
                totalattempt += (double) spinner_9_L.getSelectedItemPosition();
            }
        }
        if (spinner_10_L.getSelectedItemPosition() > 0) {
            if (spinner_10_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_10_L.getSelectedItemPosition() * returnvalue(spinner_10_R.getSelectedItemPosition());
                totalattempt += (double) spinner_10_L.getSelectedItemPosition();
            }
        }

        if (spinner_11_L.getSelectedItemPosition() > 0) {
            if (spinner_11_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_11_L.getSelectedItemPosition() * returnvalue(spinner_11_R.getSelectedItemPosition());
                totalattempt += (double) spinner_11_L.getSelectedItemPosition();
            }
        }
        if (spinner_12_L.getSelectedItemPosition() > 0) {
            if (spinner_12_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_12_L.getSelectedItemPosition() * returnvalue(spinner_12_R.getSelectedItemPosition());
                totalattempt += (double) spinner_12_L.getSelectedItemPosition();
            }
        }
        if (spinner_13_L.getSelectedItemPosition() > 0) {
            if (spinner_13_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_13_L.getSelectedItemPosition() * returnvalue(spinner_13_R.getSelectedItemPosition());
                totalattempt += (double) spinner_13_L.getSelectedItemPosition();
            }
        }
        if (spinner_14_L.getSelectedItemPosition() > 0) {
            if (spinner_14_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_14_L.getSelectedItemPosition() * returnvalue(spinner_14_R.getSelectedItemPosition());
                totalattempt += (double) spinner_14_L.getSelectedItemPosition();
            }
        }
        if (spinner_15_L.getSelectedItemPosition() > 0) {
            if (spinner_15_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_15_L.getSelectedItemPosition() * returnvalue(spinner_15_R.getSelectedItemPosition());
                totalattempt += (double) spinner_15_L.getSelectedItemPosition();
            }
        }

        if (spinner_16_L.getSelectedItemPosition() > 0) {
            if (spinner_16_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_16_L.getSelectedItemPosition() * returnvalue(spinner_16_R.getSelectedItemPosition());
                totalattempt += (double) spinner_16_L.getSelectedItemPosition();
            }
        }
        if (spinner_17_L.getSelectedItemPosition() > 0) {
            if (spinner_17_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_17_L.getSelectedItemPosition() * returnvalue(spinner_17_R.getSelectedItemPosition());
                totalattempt += (double) spinner_17_L.getSelectedItemPosition();
            }
        }
        if (spinner_18_L.getSelectedItemPosition() > 0) {
            if (spinner_18_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_18_L.getSelectedItemPosition() * returnvalue(spinner_18_R.getSelectedItemPosition());
                totalattempt += (double) spinner_18_L.getSelectedItemPosition();
            }
        }
        if (spinner_19_L.getSelectedItemPosition() > 0) {
            if (spinner_19_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_19_L.getSelectedItemPosition() * returnvalue(spinner_19_R.getSelectedItemPosition());
                totalattempt += (double) spinner_19_L.getSelectedItemPosition();
            }
        }
        if (spinner_20_L.getSelectedItemPosition() > 0) {
            if (spinner_20_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_20_L.getSelectedItemPosition() * returnvalue(spinner_20_R.getSelectedItemPosition());
                totalattempt += (double) spinner_20_L.getSelectedItemPosition();
            }
        }
        if (spinner_21_L.getSelectedItemPosition() > 0) {
            if (spinner_21_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_21_L.getSelectedItemPosition() * returnvalue(spinner_21_R.getSelectedItemPosition());
                totalattempt += (double) spinner_21_L.getSelectedItemPosition();
            }
        }

        if (spinner_22_L.getSelectedItemPosition() > 0) {
            if (spinner_22_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_22_L.getSelectedItemPosition() * returnvalue(spinner_22_R.getSelectedItemPosition());
                totalattempt += (double) spinner_22_L.getSelectedItemPosition();
            }
        }
        if (spinner_23_L.getSelectedItemPosition() > 0) {
            if (spinner_23_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_23_L.getSelectedItemPosition() * returnvalue(spinner_23_R.getSelectedItemPosition());
                totalattempt += (double) spinner_23_L.getSelectedItemPosition();
            }
        }
        if (spinner_24_L.getSelectedItemPosition() > 0) {
            if (spinner_24_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_24_L.getSelectedItemPosition() * returnvalue(spinner_24_R.getSelectedItemPosition());
                totalattempt += (double) spinner_24_L.getSelectedItemPosition();
            }
        }

        if (spinner_25_L.getSelectedItemPosition() > 0) {
            if (spinner_25_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_25_L.getSelectedItemPosition() * returnvalue(spinner_25_R.getSelectedItemPosition());
                totalattempt += (double) spinner_25_L.getSelectedItemPosition();
            }
        }

        if (spinner_26_L.getSelectedItemPosition() > 0) {
            if (spinner_26_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_26_L.getSelectedItemPosition() * returnvalue(spinner_26_R.getSelectedItemPosition());
                totalattempt += (double) spinner_26_L.getSelectedItemPosition();
            }
        }
        if (spinner_27_L.getSelectedItemPosition() > 0) {
            if (spinner_27_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_27_L.getSelectedItemPosition() * returnvalue(spinner_27_R.getSelectedItemPosition());
                totalattempt += (double) spinner_27_L.getSelectedItemPosition();
            }
        }
        if (spinner_28_L.getSelectedItemPosition() > 0) {
            if (spinner_28_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_28_L.getSelectedItemPosition() * returnvalue(spinner_28_R.getSelectedItemPosition());
                totalattempt += (double) spinner_28_L.getSelectedItemPosition();
            }
        }
        if (spinner_29_L.getSelectedItemPosition() > 0) {
            if (spinner_29_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_29_L.getSelectedItemPosition() * returnvalue(spinner_29_R.getSelectedItemPosition());
                totalattempt += (double) spinner_29_L.getSelectedItemPosition();
            }
        }
        if (spinner_30_L.getSelectedItemPosition() > 0) {
            if (spinner_30_R.getSelectedItemPosition() > 0) {
                ++courses;
                total += (double) spinner_30_L.getSelectedItemPosition() * returnvalue(spinner_30_R.getSelectedItemPosition());
                totalattempt += (double) spinner_30_L.getSelectedItemPosition();
            }
        }
        if (courses > 0) {
            total = total / totalattempt;
            DecimalFormat df =  new DecimalFormat("#.00");
            gpa.setText("GPA = " +df.format(total));
        }
    }

    private double returnvalue(int value) {
        if (value == 0) {
            Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            return 0.0;
        } else if (value == 1) {
            return 5.0;
        } else if (value == 2) {
            return 4.0;
        } else if (value == 3) {
            return 3.0;
        } else if (value == 4) {
            return 2.0;
        } else if (value == 5) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    private void setupspinners() {
        spinner_1_L = (Spinner) findViewById(R.id.spinner_1_L);
        ArrayAdapter<String> spinnerArrayAdapter_1_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_1_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_1_L.setAdapter(spinnerArrayAdapter_1_L);

        spinner_1_R = (Spinner) findViewById(R.id.spinner_1_R);
        ArrayAdapter<String> spinnerArrayAdapter_1_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_1_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_1_R.setAdapter(spinnerArrayAdapter_1_R);

        spinner_2_L = (Spinner) findViewById(R.id.spinner_2_L);
        ArrayAdapter<String> spinnerArrayAdapter_2_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_2_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_2_L.setAdapter(spinnerArrayAdapter_2_L);

        spinner_2_R = (Spinner) findViewById(R.id.spinner_2_R);
        ArrayAdapter<String> spinnerArrayAdapter_2_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_2_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_2_R.setAdapter(spinnerArrayAdapter_2_R);

        spinner_3_L = (Spinner) findViewById(R.id.spinner_3_L);
        ArrayAdapter<String> spinnerArrayAdapter_3_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_3_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_3_L.setAdapter(spinnerArrayAdapter_3_L);

        spinner_3_R = (Spinner) findViewById(R.id.spinner_3_R);
        ArrayAdapter<String> spinnerArrayAdapter_3_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_3_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_3_R.setAdapter(spinnerArrayAdapter_3_R);

        spinner_4_L = (Spinner) findViewById(R.id.spinner_4_L);
        ArrayAdapter<String> spinnerArrayAdapter_4_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_4_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_4_L.setAdapter(spinnerArrayAdapter_4_L);

        spinner_4_R = (Spinner) findViewById(R.id.spinner_4_R);
        ArrayAdapter<String> spinnerArrayAdapter_4_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_4_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_4_R.setAdapter(spinnerArrayAdapter_4_R);

        spinner_5_L = (Spinner) findViewById(R.id.spinner_5_L);
        ArrayAdapter<String> spinnerArrayAdapter_5_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_5_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_5_L.setAdapter(spinnerArrayAdapter_5_L);

        spinner_5_R = (Spinner) findViewById(R.id.spinner_5_R);
        ArrayAdapter<String> spinnerArrayAdapter_5_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_5_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_5_R.setAdapter(spinnerArrayAdapter_5_R);

        spinner_6_L = (Spinner) findViewById(R.id.spinner_6_L);
        ArrayAdapter<String> spinnerArrayAdapter_6_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_6_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_6_L.setAdapter(spinnerArrayAdapter_6_L);

        spinner_6_R = (Spinner) findViewById(R.id.spinner_6_R);
        ArrayAdapter<String> spinnerArrayAdapter_6_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_6_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_6_R.setAdapter(spinnerArrayAdapter_6_R);

        spinner_7_L = (Spinner) findViewById(R.id.spinner_7_L);
        ArrayAdapter<String> spinnerArrayAdapter_7_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_7_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_7_L.setAdapter(spinnerArrayAdapter_7_L);

        spinner_7_R = (Spinner) findViewById(R.id.spinner_7_R);
        ArrayAdapter<String> spinnerArrayAdapter_7_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_7_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_7_R.setAdapter(spinnerArrayAdapter_7_R);

        spinner_8_L = (Spinner) findViewById(R.id.spinner_8_L);
        ArrayAdapter<String> spinnerArrayAdapter_8_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_8_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_8_L.setAdapter(spinnerArrayAdapter_8_L);

        spinner_8_R = (Spinner) findViewById(R.id.spinner_8_R);
        ArrayAdapter<String> spinnerArrayAdapter_8_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_8_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_8_R.setAdapter(spinnerArrayAdapter_8_R);

        spinner_9_L = (Spinner) findViewById(R.id.spinner_9_L);
        ArrayAdapter<String> spinnerArrayAdapter_9_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_9_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_9_L.setAdapter(spinnerArrayAdapter_9_L);

        spinner_9_R = (Spinner) findViewById(R.id.spinner_9_R);
        ArrayAdapter<String> spinnerArrayAdapter_9_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_9_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_9_R.setAdapter(spinnerArrayAdapter_9_R);

        spinner_10_L = (Spinner) findViewById(R.id.spinner_10_L);
        ArrayAdapter<String> spinnerArrayAdapter_10_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_10_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_10_L.setAdapter(spinnerArrayAdapter_10_L);

        spinner_10_R = (Spinner) findViewById(R.id.spinner_10_R);
        ArrayAdapter<String> spinnerArrayAdapter_10_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_10_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_10_R.setAdapter(spinnerArrayAdapter_10_R);

        spinner_11_L = (Spinner) findViewById(R.id.spinner_11_L);
        ArrayAdapter<String> spinnerArrayAdapter_11_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_11_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_11_L.setAdapter(spinnerArrayAdapter_11_L);

        spinner_11_R = (Spinner) findViewById(R.id.spinner_11_R);
        ArrayAdapter<String> spinnerArrayAdapter_11_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_11_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_11_R.setAdapter(spinnerArrayAdapter_11_R);

        spinner_12_L = (Spinner) findViewById(R.id.spinner_12_L);
        ArrayAdapter<String> spinnerArrayAdapter_12_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_12_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_12_L.setAdapter(spinnerArrayAdapter_12_L);

        spinner_12_R = (Spinner) findViewById(R.id.spinner_12_R);
        ArrayAdapter<String> spinnerArrayAdapter_12_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_12_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_12_R.setAdapter(spinnerArrayAdapter_12_R);

        spinner_13_L = (Spinner) findViewById(R.id.spinner_13_L);
        ArrayAdapter<String> spinnerArrayAdapter_13_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_13_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_13_L.setAdapter(spinnerArrayAdapter_13_L);

        spinner_13_R = (Spinner) findViewById(R.id.spinner_13_R);
        ArrayAdapter<String> spinnerArrayAdapter_13_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_13_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_13_R.setAdapter(spinnerArrayAdapter_13_R);

        spinner_14_L = (Spinner) findViewById(R.id.spinner_14_L);
        ArrayAdapter<String> spinnerArrayAdapter_14_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_14_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_14_L.setAdapter(spinnerArrayAdapter_14_L);

        spinner_14_R = (Spinner) findViewById(R.id.spinner_14_R);
        ArrayAdapter<String> spinnerArrayAdapter_14_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_14_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_14_R.setAdapter(spinnerArrayAdapter_14_R);

        spinner_15_L = (Spinner) findViewById(R.id.spinner_15_L);
        ArrayAdapter<String> spinnerArrayAdapter_15_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_15_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_15_L.setAdapter(spinnerArrayAdapter_15_L);

        spinner_15_R = (Spinner) findViewById(R.id.spinner_15_R);
        ArrayAdapter<String> spinnerArrayAdapter_15_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_15_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_15_R.setAdapter(spinnerArrayAdapter_15_R);

        spinner_16_L = (Spinner) findViewById(R.id.spinner_16_L);
        ArrayAdapter<String> spinnerArrayAdapter_16_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_16_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_16_L.setAdapter(spinnerArrayAdapter_16_L);

        spinner_16_R = (Spinner) findViewById(R.id.spinner_16_R);
        ArrayAdapter<String> spinnerArrayAdapter_16_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_16_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_16_R.setAdapter(spinnerArrayAdapter_16_R);

        spinner_17_L = (Spinner) findViewById(R.id.spinner_17_L);
        ArrayAdapter<String> spinnerArrayAdapter_17_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_17_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_17_L.setAdapter(spinnerArrayAdapter_17_L);

        spinner_17_R = (Spinner) findViewById(R.id.spinner_17_R);
        ArrayAdapter<String> spinnerArrayAdapter_17_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_17_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_17_R.setAdapter(spinnerArrayAdapter_17_R);

        spinner_18_L = (Spinner) findViewById(R.id.spinner_18_L);
        ArrayAdapter<String> spinnerArrayAdapter_18_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_18_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_18_L.setAdapter(spinnerArrayAdapter_18_L);

        spinner_18_R = (Spinner) findViewById(R.id.spinner_18_R);
        ArrayAdapter<String> spinnerArrayAdapter_18_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_18_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_18_R.setAdapter(spinnerArrayAdapter_18_R);

        spinner_19_L = (Spinner) findViewById(R.id.spinner_19_L);
        ArrayAdapter<String> spinnerArrayAdapter_19_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_19_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_19_L.setAdapter(spinnerArrayAdapter_19_L);

        spinner_19_R = (Spinner) findViewById(R.id.spinner_19_R);
        ArrayAdapter<String> spinnerArrayAdapter_19_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_19_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_19_R.setAdapter(spinnerArrayAdapter_19_R);

        spinner_20_L = (Spinner) findViewById(R.id.spinner_20_L);
        ArrayAdapter<String> spinnerArrayAdapter_20_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_20_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_20_L.setAdapter(spinnerArrayAdapter_20_L);

        spinner_20_R = (Spinner) findViewById(R.id.spinner_20_R);
        ArrayAdapter<String> spinnerArrayAdapter_20_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_20_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_20_R.setAdapter(spinnerArrayAdapter_20_R);

        spinner_21_L = (Spinner) findViewById(R.id.spinner_21_L);
        ArrayAdapter<String> spinnerArrayAdapter_21_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_21_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_21_L.setAdapter(spinnerArrayAdapter_21_L);

        spinner_21_R = (Spinner) findViewById(R.id.spinner_21_R);
        ArrayAdapter<String> spinnerArrayAdapter_21_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_21_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_21_R.setAdapter(spinnerArrayAdapter_21_R);

        spinner_22_L = (Spinner) findViewById(R.id.spinner_22_L);
        ArrayAdapter<String> spinnerArrayAdapter_22_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_22_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_22_L.setAdapter(spinnerArrayAdapter_22_L);

        spinner_22_R = (Spinner) findViewById(R.id.spinner_22_R);
        ArrayAdapter<String> spinnerArrayAdapter_22_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_22_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_22_R.setAdapter(spinnerArrayAdapter_22_R);

        spinner_23_L = (Spinner) findViewById(R.id.spinner_23_L);
        ArrayAdapter<String> spinnerArrayAdapter_23_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_23_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_23_L.setAdapter(spinnerArrayAdapter_23_L);

        spinner_23_R = (Spinner) findViewById(R.id.spinner_23_R);
        ArrayAdapter<String> spinnerArrayAdapter_23_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_23_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_23_R.setAdapter(spinnerArrayAdapter_23_R);

        spinner_24_L = (Spinner) findViewById(R.id.spinner_24_L);
        ArrayAdapter<String> spinnerArrayAdapter_24_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_24_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_24_L.setAdapter(spinnerArrayAdapter_24_L);

        spinner_24_R = (Spinner) findViewById(R.id.spinner_24_R);
        ArrayAdapter<String> spinnerArrayAdapter_24_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_24_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_24_R.setAdapter(spinnerArrayAdapter_24_R);

        spinner_25_L = (Spinner) findViewById(R.id.spinner_25_L);
        ArrayAdapter<String> spinnerArrayAdapter_25_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_25_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_25_L.setAdapter(spinnerArrayAdapter_25_L);

        spinner_25_R = (Spinner) findViewById(R.id.spinner_25_R);
        ArrayAdapter<String> spinnerArrayAdapter_25_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_25_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_25_R.setAdapter(spinnerArrayAdapter_25_R);

        spinner_26_L = (Spinner) findViewById(R.id.spinner_26_L);
        ArrayAdapter<String> spinnerArrayAdapter_26_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_26_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_26_L.setAdapter(spinnerArrayAdapter_26_L);

        spinner_26_R = (Spinner) findViewById(R.id.spinner_26_R);
        ArrayAdapter<String> spinnerArrayAdapter_26_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_26_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_26_R.setAdapter(spinnerArrayAdapter_26_R);

        spinner_27_L = (Spinner) findViewById(R.id.spinner_27_L);
        ArrayAdapter<String> spinnerArrayAdapter_27_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_27_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_27_L.setAdapter(spinnerArrayAdapter_27_L);

        spinner_27_R = (Spinner) findViewById(R.id.spinner_27_R);
        ArrayAdapter<String> spinnerArrayAdapter_27_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_27_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_27_R.setAdapter(spinnerArrayAdapter_27_R);

        spinner_28_L = (Spinner) findViewById(R.id.spinner_28_L);
        ArrayAdapter<String> spinnerArrayAdapter_28_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_28_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_28_L.setAdapter(spinnerArrayAdapter_28_L);

        spinner_28_R = (Spinner) findViewById(R.id.spinner_28_R);
        ArrayAdapter<String> spinnerArrayAdapter_28_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_28_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_28_R.setAdapter(spinnerArrayAdapter_28_R);

        spinner_29_L = (Spinner) findViewById(R.id.spinner_29_L);
        ArrayAdapter<String> spinnerArrayAdapter_29_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_29_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_29_L.setAdapter(spinnerArrayAdapter_29_L);

        spinner_29_R = (Spinner) findViewById(R.id.spinner_29_R);
        ArrayAdapter<String> spinnerArrayAdapter_29_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_29_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_29_R.setAdapter(spinnerArrayAdapter_29_R);

        spinner_30_L = (Spinner) findViewById(R.id.spinner_30_L);
        ArrayAdapter<String> spinnerArrayAdapter_30_L = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.credit_array));
        spinnerArrayAdapter_30_L.setDropDownViewResource(R.layout.spinner_item);
        spinner_30_L.setAdapter(spinnerArrayAdapter_30_L);

        spinner_30_R = (Spinner) findViewById(R.id.spinner_30_R);
        ArrayAdapter<String> spinnerArrayAdapter_30_R = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.grade_array));
        spinnerArrayAdapter_30_R.setDropDownViewResource(R.layout.spinner_item);
        spinner_30_R.setAdapter(spinnerArrayAdapter_30_R);

        gpa = (TextView) findViewById(R.id.textView);
    }

    private void setuplisteners() {
        spinner_1_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_1_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_2_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_2_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_3_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_3_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_4_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_4_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_5_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_5_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_6_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_6_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_7_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_7_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_8_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_8_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_9_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_9_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_10_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_10_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinner_11_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_11_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_12_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_12_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_13_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_13_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_14_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_14_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_15_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_15_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_16_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_16_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_17_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_17_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_18_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_18_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_19_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_19_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_20_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_20_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinner_21_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_21_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_22_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_22_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_23_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_23_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_24_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_24_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_25_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_25_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_26_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_26_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_27_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_27_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_28_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_28_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_29_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_29_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_30_L.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        spinner_30_R.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateGPA();

            }
        });


    }

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    @Override
    public void onBackPressed(){
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back twice to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }
}

