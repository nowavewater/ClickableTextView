package com.example.clickabletextview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private String[] names = {"Lucy", "Jim", "Lily", "Li Lei"}; // An Array of strings

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.main_text_view);
        String nameString = "";
        for (String name : names) {  // Create a single string with names
            nameString = nameString + name + "   ";  // 3 spaces
        }
        SpannableString spannableNames = new SpannableString(nameString);
        int start = 0;
        int end;
        for (String name : names) {  // Set span for each name in the string
            end = start + name.length();
            spannableNames.setSpan(new ClickableName(this), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 3;  // 3 spaces
        }
        mTextView.setMovementMethod(LinkMovementMethod.getInstance()); // Required to make things work
        mTextView.setText(spannableNames);
    }

    private class ClickableName extends ClickableSpan {

        private Context context;

        public ClickableName(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View widget) { // Handle click event
            TextView textView = (TextView) widget;
            Spanned spanned = (Spanned) textView.getText();
            int start = spanned.getSpanStart(this);
            int end = spanned.getSpanEnd(this);
            String name = spanned.subSequence(start, end).toString();
            Toast.makeText(context, name, Toast.LENGTH_LONG).show();
        }
    }
}
