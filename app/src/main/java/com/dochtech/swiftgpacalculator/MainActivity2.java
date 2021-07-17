
package com.dochtech.swiftgpacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    private Button rateApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //rateApp = findViewById(R.id.rate_us_btn);
    }

    public void RateApp(View view){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
        }
    }
    public void ShareApp(View view){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Swift GPA Calculator");
                String shareMessage= "\nNever worry about calculating your GPA, download Swift GPA App today and calculate your GPA with ease\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.dochtech.swiftgpacalculator"+"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }



    }
    public void AboutApp(View view){
        Intent intent = new Intent(getBaseContext(), AboutApp.class);
        startActivity(intent);
    }
    public void CalculateGPA(View view){
        Intent homeIntent = new Intent(getBaseContext(), GPACalculator.class);
        startActivity(homeIntent);
    }
    public void MoreApp(View view){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/collection/cluster?clp=igNGChkKEzcxNzg5Nzk2MTM0NDQxNDMzMDAQCBgDEicKIWNvbS5kb2NodGVjaC5ncmVhdG1hcnZhYmxlcHJpbWFyeRABGAMYAQ%3D%3D:S:ANO1ljKhdw8&gsr=CkmKA0YKGQoTNzE3ODk3OTYxMzQ0NDE0MzMwMBAIGAMSJwohY29tLmRvY2h0ZWNoLmdyZWF0bWFydmFibGVwcmltYXJ5EAEYAxgB:S:ANO1ljK2XrM")));
        }
        catch (ActivityNotFoundException e){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/collection/cluster?clp=igNGChkKEzcxNzg5Nzk2MTM0NDQxNDMzMDAQCBgDEicKIWNvbS5kb2NodGVjaC5ncmVhdG1hcnZhYmxlcHJpbWFyeRABGAMYAQ%3D%3D:S:ANO1ljKhdw8&gsr=CkmKA0YKGQoTNzE3ODk3OTYxMzQ0NDE0MzMwMBAIGAMSJwohY29tLmRvY2h0ZWNoLmdyZWF0bWFydmFibGVwcmltYXJ5EAEYAxgB:S:ANO1ljK2XrM")));
        }
    }
}